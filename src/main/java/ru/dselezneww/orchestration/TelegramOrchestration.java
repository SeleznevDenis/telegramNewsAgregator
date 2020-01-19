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
import java.util.Random;

@Component
public class TelegramOrchestration {

    private NewsLoader newsLoader;
    private PlConverter plConverter;

    @Autowired
    public TelegramOrchestration(NewsLoader newsLoader, PlConverter plConverter) {
        this.newsLoader = newsLoader;
        this.plConverter = plConverter;
    }

    public String handleInput(Update update) {
        List<News> news = this.newsLoader.getNews(NewsSources.HABR);
        Random random = new Random();
        News news1 = news.get(random.nextInt(news.size() - 1));
        return this.plConverter.convert(news1);
    }
}
