package ru.dselezneww.models;

import java.util.Calendar;
import java.util.Date;

public class News {
    private String newsOwner;
    private String title;
    private String author;
    private String link;
    private String shortDescription;
    private String longDescription;
    private Date publishedDate;

    public String getNewsOwner() {
        return newsOwner;
    }

    public void setNewsOwner(String newsOwner) {
        this.newsOwner = newsOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
