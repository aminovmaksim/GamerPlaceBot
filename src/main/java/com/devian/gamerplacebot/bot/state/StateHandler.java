package com.devian.gamerplacebot.bot.state;

import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;

public interface StateHandler {

    /**
     * Обработка входящего сообщения от пользователя
     */
    HandleResult handle(Long userId, Message message);

    /**
     * Обработка входящего Callback от пользователя
     */
    HandleResult handle(Long userId, CallbackQuery callback);
}
