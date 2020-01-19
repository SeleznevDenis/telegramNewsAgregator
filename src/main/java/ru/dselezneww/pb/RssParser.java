package ru.dselezneww.pb;

import com.sun.syndication.feed.synd.SyndFeed;
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
}
