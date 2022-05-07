package com.devian.gamerplacebot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotProperties {

    @Value("${telegram.bot.token}")
    String token;

    @Value("${telegram.bot.webhook.enabled}")
    Boolean webhookEnabled;

    @Value("${telegram.bot.webhook.host}")
    String webhookHost;

    @Value("${server.port}")
    Integer webhookPort;

    @Value("${telegram.bot.webhook.maxConnections}")
    Integer webhookMaxConnections;
}
