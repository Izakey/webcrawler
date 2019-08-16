package com.webcrawler;

import com.webcrawler.domain.WebCrawlerResult;
import com.webcrawler.service.WebCrawlerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebCrawlerApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(WebCrawlerApplicationTests.class);

	@Autowired
	private WebCrawlerService webCrawlerService;

	@Test
	public void shouldTestCrawler() throws ExecutionException, InterruptedException {
		// Start the clock
		long start = System.currentTimeMillis();

		// execute asynchronous lookups
		final CompletableFuture<WebCrawlerResult> rescale = webCrawlerService.webCrawlerSite("https://rescale.com");
		logger.info("Processing rescale...");
		final CompletableFuture<WebCrawlerResult> google = webCrawlerService.webCrawlerSite("https://www.google.com");
		logger.info("Processing google...");

		// Wait until they are all done
		CompletableFuture.allOf(rescale,google).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));

		Assert.assertNotNull(rescale);
		Assert.assertEquals("https://rescale.com", rescale.get().getBaseUrl());

		Assert.assertNotNull(google);
		Assert.assertEquals("https://www.google.com", google.get().getBaseUrl());
	}

}
