package com.devian.gamerplacebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.devian.gamerplacebot.data.postgres")
@EnableRedisRepositories(basePackages = "com.devian.gamerplacebot.data.redis")
public class GamerPlaceBotApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GamerPlaceBotApplication.class, args);
    }

}
