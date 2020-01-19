package ru.dselezneww.pl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dselezneww.orchestration.TelegramOrchestration;

@Component
public class TelegramEndpoint extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramEndpoint.class);

    private TelegramOrchestration orchestration;

    @Autowired
    public TelegramEndpoint(TelegramOrchestration orchestration) {
        this.orchestration = orchestration;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String result;
        try {
            result = this.orchestration.handleInput(update);
            LOGGER.info(result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            result = "Some problem";
        }
        this.sendMsg(update.getMessage().getChatId().toString(), result);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableWebPagePreview();
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
             execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка при отправке сообщения", e);
            LOGGER.error("Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return "FormerNewsFeederBot";
    }

    @Override
    public String getBotToken() {
        return "1021779322:AAFnf_HBppypJcG-x49UWWOodWiUH4gN98I";
    }
}
