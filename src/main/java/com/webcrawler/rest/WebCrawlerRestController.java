package com.webcrawler.rest;

import com.webcrawler.domain.WebCrawlerResult;
import com.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.concurrent.CompletableFuture;

@RestController
public class WebCrawlerRestController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @RequestMapping("/")
    public CompletableFuture<WebCrawlerResult> crawl(@PathParam("url") String url) {
        return webCrawlerService.webCrawlerSite(url);
    }
}
