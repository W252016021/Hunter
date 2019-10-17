package com.walixiwa.webhunter.model;

public class TabModel {
    private String tabTitle;
    private String tabUrl;
    private String first;

    public TabModel() {
    }

    public TabModel(String tabTitle, String tabUrl) {
        this.tabTitle = tabTitle;
        this.tabUrl = tabUrl;
    }

    public TabModel(String tabTitle, String tabUrl, String first) {
        this.tabTitle = tabTitle;
        this.tabUrl = tabUrl;
        this.first = first;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public TabModel setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
        return this;
    }

    public String getTabUrl() {
        return tabUrl;
    }

    public TabModel setTabUrl(String tabUrl) {
        this.tabUrl = tabUrl;
        return this;
    }

    public String getFirst() {
        return first;
    }

    public TabModel setFirst(String first) {
        this.first = first;
        return this;
    }

}
