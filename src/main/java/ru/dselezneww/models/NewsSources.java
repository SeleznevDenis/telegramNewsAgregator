package ru.dselezneww.models;

public enum  NewsSources {
    HABR("https://habr.com/ru/rss/best/daily");

    private String url;

    NewsSources(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
