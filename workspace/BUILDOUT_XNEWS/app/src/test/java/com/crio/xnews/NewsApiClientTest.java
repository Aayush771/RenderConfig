package com.crio.xnews;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class NewsApiClientTest {

    private NewsApiClient newsApiClient = new NewsApiClient();

    /**
     * Test to fetch news articles with a valid query.
     * 
     * This test sends a request with a valid query ("technology"), language ("en"), 
     * and sort order ("relevance"). It verifies that the list of articles returned 
     * is not null and contains at least one article.
     *
     * @throws IOException if there is an error during the API call.
     */
    @Test
    public void testFetchNewsArticlesWithValidQuery() throws IOException {
        List<NewsArticle> articles = newsApiClient.fetchNewsArticles("technology", "en", "relevance");

        assertNotNull(articles);
        assertTrue(articles.size() > 0);
    }

    /**
     * Test to fetch news articles with an empty query.
     * 
     * This test sends a request with an empty query string. It verifies that an 
     * IllegalArgumentException is thrown with the expected message.
     */
    @Test
    public void testFetchNewsArticlesWithEmptyQuery() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            newsApiClient.fetchNewsArticles("", "en", "relevance");
        });

        assertEquals("Query parameter must not be empty", exception.getMessage());
    }

    /**
     * Test to fetch news articles with a null query.
     * 
     * This test sends a request with a null query string. It verifies that an 
     * IllegalArgumentException is thrown with the expected message.
     */
    @Test
    public void testFetchNewsArticlesWithNullQuery() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            newsApiClient.fetchNewsArticles(null, "en", "relevance");
        });

        assertEquals("Query parameter must not be empty", exception.getMessage());
    }
}
