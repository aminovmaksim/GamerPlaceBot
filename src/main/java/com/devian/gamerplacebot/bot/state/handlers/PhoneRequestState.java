package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.data.DataAccess;
import com.devian.gamerplacebot.data.redis.entity.UserInfo;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.WebAppInfo;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PhoneRequestState implements StateHandler {

    static String TEXT_MAIN = "Отлично, %s! Теперь приступим к работе ...";
    static String TEXT_TRY_AGAIN = "Мне все-таки нужен твой номер телефона 😔";
    static String BUTTON_REQUEST_NUMBER = "Предоставить номер телефона";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Long userId, Message message) {
        var contact = message.contact();
        if (contact != null) {
            dataAccess.userDao.setUserInfo(UserInfo.builder()
                    .id(contact.userId())
                    .phoneNumber(contact.phoneNumber())
                    .firstName(contact.firstName())
                    .lastName(contact.lastName())
                    .build());
            return HandleResult.builder()
                    .nextState(State.PHONE_REQUEST)
                    .baseRequest(new SendMessage(userId, String.format(TEXT_MAIN, contact.firstName())).replyMarkup(
                            new InlineKeyboardMarkup(
                                    new InlineKeyboardButton("Карта").webApp(new WebAppInfo("http://localhost/bot/clubs-map"))
                            )
                    ))
                    .build();
        }
        return HandleResult.builder()
                .nextState(State.PHONE_REQUEST)
                .baseRequest(new SendMessage(userId, TEXT_TRY_AGAIN).replyMarkup(new ReplyKeyboardMarkup(
                        new KeyboardButton(BUTTON_REQUEST_NUMBER).requestContact(true))
                        .oneTimeKeyboard(true)
                        .resizeKeyboard(true)))
                .build();
    }

    @Override
    public HandleResult handle(Long userId, CallbackQuery callback) {
        return HandleResult.empty();
    }
}
