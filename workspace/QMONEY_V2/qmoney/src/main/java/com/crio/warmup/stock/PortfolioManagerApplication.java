
package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.crio.warmup.stock.portfolio.PortfolioManagerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {

  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Task:
  //       - Read the json file provided in the argument[0], The file is available in the classpath.
  //       - Go through all of the trades in the given file,
  //       - Prepare the list of all symbols a portfolio has.
  //       - if "trades.json" has trades like
  //         [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  //         Then you should return ["MSFT", "AAPL", "GOOGL"]
  //  Hints:
  //    1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  //       Check if they are of any help to you.
  //    2. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {

    // String filename = args[0];
    // String content =  readfileAsString(filename);
    // ObjectMapper objectMapper = getObjectMapper();
    // trades[] trade = objectMapper.readValue(content, trades[].class);
    // return Stream.of(trade).map(trades::getSymbol).collect(Collectors.toList());
     return Arrays.stream(getObjectMapper().readValue(resolveFileFromResources(args[0]), PortfolioTrade[].class)).map(a->a.getSymbol()).collect(Collectors.toList());
    
  }





  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.





   private static String readfileAsString(String filename) throws UnsupportedEncodingException, IOException, URISyntaxException{
       return new  String(Files.readAllBytes(resolveFileFromResources(filename).toPath()),"UTF-8");         
   }



  // TODO: CRIO_TASK_MODULE_REST_API
  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.

  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "trades.json";
     String toStringOfObjectMapper = "ObjectMapper";
     String functionNameFromTestFileInStackTrace = "mainReadFile";
     String lineNumberFromTestFileInStackTrace = "";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
   ObjectMapper objectMapper = getObjectMapper();
    List<PortfolioTrade> portfolioTrades = Arrays.asList(objectMapper.readValue
    (resolveFileFromResources(args[0]), PortfolioTrade[].class));
    List<TotalReturnsDto> totalReturnsDtos = new ArrayList<>();
      for(PortfolioTrade i:portfolioTrades){
      List<Candle> candles = fetchCandles(i, LocalDate.parse(args[1]), getToken());
        if(candles != null){
          totalReturnsDtos.add(new TotalReturnsDto(i.getSymbol(),candles.get(candles.size()-1).getClose()));
        }
      }
  //  List<TotalReturnsDto> totalReturnsDtos = ReadQuotesHelper(args, portfolioTrades);
    totalReturnsDtos.sort((a,b)-> (a.getClosingPrice().compareTo(b.getClosingPrice())));
    List<String> stocks = new ArrayList<>();
      for(TotalReturnsDto i:totalReturnsDtos){
        stocks.add(i.getSymbol());
      }
      return stocks;
  }
  public static final Comparator<TotalReturnsDto> closingComparator = new Comparator<TotalReturnsDto>() {
     public int compare(TotalReturnsDto t1, TotalReturnsDto t2){
      return (int) (t1.getClosingPrice().compareTo(t2.getClosingPrice()));
     }
  };
  // public static List<TotalReturnsDto> ReadQuotesHelper(String[] args,List<PortfolioTrade> trade) throws IOException, URISyntaxException{
     
  //     List<TotalReturnsDto> list = new ArrayList<>();
  //     for(PortfolioTrade i:trade){
  //       String url = prepareUrl(i, args[1], getToken());
  //       // String url = "https://api.tiingo.com/tiingo/daily/"+i.getSymbol()+
  //       // "/prices?startDate="+i.getPurchaseDate()+"&endDate="+args[1]+"&token="+getToken();
  //     TiingoCandle[] tiingoCandles = restTemplate.getForObject(url, TiingoCandle[].class);
  //       if(tiingoCandles != null){
  //         list.add(new TotalReturnsDto(i.getSymbol(),tiingoCandles[tiingoCandles.length-1].getClose()));
  //       }
  //     }
  //     return list;
  // }
  
  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile
  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    // String content =  readfileAsString(filename);
    // ObjectMapper objectMapper = getObjectMapper();
    // PortfolioTrade[] trade = objectMapper.readValue(content, PortfolioTrade[].class);
    // List<PortfolioTrade> sybl = Stream.of(trade).collect(Collectors.toList());
    // return sybl;

     return Arrays.asList(getObjectMapper().readValue(resolveFileFromResources(filename),PortfolioTrade[].class));
  }
  
  
  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.
  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    return "https://api.tiingo.com/tiingo/daily/"+trade.getSymbol()+"/prices?startDate="+trade.getPurchaseDate()+"&endDate="+endDate+"&token="+token;
  }
  // TODO:
  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest
  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
     return candles.get(0).getOpen();
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
     return candles.get(candles.size()-1).getClose();
  }


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
     String url = prepareUrl(trade,endDate,token);
     Candle[] candle = new RestTemplate().getForObject(url,TiingoCandle[].class);
     return  Arrays.asList(candle);
  }
  public static final Comparator<AnnualizedReturn> annualizedComparator = new Comparator<AnnualizedReturn>() {
    public int compare(AnnualizedReturn t1, AnnualizedReturn t2){
     return (int) (t2.getAnnualizedReturn().compareTo(t1.getAnnualizedReturn()));
    }
 };
  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
        ObjectMapper objectMapper = getObjectMapper();
    List<PortfolioTrade> portfolioTrades = Arrays.asList(objectMapper.readValue
    (resolveFileFromResources(args[0]), PortfolioTrade[].class));
    List<AnnualizedReturn> annualizedReturns = new ArrayList<>();
    for(PortfolioTrade i:portfolioTrades){
     List<Candle> candles = fetchCandles(i, LocalDate.parse(args[1]), getToken());
     if(candles != null){
      AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(candles.get(candles.size()-1).getDate(),i,candles.get(0).getOpen(), candles.get(candles.size()-1).getClose());
       annualizedReturns.add(annualizedReturn);   
    }
    }

     // List<AnnualizedReturn> annualizedReturns = annualizedReturnHelper(args, portfolioTrades);
      annualizedReturns.sort((a,b)-> b.getAnnualizedReturn().compareTo(a.getAnnualizedReturn()));
      return annualizedReturns;
  }

  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        double totalReturn  = (double)(sellPrice - buyPrice) / buyPrice;
        double year=(double)ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate)/365;
        double annualizedReturn = Math.pow(++totalReturn,1/year)-1;

      return new AnnualizedReturn(trade.getSymbol(), annualizedReturn, totalReturn);
  }






















  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.

  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       String file = args[0];
       LocalDate endDate = LocalDate.parse(args[1]);
       String contents = readfileAsString(file);
       ObjectMapper objectMapper = getObjectMapper();
       List<PortfolioTrade> portfolioTrades = Arrays.asList(objectMapper.readValue
       (resolveFileFromResources(args[0]), PortfolioTrade[].class));
       PortfolioManager portfolioManager = new PortfolioManagerImpl();
       return portfolioManager.calculateAnnualizedReturn(portfolioTrades,endDate);
  }


  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());

    printJsonObject(mainReadFile(args));


    printJsonObject(mainReadQuotes(args));



    printJsonObject(mainCalculateSingleReturn(args));
    printJsonObject(mainCalculateReturnsAfterRefactor(args));

  }





  public static String getToken() {
    return "0ebb5832b9e1624cc372710e2355810f10fea3cb";
    
  }
}

