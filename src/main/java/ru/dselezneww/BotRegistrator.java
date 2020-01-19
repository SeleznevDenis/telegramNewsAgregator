package ru.dselezneww;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.dselezneww.pl.TelegramEndpoint;

@Component
public class BotRegistrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotRegistrator.class);

    @Autowired
    private static TelegramEndpoint bot;

    public static void register() {
        if (bot != null) {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(bot);
            } catch (TelegramApiRequestException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("Контекст не поднят");
        }
    }
}
