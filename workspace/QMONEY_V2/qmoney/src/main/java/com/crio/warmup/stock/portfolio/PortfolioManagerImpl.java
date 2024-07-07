
package com.crio.warmup.stock.portfolio;

// import static java.time.temporal.ChronoUnit.DAYS;
// import static java.time.temporal.ChronoUnit.SECONDS;
// import java.io.IOException;
// import java.net.URISyntaxException;

import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.crio.warmup.stock.quotes.StockQuotesService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// import java.util.concurrent.ExecutionException;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.Future;
// import java.util.concurrent.TimeUnit;
// import java.util.stream.Collectors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.*;

public class PortfolioManagerImpl implements PortfolioManager {

  private RestTemplate restTemplate;
  private StockQuotesService stockQuotesService;
  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
   public RestTemplate getTemplate(){
    return this.restTemplate;
   }
    public PortfolioManagerImpl(){
    }
    
  //TODO: CRIO_TASK_MODULE_REFACTOR
  // 1. Now we want to convert our code into a module, so we will not call it from main anymore.
  //    Copy your code from Module#3 PortfolioManagerApplication#calculateAnnualizedReturn
  //    into #calculateAnnualizedReturn function here and ensure it follows the method signature.
  // 2. Logic to read Json file and convert them into Objects will not be required further as our
  //    clients will take care of it, going forward.

  // Note:
  // Make sure to exercise the tests inside PortfolioManagerTest using command below:
  // ./gradlew test --tests PortfolioManagerTest

  //CHECKSTYLE:OFF


  public PortfolioManagerImpl(StockQuotesService stockQuotesService,RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
      this.stockQuotesService = stockQuotesService;
  }
  
  public PortfolioManagerImpl(StockQuotesService stockQuotesService) {
    this.stockQuotesService = stockQuotesService;
  }
  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  //CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Extract the logic to call Tiingo third-party APIs to a separate function.
  //  Remember to fill out the buildUri function and use that.


  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException {
        String url = buildUri(symbol, from, to);
        TiingoCandle[] tiingoCandles = restTemplate.getForObject(url, TiingoCandle[].class);
        //System.out.println(Arrays.toString(tiingoCandles));
      //  if(tiingoCandles != null) 
        return Arrays.asList(tiingoCandles);

       // else return new ArrayList<>();
  }

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
      return "https://api.tiingo.com/tiingo/daily/"+symbol+"/prices?"
            + "startDate="+startDate+"&endDate="+endDate+"&token=0ebb5832b9e1624cc372710e2355810f10fea3cb";
  }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate) throws StockQuoteServiceException {
    List<AnnualizedReturn> annualizedReturns =new ArrayList<>();
    try {
      annualizedReturns = annualizedReturnHelper(endDate, portfolioTrades);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      Collections.sort(annualizedReturns,getComparator());
      return annualizedReturns;
  }
  public List<AnnualizedReturn> annualizedReturnHelper(LocalDate endDate,List<PortfolioTrade> trade) throws JsonProcessingException, StockQuoteServiceException {
    List<AnnualizedReturn> list = new ArrayList<>();
    for(PortfolioTrade i:trade){
      List<Candle> candles = stockQuotesService.getStockQuote(i.getSymbol(), i.getPurchaseDate(), endDate);
      // if(candles != null){
        Candle tiingoCandle = candles.get(0);
        Candle tiingoCandlelast = candles.get(candles.size()-1);
        AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(tiingoCandlelast.getDate(), i, tiingoCandle.getOpen(), tiingoCandlelast.getClose());
        list.add(annualizedReturn);
      // }
    }
    return list;
}
public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        double totalReturn  = (double)(sellPrice - buyPrice) / buyPrice;
        double year=(double)ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate)/365;
        double annualizedReturn = Math.pow(++totalReturn,1/year)-1;

      return new AnnualizedReturn(trade.getSymbol(), annualizedReturn, totalReturn);
  }
@Override
public List<AnnualizedReturn> calculateAnnualizedReturnParallel(
    List<PortfolioTrade> portfolioTrades, LocalDate endDate, int numThreads)
    throws InterruptedException, StockQuoteServiceException {
  
      ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Future<AnnualizedReturn>> futures = new ArrayList<>();

        
        for (int i = 0; i < numThreads && i< portfolioTrades.size(); i++) {
            Callable<AnnualizedReturn> task = new TaskCallable(endDate,portfolioTrades.get(i));
            Future<AnnualizedReturn> future = executor.submit(task);
            futures.add(future);
        }

        // Collect the results from the futures
        List<AnnualizedReturn> results = new ArrayList<>();
        for (Future<AnnualizedReturn> future : futures) {
            try {
                AnnualizedReturn annualizedReturn = future.get();
                results.add(annualizedReturn);
            } catch (InterruptedException | ExecutionException e) {
                throw new StockQuoteServiceException("Rate limit excided");
            }
        }
        executor.shutdown();
        Collections.sort(results,getComparator());
        return results;
    }

class TaskCallable implements Callable<AnnualizedReturn> {
    PortfolioTrade trades;
    LocalDate endDate;


    public TaskCallable(LocalDate endDate, PortfolioTrade trades) {
        this.endDate = endDate;
        this.trades = trades;
    }

    @Override
    public AnnualizedReturn call() throws Exception {
      List<Candle> candles = stockQuotesService.getStockQuote(trades.getSymbol(), trades.getPurchaseDate(), endDate);
      // if(candles != null){
        Candle tiingoCandle = candles.get(0);
        Candle tiingoCandlelast = candles.get(candles.size()-1);
        AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(tiingoCandlelast.getDate(), trades, tiingoCandle.getOpen(), tiingoCandlelast.getClose());
        return annualizedReturn;
    }
}

}
  


  // ¶TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Modify the function #getStockQuote and start delegating to calls to
  //  stockQuoteService provided via newly added constructor of the class.
  //  You also have a liberty to completely get rid of that function itself, however, make sure
  //  that you do not delete the #getStockQuote function.


