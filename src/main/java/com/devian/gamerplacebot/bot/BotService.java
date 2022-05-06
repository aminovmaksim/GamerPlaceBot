package com.devian.gamerplacebot.bot;

import com.devian.gamerplacebot.bot.state.Command;
import com.devian.gamerplacebot.bot.state.StateHandlerProvider;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.data.dao.UserDao;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BotService {

    TelegramBot bot;
    UserDao userDao;
    StateHandlerProvider stateProvider;

    public BotService(TelegramBot bot,
                      UserDao userDao,
                      StateHandlerProvider stateProvider) {
        this.bot = bot;
        this.userDao = userDao;
        this.stateProvider = stateProvider;
        setListener();
    }

    private void setListener() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    var userId = 0L;
                    var handleResult = HandleResult.empty();
                    var message = update.message();
                    if (message != null) {
                        userId = message.from().id();
                        var command = Command.of(message.text());
                        var state = command != null ? command.getState() : userDao.getState(userId);
                        handleResult = stateProvider.getHandler(state).handle(userId, message);
                    }
                    var callback = update.callbackQuery();
                    if (callback != null) {
                        userId = callback.from().id();
                        var state = userDao.getState(userId);
                        handleResult = stateProvider.getHandler(state).handle(userId, callback);
                    }
                    bot.execute(handleResult.getBaseRequest());
                    userDao.setState(userId, handleResult.getNextState());
                } catch (Exception e) {
                    log.error("Update Listener exception: {}", e.getMessage());
                    return update.updateId();
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
