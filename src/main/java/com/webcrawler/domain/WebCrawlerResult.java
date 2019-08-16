package com.webcrawler.domain;

import java.util.HashMap;
import java.util.HashSet;

public class WebCrawlerResult {

    private String baseUrl;
    private HashMap<String, HashSet<String>> result;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HashMap<String, HashSet<String>> getResult() {
        return result;
    }

    public void setResult(HashMap<String, HashSet<String>> result) {
        this.result = result;
    }
}
