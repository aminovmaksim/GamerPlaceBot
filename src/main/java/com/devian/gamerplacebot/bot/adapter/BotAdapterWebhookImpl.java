package com.devian.gamerplacebot.bot.adapter;

import com.devian.gamerplacebot.bot.BotService;
import com.devian.gamerplacebot.config.BotProperties;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetWebhook;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequiredArgsConstructor
@DependsOn("botProperties")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ConditionalOnProperty(name = "telegram.bot.webhook.enabled", havingValue = "true")
public class BotAdapterWebhookImpl {

    TelegramBot bot;
    BotService botService;
    BotProperties botProperties;
    ApplicationContext context;

    @PostConstruct
    public void registerWebhook() {
        var webhookUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(botProperties.getWebhookHost())
                .port(botProperties.getWebhookPort())
                .addPathSegment(botProperties.getToken())
                .build().url().toString();
        log.info("Setting up webhook URL: {}", webhookUrl);
        var response = bot.execute(new SetWebhook()
                .url(webhookUrl)
                .maxConnections(botProperties.getWebhookMaxConnections()));
        if (!response.isOk()) {
            log.error("Cannot register webhook {} : {}", response.errorCode(), response.description());
            SpringApplication.exit(context);
            System.exit(1);
        }
        log.info("Webhook successfully registered");
    }

    @PostMapping(path = "/#{@botProperties.getToken()}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleUpdate(@RequestBody Update update) {
        try {
            botService.handleUpdate(update);
            return ResponseEntity.ok("ACCEPTED");
        } catch (Exception e) {
            log.error("Update Listener exception", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
