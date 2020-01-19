package ru.dselezneww.pb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dselezneww.models.News;

import java.util.ArrayList;
import java.util.List;

@Component
public class HabrToNewsConverter {

    private RssParser rssparser;
    private String url = "https://habr.com/ru/rss/best/daily";

    @Autowired
    public HabrToNewsConverter(RssParser parser) {
        this.rssparser = parser;
    }

    public List<News> convert() {
        List<News> newsList = new ArrayList<>();
        SyndFeed syndFeed = this.rssparser.parseFeed(this.url);
        String habrNewsOwner = syndFeed.getAuthor();
        for (Object obj : syndFeed.getEntries()) {
            SyndEntry entry = (SyndEntry) obj;
            News news = new News();
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
