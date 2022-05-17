package com.devian.gamerplacebot.bot;

import com.devian.gamerplacebot.bot.state.Command;
import com.devian.gamerplacebot.bot.state.StateHandlerProvider;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.data.DataAccess;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
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
    DataAccess dataAccess;
    StateHandlerProvider stateProvider;

    public void handleUpdate(Update update) {
        var handleResult = HandleResult.empty();
        // Пришло новое сообщение
        if (update.message() != null) {
            handleResult = handleMessage(update.message());
        }
        // Пришел callback с клавиатуры
        if (update.callbackQuery() != null) {
            handleResult = handleCallback(update.callbackQuery());
        }
        var response = bot.execute(handleResult.getBaseRequest());

        // Сохраним идентификатор последнего отправленного сообщения
        if (response instanceof SendResponse) {
            var responseMessage = ((SendResponse) response).message();
            if (responseMessage != null) {
                dataAccess.userDao.setLastMessage(handleResult.getUserId(), responseMessage.messageId());
            }
        }

        // Обновим статус пользователя
        dataAccess.userDao.setState(handleResult.getUserId(), handleResult.getNextState());
    }

    private HandleResult handleMessage(Message message) {
        var userId = message.from().id();
        var command = Command.of(message.text());
        var state = command != null ? command.getState() : dataAccess.userDao.getState(userId);
        log.info("Message from user {} (state: {}) - {}", userId, state, message.text());
        return stateProvider.getHandler(state).handle(message);
    }

    private HandleResult handleCallback(CallbackQuery callbackQuery) {
        var userId = callbackQuery.from().id();
        var state = dataAccess.userDao.getState(userId);
        return stateProvider.getHandler(state).handle(callbackQuery);
    }
}
