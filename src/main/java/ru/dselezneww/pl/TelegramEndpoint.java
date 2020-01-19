package ru.dselezneww.pl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramEndpoint extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramEndpoint.class);

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
             execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return "FormerNewsFeeder";
    }

    @Override
    public String getBotToken() {
        return "FormerNewsFeeder";
    }
}
