package com.devian.gamerplacebot.bot.adapter;

import com.devian.gamerplacebot.bot.BotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetWebhook;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ConditionalOnProperty(name = "telegram.bot.webhook.enabled", havingValue = "false")
public class BotAdapterPollingImpl {

    TelegramBot bot;
    BotService botService;
    ApplicationContext applicationContext;

    @PostConstruct
    public void setupListener() {
        var webhookRemoveResponse = bot.execute(new SetWebhook());
        if (!webhookRemoveResponse.isOk()) {
            log.error("Cannot remove webhook {} : {}", webhookRemoveResponse.errorCode(), webhookRemoveResponse.description());
            SpringApplication.exit(applicationContext);
            System.exit(1);
        }
        log.info("Existing webhook successfully removed. Setting up listener ...");
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    botService.handleUpdate(update);
                } catch (Exception e) {
                    log.error("Update Listener exception", e);
                    return update.updateId();
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        log.info("Update listener successfully added");
    }
}
