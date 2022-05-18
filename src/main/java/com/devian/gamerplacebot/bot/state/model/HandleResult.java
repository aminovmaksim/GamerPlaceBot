package com.devian.gamerplacebot.bot.state.model;

import com.devian.gamerplacebot.bot.model.Intent;
import com.devian.gamerplacebot.bot.state.State;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandleResult {

    Long userId;
    State nextState;
    Intent intent;

    public static HandleResult empty() {
        return create(0L, null, new Intent(new SendChatAction(0, ChatAction.choose_sticker)));
    }

    public static HandleResult create(Long userId, State nextState, Intent intent) {
        return new HandleResult(userId, nextState, intent);
    }
}
