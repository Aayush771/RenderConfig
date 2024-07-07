package com.crio.xnews;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;

public class NewsApiClient {

    private static final String API_URL = "https://newsapi.org/v2/everything";
    private static final String API_KEY = "d1f8fcfbd4a24bd4861cce1fb9d3d699";

// TODO: CRIO_TASK_MODULE_PROJECT
// Utilize the Okhttp3 library to send a request to the News API, including the provided query, language, and sortBy parameters.
// Ensure that the Gradle dependency for Okhttp3 is included in build.gradle.
// Parse the JSON response using NewsParser.
// If the "query" parameter is empty, an IllegalArgumentException is thrown. 
// If there is an error during the API request or response parsing, IOException is thrown.

    public List<NewsArticle> fetchNewsArticles(String query, String language, String sortBy) throws IOException {
      //if(sortBy == null) sortBy = "popularity";
      String url = buildUrl(query, language, sortBy);
    //  String url = "https://newsapi.org/v2/everything?q=Apple&from=2024-05-18&sortBy=popularity&apiKey=d1f8fcfbd4a24bd4861cce1fb9d3d699";
      ObjectMapper objectMapper = new ObjectMapper();
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
      .url(url)
      .build();

  try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      String jsonResponse = response.body().string();
      NewsApiResponse apiResponse = objectMapper.readValue(jsonResponse, NewsApiResponse.class);
      return apiResponse.getArticles();
    }

}

// TODO: CRIO_TASK_MODULE_PROJECT
// Construct the URL required to make a request to the News API and use this in above method.
// Refer to https://newsapi.org/docs/endpoints/everything for guidance on URL construction.
// The "query" parameter is mandatory and must not be empty. 
// If the "query" parameter is empty, throw IllegalArgumentException with message "Query parameter must not be empty".
// The "language" and "sortBy" parameters are optional and will be included in the URL if they are non-empty.

private String buildUrl(String query, String language, String sortBy) {
  if (query == null || query.isEmpty()) throw new IllegalArgumentException("Query parameter must not be empty");

  StringBuilder urlBuilder = new StringBuilder(API_URL);
  //urlBuilder.append(String.format("https://newsapi.org/v2/everything?q=%s", query));

  urlBuilder.append(String.format("?q=%s", query));
  if (language != null && !language.isEmpty()) {
      urlBuilder.append(String.format("&language=%s", language));
  }
  if (sortBy != null && !sortBy.isEmpty()) {
    urlBuilder.append(String.format("&sortBy=%s", sortBy));
}
  
//  urlBuilder.append(String.format("&sortBy=%s", sortBy));
  urlBuilder.append(String.format("&apiKey=%s", API_KEY));
  return urlBuilder.toString();
}
}