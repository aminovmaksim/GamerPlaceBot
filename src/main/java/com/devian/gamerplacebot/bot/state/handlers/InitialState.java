package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.data.DataAccess;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitialState implements StateHandler {

    static String TEXT_MAIN = "Добро пожаловать ... Для работы бота предоставьте номер телефона";
    static String BUTTON_REQUEST_NUMBER = "Предоставить номер телефона";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Long userId, Message message) {
        return HandleResult.builder()
                .nextState(State.PHONE_REQUEST)
                .baseRequest(new SendMessage(userId, TEXT_MAIN)
                        .replyMarkup(new ReplyKeyboardMarkup(
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
