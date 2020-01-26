package ru.dselezneww.gf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.dselezneww.converters.NewsConverter;
import ru.dselezneww.models.News;
import ru.dselezneww.models.NewsSources;
import ru.dselezneww.pb.RssParser;

import java.util.*;

@Component
public class NewsLoader {

    private RssParser rssParser;
    private Map<NewsSources, NewsConverter> storage = new HashMap<>();

    @Autowired
    public NewsLoader(RssParser rssParser, NewsConverter... converters) {
        this.rssParser = rssParser;
        for (NewsConverter converter : converters) {
            this.storage.put(converter.getSource(), converter);
        }
    }

    public static synchronized void test() {

    }

    @Cacheable("news")
    public List<News> getNews(NewsSources source) {
        if (!this.storage.containsKey(source)) {
            throw new IllegalArgumentException();
        }
        return this.storage.get(source).convert(rssParser.parseFeed(source.getUrl()));
    }
}
