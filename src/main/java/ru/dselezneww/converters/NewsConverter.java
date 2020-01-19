package ru.dselezneww.converters;

import com.sun.syndication.feed.synd.SyndFeed;
import ru.dselezneww.models.News;
import ru.dselezneww.models.NewsSources;

import java.util.List;

public interface NewsConverter {

    List<News> convert(SyndFeed source);

    NewsSources getSource();
}
