package com.walixiwa.webhunter.model;

import java.io.Serializable;

public class ParseResultModel implements Serializable {
    private String title;
    private String url;

    public ParseResultModel() {
    }

    public ParseResultModel(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public ParseResultModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ParseResultModel setUrl(String url) {
        this.url = url;
        return this;
    }
}
