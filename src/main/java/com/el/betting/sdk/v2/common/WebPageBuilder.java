package com.el.betting.sdk.v2.common;

public class WebPageBuilder {
    private String name;
    private String url = "";
    private String description = "";
    private boolean webCrawlerSupported = false;
    private boolean httpCrawlerSupported = false;
    private String webCrawlStrategy = "default";

    public WebPageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WebPageBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public WebPageBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public WebPageBuilder setWebCrawlerSupported(boolean webCrawlerSupported) {
        this.webCrawlerSupported = webCrawlerSupported;
        return this;
    }

    public WebPageBuilder setHttpCrawlerSupported(boolean httpCrawlerSupported) {
        this.httpCrawlerSupported = httpCrawlerSupported;
        return this;
    }

    public WebPageBuilder setWebCrawlStrategy(String webCrawlStrategy) {
        this.webCrawlStrategy = webCrawlStrategy;
        return this;
    }

    public WebPage createWebPage() {
        return new WebPage(name, url, description, webCrawlerSupported, httpCrawlerSupported, webCrawlStrategy);
    }
}