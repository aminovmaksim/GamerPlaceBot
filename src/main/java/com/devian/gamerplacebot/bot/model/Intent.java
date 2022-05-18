package com.devian.gamerplacebot.bot.model;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intent {

    private final List<BaseRequest<?, ?>> requests = new ArrayList<>();

    public Intent(BaseRequest<?, ?>... requests) {
        this.requests.addAll(Arrays.asList(requests));
    }

    public Intent addAction(BaseRequest<?, ?> request) {
        this.requests.add(request);
        return this;
    }

    public void executeAll(TelegramBot bot) {
        requests.forEach(baseRequest -> execute(baseRequest, bot));
    }

    private void execute(BaseRequest<?, ?> request, TelegramBot bot) {
        bot.execute(request);
    }
}
