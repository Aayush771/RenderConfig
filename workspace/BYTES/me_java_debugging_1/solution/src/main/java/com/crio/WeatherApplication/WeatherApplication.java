package com.crio.WeatherApplication;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherApplication {

  Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

  // WeatherApplication prints the city name and
  // whether rain is expected in the next few days or not.
  // Input: city prefix from command line argument
  // Output: Multiple lines of <City Name, Rain Status>

  // Sample run
  // ./gradlew bootrun -q -Pargs="Ban"
  // Sample output
  //   Brisbane : Won't Rain
  //   Bangkok : Will Rain
  //   Bangalore : Will Rain
  //   Durban : Will Rain
  public static void main(String[] args) throws IOException {
    ForecastFetcher forecastFetcher = new ForecastFetcher();
    ForecastParser forecastParser = new ForecastParser();

    String cityWoeIds = forecastFetcher.fetchWoeIds(args[0]);
    System.out.println(cityWoeIds);
  }
}
