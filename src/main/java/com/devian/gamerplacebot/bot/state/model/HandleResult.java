package com.devian.gamerplacebot.bot.state.model;

import com.devian.gamerplacebot.bot.state.State;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendChatAction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandleResult {

    State nextState;
    BaseRequest<?, ?> baseRequest;

    public static HandleResult empty() {
        return new HandleResult(null, new SendChatAction(0, ChatAction.choose_sticker));
    }

    public static HandleResult create(State nextState, BaseRequest<?, ?> baseRequest) {
        return new HandleResult(nextState, baseRequest);
    }
}
