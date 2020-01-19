package ru.dselezneww.gf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.dselezneww.converters.NewsConverter;
import ru.dselezneww.models.News;
import ru.dselezneww.models.NewsSources;
import ru.dselezneww.pb.RssParser;

import java.util.List;
import java.util.Map;

@Component
public class ConvertersStorage {

    private RssParser rssParser;
    private Map<NewsSources, NewsConverter> storage;

    @Autowired
    public ConvertersStorage(RssParser rssParser, NewsConverter... converters) {
        for (NewsConverter converter : converters) {
            this.storage.put(converter.getSource(), converter);
        }
    }

    @Cacheable("news")
    public List<News> getNews(NewsSources source) {
        if (!this.storage.containsKey(source)) {
            throw new IllegalArgumentException();
        }
        return this.storage.get(source).convert(rssParser.parseFeed(source.getUrl()));
    }
}
