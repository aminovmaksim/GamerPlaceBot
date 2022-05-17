package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.bot.state.utils.KeyboardProvider;
import com.devian.gamerplacebot.data.DataAccess;
import com.devian.gamerplacebot.data.model.UserInfo;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PhoneRequest implements StateHandler {

    static String TEXT_MAIN = "Отлично, %s! Теперь приступим к работе ...";
    static String TEXT_TRY_AGAIN = "Мне все-таки нужен твой номер телефона 😔";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Message message) {
        var userId = message.from().id();
        var contact = message.contact();
        if (contact != null) {
            dataAccess.userDao.setUserInfo(UserInfo.builder()
                    .id(contact.userId())
                    .phoneNumber(contact.phoneNumber())
                    .firstName(contact.firstName())
                    .lastName(contact.lastName())
                    .build());
            return HandleResult.create(userId, State.MAIN_MENU, new SendMessage(userId, String.format(TEXT_MAIN, contact.firstName()))
                    .replyMarkup(KeyboardProvider.mainMenu()));
        }
        return HandleResult.create(userId, State.PHONE_REQUEST, new SendMessage(userId, TEXT_TRY_AGAIN)
                        .replyMarkup(KeyboardProvider.requestNumber()));
    }

    @Override
    public HandleResult handle(CallbackQuery callback) {
        return HandleResult.empty();
    }
}
