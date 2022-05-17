package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.bot.state.utils.KeyboardProvider;
import com.devian.gamerplacebot.data.DataAccess;
import com.devian.gamerplacebot.utils.ClubUtils;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingSelect implements StateHandler {

    static String TEXT_BOOK = "Выберите клуб на карте или введите 4-х значный код клуба\nНапример: 0139";
    static String TEXT_SELECTED = "Вы выбрали клуб %s. Все верно?";

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Message message) {
        var userId = message.from().id();
        var text = message.text();
        if (ClubUtils.isValidId(text)) {
            return HandleResult.create(userId, State.CLUB_SELECTED, new SendMessage(userId, String.format(TEXT_SELECTED, text))
                    .replyMarkup(KeyboardProvider.confirmSelectedClub()));
        }
        var mapData = message.webAppData();
        if (mapData != null) {
            return HandleResult.create(userId, State.CLUB_SELECTED, new SendMessage(userId, String.format(TEXT_SELECTED, mapData.data()))
                    .replyMarkup(KeyboardProvider.confirmSelectedClub()));
        }
        return HandleResult.create(userId, State.BOOKING_SELECT, new SendMessage(userId, TEXT_BOOK)
                .replyMarkup(KeyboardProvider.map()));
    }

    @Override
    public HandleResult handle(CallbackQuery callback) {
        return HandleResult.empty();
    }
}
