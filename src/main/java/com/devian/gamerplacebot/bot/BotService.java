package com.devian.gamerplacebot.bot;

import com.devian.gamerplacebot.bot.state.Command;
import com.devian.gamerplacebot.bot.state.StateHandlerProvider;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.data.dao.UserDao;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BotService {

    TelegramBot bot;
    UserDao userDao;
    StateHandlerProvider stateProvider;

    public void handleUpdate(Update update) {
        var userId = 0L;
        var handleResult = HandleResult.empty();
        var message = update.message();
        if (message != null) {
            userId = message.from().id();
            var command = Command.of(message.text());
            var state = command != null ? command.getState() : userDao.getState(userId);
            handleResult = stateProvider.getHandler(state).handle(userId, message);
            log.info("User {} (state: {}) - {}", userId, state, message.text());
        }
        var callback = update.callbackQuery();
        if (callback != null) {
            userId = callback.from().id();
            var state = userDao.getState(userId);
            handleResult = stateProvider.getHandler(state).handle(userId, callback);
        }
        var response = bot.execute(handleResult.getBaseRequest());
        if (response instanceof SendResponse) {
            var responseMessage = ((SendResponse) response).message();
            if (responseMessage != null) {
                userDao.setLastMessage(userId, responseMessage.messageId());
            }
        }
        userDao.setState(userId, handleResult.getNextState());
    }
}
