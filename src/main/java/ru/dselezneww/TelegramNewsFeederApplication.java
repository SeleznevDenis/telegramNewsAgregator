package ru.dselezneww;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import ru.dselezneww.orchestration.TelegramOrchestration;
import ru.dselezneww.pl.TelegramEndpoint;

@ComponentScan("ru.dselezneww")
@EnableAutoConfiguration
@SpringBootConfiguration
@EnableScheduling
@EnableCaching
public class TelegramNewsFeederApplication {

    @Autowired
    private TelegramEndpoint bot;

    public static void main(String[] args) {
        SpringApplication.run(TelegramNewsFeederApplication.class, args);
        BotRegistrator.register();
    }

}
