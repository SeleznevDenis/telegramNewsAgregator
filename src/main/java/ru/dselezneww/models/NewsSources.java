package ru.dselezneww.models;

public enum  NewsSources {
    HABR("");

    private String url;

    NewsSources(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
