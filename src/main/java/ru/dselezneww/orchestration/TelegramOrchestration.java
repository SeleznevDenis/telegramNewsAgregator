package ru.dselezneww.orchestration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dselezneww.gf.NewsLoader;
import ru.dselezneww.models.News;
import ru.dselezneww.models.NewsSources;
import ru.dselezneww.pl.PlConverter;
import ru.dselezneww.pl.TelegramEndpoint;

import java.util.List;

@Component
public class TelegramOrchestration {

    private TelegramEndpoint endpoint;
    private NewsLoader newsLoader;
    private PlConverter plConverter;

    @Autowired
    public TelegramOrchestration(NewsLoader newsLoader, PlConverter plConverter) {
        this.newsLoader = newsLoader;
        this.plConverter = plConverter;
    }

    @Autowired
    public void setTelegramEndpoint(TelegramEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public void handleInput(Update update) {
        List<News> news = this.newsLoader.getNews(NewsSources.HABR);
        News news1 = news.get(0);
        this.sendMessage(update.getMessage().getChatId().toString(), this.plConverter.convert(news1));
    }

    public void sendMessage(String chatId, String s) {
        this.endpoint.sendMsg(chatId, s);
    }
}
