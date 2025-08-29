package com.westmike.articool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.westmike.articool.service.APScraper;

@SpringBootApplication
public class ArticoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticoolApplication.class, args);
	}

	@Bean
	public CommandLineRunner runScraper(APScraper scraper) {
		return args -> {
			String url = "https://apnews.com/article/artificial-intelligence-religion-apocalypse-ec449ccedbf8054764547356757ff335";
			scraper.scrapeOne(url);
		};
	}
}
