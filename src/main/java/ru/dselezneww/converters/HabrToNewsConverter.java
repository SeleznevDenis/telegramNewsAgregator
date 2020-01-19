package ru.dselezneww.converters;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.springframework.stereotype.Component;
import ru.dselezneww.models.News;
import ru.dselezneww.models.NewsSources;

import java.util.ArrayList;
import java.util.List;

@Component
public class HabrToNewsConverter implements NewsConverter {

    @Override
    public NewsSources getSource() {
        return NewsSources.HABR;
    }

    @Override
    public List<News> convert(SyndFeed source) {
        List<News> newsList = new ArrayList<>();
        String habrNewsOwner = source.getAuthor();
        for (Object obj : source.getEntries()) {
            SyndEntry entry = (SyndEntry) obj;
            News news = new News();
            news.setNewsOwner(habrNewsOwner);
            news.setTitle(entry.getTitle());
            news.setLink(entry.getLink());
            news.setPublishedDate(entry.getPublishedDate());
            SyndContent content = entry.getDescription();
            if (content != null) {
                news.setLongDescription(content.getValue());
            }
            newsList.add(news);
        }
        return newsList;
    }
}
