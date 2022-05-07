package com.devian.gamerplacebot.bot.state.handlers;

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
public class MainMenu implements StateHandler {

    static String TEXT_RETRY = "Выберите действие в меню";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Long userId, Message message) {
        return HandleResult.create(State.MAIN_MENU, new SendMessage(userId, TEXT_RETRY)
                .replyMarkup(KeyboardProvider.mainMenu()));
    }

    @Override
    public HandleResult handle(Long userId, CallbackQuery callback) {
        return HandleResult.empty();
    }
}
