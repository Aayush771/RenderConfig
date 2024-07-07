package com.crio.xnews;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NewsParserTest {

    /**
     * Test the parse method with valid JSON input.
     * 
     * @throws IOException if an I/O error occurs during JSON parsing.
     */
    @Test
    public void testParseValidJson() throws IOException {
        // Sample JSON representing a valid response
        String json = "{\"status\":\"ok\",\"totalResults\":2,\"articles\":[" +
                "{\"title\":\"Title 1\",\"description\":\"Description 1\",\"author\":\"Author 1\",\"url\":\"http://example.com/1\",\"publishedAt\":\"2023-06-12T12:00:00Z\",\"content\":\"Content 1\"}," +
                "{\"title\":\"Title 2\",\"description\":\"Description 2\",\"author\":\"Author 2\",\"url\":\"http://example.com/2\",\"publishedAt\":\"2023-06-12T13:00:00Z\",\"content\":\"Content 2\"}" +
                "]}";

        // Parse the JSON
        List<NewsArticle> articles = NewsParser.parse(json);

        // Verify the results
        assertNotNull(articles); // Assert that the list is not null
        assertEquals(2, articles.size()); // Assert that the list contains exactly 2 articles

        // Validate the first article
        NewsArticle article1 = articles.get(0);
        assertEquals("Title 1", article1.getTitle());
        assertEquals("Description 1", article1.getDescription());
        assertEquals("Author 1", article1.getAuthor());
        assertEquals("http://example.com/1", article1.getUrl());
        assertEquals("2023-06-12T12:00:00Z", article1.getPublishedAt());
        assertEquals("Content 1", article1.getContent());

        // Validate the second article
        NewsArticle article2 = articles.get(1);
        assertEquals("Title 2", article2.getTitle());
        assertEquals("Description 2", article2.getDescription());
        assertEquals("Author 2", article2.getAuthor());
        assertEquals("http://example.com/2", article2.getUrl());
        assertEquals("2023-06-12T13:00:00Z", article2.getPublishedAt());
        assertEquals("Content 2", article2.getContent());
    }
}
