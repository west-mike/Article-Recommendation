package com.westmike.articool.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class APScraper {
    private String[] urlStrings;

    public APScraper(String[] urlStrings) {
        this.urlStrings = urlStrings;
    }

    public void scrapeOne(String urlString) {
        try {
            Document doc = Jsoup.connect(urlString).get();
            Element title = doc.selectFirst("h1.Page-headline");
            System.out.println("Title: " + (title != null ? title.text() : "Not found"));
            Element time = doc.selectFirst("bsp-timestamp");
            System.out.println("Time: " + (time != null ? time.attr("data-timestamp") : "Not found"));
            Elements tags = doc.select("div.Page-breadcrumbs");
            for (Element tag : tags) {
                System.out.println("Tag: " + tag.text());
            }
            Element author = doc.selectFirst("div.Author-name");
            System.out.println("Author: " + (author != null ? author.text() : "Not found"));
            Element body = doc.selectFirst("div.RichTextBody");
            Elements body_p = body.getElementsByTag("p");
            String content = "";
            for (Element p : body_p) {
                // System.out.println("Paragraph: " + p.text());
                content += p.text();
            }
            System.out.println("Content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    public void scrapeAll() {
        for (String url : urlStrings) {
            System.out.println("Scraping URL: " + url);
            try {
                Document doc = Jsoup.connect(url).get();
                Element title = doc.selectFirst("h1.Page-headline");
                System.out.println("Title: " + (title != null ? title.text() : "Not found"));
                Element time = doc.selectFirst("bsp-timestamp");
                System.out.println("Time: " + (time != null ? time.attr("data-timestamp") : "Not found"));
                Elements tags = doc.select("div.Page-breadcrumbs");
                for (Element tag : tags) {
                    System.out.println("Tag: " + tag.text());
                }
                Element author = doc.selectFirst("div.Author-name");
                System.out.println("Author: " + (author != null ? author.text() : "Not found"));
                Element body = doc.selectFirst("div.RichTextBody");
                Elements body_p = body.getElementsByTag("p");
                String content = "";
                for (Element p : body_p) {
                    // System.out.println("Paragraph: " + p.text());
                    content += p.text();
                }
                System.out.println("Content: " + content);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public String[] scrapeOneAndTokenize(String urlString) {
        try {
            Document doc = Jsoup.connect(urlString).get();
            Element body = doc.selectFirst("div.RichTextBody");
            Elements body_p = body.getElementsByTag("p");
            String content = "";
            for (Element p : body_p) {
                content += p.text();
            }
            TextProcessor tp = new TextProcessor(content);
            tp.stripToLowercase();
            tp.tokenize();
            tp.stripStopwordsEN();
            return tp.getTokens();
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
