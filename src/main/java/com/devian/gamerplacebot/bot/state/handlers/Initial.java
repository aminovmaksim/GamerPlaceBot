package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.model.Intent;
import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.bot.state.utils.KeyboardProvider;
import com.devian.gamerplacebot.data.DataAccess;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Initial implements StateHandler {

    static String TEXT_MAIN = "Добро пожаловать ... Для работы бота предоставьте номер телефона";
    static String TEXT_USER_EXIST = "Я тебя помню, %s! Давай начнем!";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Message message) {
        var userId = message.from().id();
        var userInfo = dataAccess.userDao.getUserInfo(userId);
        if (userInfo != null && userInfo.getPhoneNumber() != null) {
            return HandleResult.create(userId, State.MAIN_MENU, new Intent(new SendMessage(userId, String.format(TEXT_USER_EXIST, userInfo.getFirstName()))
                    .replyMarkup(KeyboardProvider.mainMenu())));
        }
        return HandleResult.create(userId, State.PHONE_REQUEST, new Intent(new SendMessage(userId, TEXT_MAIN)
                        .replyMarkup(KeyboardProvider.requestNumber())));
    }

    @Override
    public HandleResult handle(CallbackQuery callback) {
        return HandleResult.empty();
    }
}
