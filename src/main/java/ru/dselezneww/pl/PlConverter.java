package ru.dselezneww.pl;

import org.springframework.stereotype.Component;
import ru.dselezneww.models.News;

import java.util.StringJoiner;

@Component
public class PlConverter {

    public String convert(News news) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add(news.getTitle() + " " + news.getAuthor())
                .add(news.getLink())
                .add(news.getLongDescription())
                .add(news.getPublishedDate().toString())
                .add("(C) " + news.getNewsOwner());
        return sj.toString();
    }
}
