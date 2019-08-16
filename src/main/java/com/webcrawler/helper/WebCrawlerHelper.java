package com.webcrawler.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerHelper {

    protected HashSet<String> getPageLinks(String url, int nest, HashSet<String> links) {

        if ((!links.contains(url) && (url.length() > 2))) {

            try {
                links.add(url);

                Document document = Jsoup.connect(url).get(); // fetch html code
                Elements linksOnPage = document.select("a[href]"); // select hyperlinks

                for (Element page : linksOnPage) { // get links found on the page
                    links.add(page.attr("abs:href"));

                    if (nest == 1) {  // fetch nested links
                        getPageLinks(page.attr("abs:href"), nest, links);
                    }
                }
            } catch (IOException e) {
                System.out.println("For '" + url + "': " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error on accessing url: " + url);
                System.err.println("Exception: " + e);
            }
        }

        return links;
    }

}
