package com.devian.gamerplacebot.bot.state.handlers;

import com.devian.gamerplacebot.bot.model.Intent;
import com.devian.gamerplacebot.bot.state.State;
import com.devian.gamerplacebot.bot.state.StateHandler;
import com.devian.gamerplacebot.bot.state.model.HandleResult;
import com.devian.gamerplacebot.bot.state.utils.KeyboardProvider;
import com.devian.gamerplacebot.data.DataAccess;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.devian.gamerplacebot.bot.state.utils.Buttons.CHANGE;
import static com.devian.gamerplacebot.bot.state.utils.Buttons.CORRECT;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClubSelected implements StateHandler {

    DataAccess dataAccess;

    @Override
    public HandleResult handle(Message message) {
        return HandleResult.empty();
    }

    @Override
    public HandleResult handle(CallbackQuery callback) {
        var userId = callback.from().id();
        var messageId = callback.message().messageId();
        if (messageId != null) {
            if (CORRECT.equals(callback.data())) {
                return HandleResult.create(userId, State.CLUB_SELECTED, new Intent(new EditMessageReplyMarkup(userId, messageId)
                        .replyMarkup(KeyboardProvider.confirmSelectedClub_Correct())));
            }
            if (CHANGE.equals(callback.data())) {
                return HandleResult.create(userId, State.BOOKING_SELECT, new Intent(new DeleteMessage(userId, messageId), BookingSelect.smSelect(userId)));
            }
        }
        return HandleResult.empty();
    }
}
