package com.el.betting.sdk.v2.common;

public class WebPage {

    private String name;

    private String url;

    private String description;

    private boolean webCrawlerSupported;

    private boolean httpCrawlerSupported;

    private String webCrawlStrategy;

    public WebPage(String name, String url, String description, boolean webCrawlerSupported, boolean httpCrawlerSupported, String webCrawlStrategy) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.webCrawlerSupported = webCrawlerSupported;
        this.httpCrawlerSupported = httpCrawlerSupported;
        this.webCrawlStrategy = webCrawlStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isWebCrawlerSupported() {
        return webCrawlerSupported;
    }

    public void setWebCrawlerSupported(boolean webCrawlerSupported) {
        this.webCrawlerSupported = webCrawlerSupported;
    }

    public boolean isHttpCrawlerSupported() {
        return httpCrawlerSupported;
    }

    public void setHttpCrawlerSupported(boolean httpCrawlerSupported) {
        this.httpCrawlerSupported = httpCrawlerSupported;
    }

    public String getWebCrawlStrategy() {
        return webCrawlStrategy;
    }

    public void setWebCrawlStrategy(String webCrawlStrategy) {
        this.webCrawlStrategy = webCrawlStrategy;
    }
}
