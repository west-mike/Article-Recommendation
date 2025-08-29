package com.westmike.articool.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
    private String text;
    private String[] tokens;

    public TextProcessor(String text) {
        this.text = text;
    }

    // Strip all non-alphanumeric characters and convert to lowercase
    public void stripToLowercase() {
        // System.out.println("Original text: " + this.text);
        this.text = this.text.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();
        // System.out.println("Stripped text: " + this.text);
    }

    // Tokenize the text into words
    public void tokenize() {
        if (this.text == null) {
            this.tokens = new String[0];
        } else {
            this.tokens = this.text.split("\\s+");
        }
    }

    // Remove English stopwords from the tokens
    public void stripStopwordsEN() {
        Set<String> stopwords = new HashSet<>();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("englishST.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopwords.add(line.trim().toLowerCase());
            }
        } catch (IOException | NullPointerException e) {
            // Handle missing file or IO error
            this.tokens = new String[0];
            return;
        }
        if (this.tokens == null) {
            this.tokens = new String[0];
            return;
        }
        List<String> filtered = new ArrayList<>();
        for (String token : this.tokens) {
            if (!stopwords.contains(token)) {
                filtered.add(token);
            }
        }
        this.tokens = filtered.toArray(new String[0]);
    }

    public String[] getTokens() {
        return this.tokens;
    }
}
