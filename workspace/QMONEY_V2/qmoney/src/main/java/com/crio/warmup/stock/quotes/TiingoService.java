
package com.crio.warmup.stock.quotes;

import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class TiingoService implements StockQuotesService {


  private RestTemplate restTemplate;

  protected TiingoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws StockQuoteServiceException, JsonMappingException, JsonProcessingException {
    List<Candle> l = new ArrayList<>();
    try {
      String url = buildUri(symbol, from, to);

      String str = restTemplate.getForObject(url, String.class);

      ObjectMapper objectMapper = getObjectMapper();
      TiingoCandle[] tiingoCandles;

      tiingoCandles = objectMapper.readValue(str, TiingoCandle[].class);
      l = Arrays.asList(tiingoCandles);

    } catch (RuntimeException re) {
      throw new StockQuoteServiceException("Rate limit excided");
    }
    return l;

  }
  // System.out.println(Arrays.toString(tiingoCandles));

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
    return "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?" + "startDate=" + startDate
        + "&endDate=" + endDate + "&token=48f14c98fdb5bfe25d4cd9efdab2929656daeba9";
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  // Implement getStockQuote method below that was also declared in the interface.

  // Note:
  // 1. You can move the code from PortfolioManagerImpl#getStockQuote inside newly created method.
  // 2. Run the tests using command below and make sure it passes.
  // ./gradlew test --tests TiingoServiceTest


  // CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  // Write a method to create appropriate url to call the Tiingo API.



  // TODO: CRIO_TASK_MODULE_EXCEPTIONS
  // 1. Update the method signature to match the signature change in the interface.
  // Start throwing new StockQuoteServiceException when you get some invalid response from
  // Tiingo, or if Tiingo returns empty results for whatever reason, or you encounter
  // a runtime exception during Json parsing.
  // 2. Make sure that the exception propagates all the way from
  // PortfolioManager#calculateAnnualisedReturns so that the external user's of our API
  // are able to explicitly handle this exception upfront.

  // CHECKSTYLE:OFF


}
