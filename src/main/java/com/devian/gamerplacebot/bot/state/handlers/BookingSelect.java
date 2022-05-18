package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.model.Intent;
import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.bot.state.utils.KeyboardProvider;
import com.devian.gamerplacebot.data.DataAccess;
import com.devian.gamerplacebot.utils.ClubUtils;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.BaseRequest;
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
            return HandleResult.create(userId, State.CLUB_SELECTED, new Intent(smSelected(userId, text)));
        }
        var mapData = message.webAppData();
        if (mapData != null) {
            return HandleResult.create(userId, State.CLUB_SELECTED, new Intent(smSelected(userId, mapData.data())));
        }
        return HandleResult.create(userId, State.BOOKING_SELECT, new Intent(smSelect(userId)));
    }

    @Override
    public HandleResult handle(CallbackQuery callback) {
        return HandleResult.empty();
    }

    public static BaseRequest<?, ?> smSelect(Long userId) {
        return new SendMessage(userId, TEXT_BOOK)
                .replyMarkup(KeyboardProvider.map());
    }

    public static BaseRequest<?, ?> smSelected(Long userId, String clubInfo) {
        return new SendMessage(userId, String.format(TEXT_SELECTED, clubInfo))
                .replyMarkup(KeyboardProvider.confirmSelectedClub());
    }
}
