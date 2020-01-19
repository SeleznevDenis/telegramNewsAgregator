package ru.dselezneww.pb;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class RssParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssParser.class);

    public SyndFeed parseFeed(String url) {
        SyndFeed result = null;
        try {
            result = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public void printRSSContent(SyndFeed feed) {
        System.out.println("About feed:");
        System.out.println("Author: " + feed.getAuthor());
        System.out.println("Authors:");
        if (feed.getAuthors() != null) {
            for (Object author : feed.getAuthors()) {
                System.out.println(((SyndPerson) author).getName());
                System.out.println(((SyndPerson) author).getEmail());
                System.out.println(((SyndPerson) author).getUri());
                System.out.println();
            }
        }

        System.out.println("Title: " + feed.getTitle());
        System.out.println("Title Ex: " + feed.getTitleEx());
        System.out.println("Description: " + feed.getDescription());
        System.out.println("Description Ex: " + feed.getDescriptionEx());
        System.out.println("Date" + feed.getPublishedDate());
        System.out.println("Type: " + feed.getFeedType());
        System.out.println("Encoding: " + feed.getEncoding());
        System.out.println("(C) " + feed.getCopyright());
        System.out.println();

        for (Object object : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) object;
            System.out.println(entry.getTitle() + " - " + entry.getAuthor());
            System.out.println("entry.getLink()" + entry.getLink());
            for (Object contobj : entry.getContents()) {
                SyndContent content = (SyndContent) contobj;
                System.out.println("content.getType()" + content.getType());
                System.out.println("content.getValue()" + content.getValue());
            }

            SyndContent content = entry.getDescription();
            if (content != null)
                System.out.println("content.getValue()" + content.getValue());

            System.out.println("entry.getPublishedDate()" + entry.getPublishedDate());
            System.out.println();
        }
    }

/*
    public static void main(String[] args) throws Exception {

        RssParser parser = new RssParser();
        //parser.printRSSContent(parser.parseFeed("http://feeds2.feedburner.com/samolisov"));
        parser.printRSSContent(parser.parseFeed("https://habr.com/ru/rss/best/daily"));
    }*/
}
