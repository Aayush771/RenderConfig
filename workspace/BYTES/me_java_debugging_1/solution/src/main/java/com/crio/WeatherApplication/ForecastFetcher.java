package com.crio.WeatherApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ForecastFetcher {

  public String fetchWoeIds(String cityPrefix) throws IOException {
    // create url
    String tokenId="1f381c8b0af1955d40ae9971ccdbb09b";      
    URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q="+cityPrefix+"&appid="+tokenId);

    // Send Get request and fetch data
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    BufferedReader br = new BufferedReader(new InputStreamReader(
        (conn.getInputStream())));

    // Read data line-by-line from buffer & print it out
    String output;
    StringBuilder jsonResponse = new StringBuilder();
    while ((output = br.readLine()) != null) {
      jsonResponse.append(output);
    }

    conn.disconnect();
    return jsonResponse.toString();
  }

  private ArrayList<CityWoeId> getWoeIdsFromJson(String jsonString) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    CityDetail[] citiesDetail = mapper.readValue(jsonString, CityDetail[].class);
    ArrayList<CityWoeId> cityWoeIds = new ArrayList<>();
    for (CityDetail cityDetail : citiesDetail) {
      cityWoeIds.add(new CityWoeId(cityDetail.title, cityDetail.woeid));
    }
    return cityWoeIds;
  }
}
