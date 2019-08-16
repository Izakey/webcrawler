package com.webcrawler.service;

import com.webcrawler.domain.WebCrawlerResult;
import com.webcrawler.helper.WebCrawlerHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

@Service
public class WebCrawlerService extends WebCrawlerHelper {

    @Async
    public CompletableFuture<WebCrawlerResult> webCrawlerSite(final String url) {
        HashMap<String, HashSet<String>> urlMap = new HashMap<>();

        for (String href : getPageLinks(url, 0, new HashSet<>())) { // fetch top level links

            if (href.isEmpty()) {
                continue;
            }

            urlMap.put(href, getPageLinks(href, 1, new HashSet<>())); // fetch nested links
        }

        final WebCrawlerResult webCrawlerResult = new WebCrawlerResult();
        webCrawlerResult.setBaseUrl(url);
        webCrawlerResult.setResult(urlMap);

        return CompletableFuture.completedFuture(webCrawlerResult);
    }
}
