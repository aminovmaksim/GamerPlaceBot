package com.devian.gamerplacebot.config;

import com.pengrad.telegrambot.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BotConfiguration {

    @Value("${telegram.bot.token}")
    String token;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(token);
    }
}
