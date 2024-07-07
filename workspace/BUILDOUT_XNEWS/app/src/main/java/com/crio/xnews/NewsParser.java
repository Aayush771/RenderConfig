package com.crio.xnews;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

// TODO: CRIO_TASK_MODULE_PROJECT
// Deserialize JSON String representing the response from the News API and 
// then return the list of NewsArticle objects contained in the response.

    public static List<NewsArticle> parse(String json) throws IOException {

      NewsApiResponse apiResponse = objectMapper.readValue(json, NewsApiResponse.class);
      return apiResponse.getArticles();
    }
}