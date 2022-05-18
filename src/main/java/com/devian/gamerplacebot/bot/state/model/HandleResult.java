package com.devian.gamerplacebot.bot.state.model;

import com.devian.gamerplacebot.bot.state.State;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendChatAction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandleResult {

    Long userId;
    State nextState;
    List<BaseRequest<?, ?>> baseRequests;

    public static HandleResult empty() {
        return create(0L, null, new SendChatAction(0, ChatAction.choose_sticker));
    }

    public static HandleResult create(Long userId, State nextState, BaseRequest<?, ?> baseRequest) {
        var baseRequests = new ArrayList<BaseRequest<?, ?>>();
        baseRequests.add(baseRequest);
        return new HandleResult(userId, nextState, baseRequests);
    }

    public HandleResult addRequest(BaseRequest<?, ?> baseRequest) {
        this.baseRequests.add(baseRequest);
        return this;
    }
}
