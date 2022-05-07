package com.devian.gamerplacebot.config;

import com.pengrad.telegrambot.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BotConfiguration {

    @Bean
    public TelegramBot telegramBot(BotProperties properties) {
        return new TelegramBot(properties.getToken());
    }
}
