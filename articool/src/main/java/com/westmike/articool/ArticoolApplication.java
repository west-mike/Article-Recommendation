package com.westmike.articool;

import com.westmike.articool.service.APScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticoolApplication.class, args);

		String[] urls = {
				"https://apnews.com/article/artificial-intelligence-religion-apocalypse-ec449ccedbf8054764547356757ff335"
		};

		APScraper scraper = new APScraper(urls);

		// scraper.scrapeAll();
		String[] testTokens = scraper.scrapeOneAndTokenize(urls[0]);
		System.out.println("Tokens:" + String.join(", ", testTokens));
	}

}
