package com.example.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {

   
  public void parseJsonManually(File file) {

  }

  public void parseJsonJacksomatically(File inputFile, File outputFile) throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    TradePrivate[] trades = objectMapper.readValue(inputFile, TradePrivate[].class);
    System.out.println("Deserialized(read) successfully");

    for (TradePrivate trade : trades) {
      System.out.println(trade);
    }

    // Uncomment these past Milestone 1 to serialize the JSON read
    // Serialize back the Java Trade objects to a file
    objectMapper.writeValue(outputFile, trades);
     System.out.println("Serialized(wrote) successfully");
  }

  // Milestone 1
  public void writeImportantPurchasesToFile(int threshold, File inputFile, File outputFile) 
      throws JsonParseException, JsonMappingException, IOException {
    List<Trade> impTrades = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    Trade[] trades = objectMapper.readValue(inputFile, Trade[].class);
    
    for (Trade trade : trades) {
      if (trade.quantity > threshold) {
        impTrades.add(trade);
      }
      System.out.println(trade);
    }

    // TODO: Add method arguments
    // objectMapper.writeValue(, );
  }

  // Milestone 4
  public void parseJsonJacksomaticallyPrivate(File inputFile, File outputFile) 
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    TradePrivate[] trades = objectMapper.readValue(inputFile, TradePrivate[].class);
    System.out.println("Deserialized(read) successfully");
    
    for (TradePrivate trade : trades) {
      System.out.println(trade);
    }

    objectMapper.writeValue(outputFile, trades);
    System.out.println("Serialized(wrote) successfully");
  }

  public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    Main main = new Main();

    // Input JSON files to be deserialized
    File trades = new File("src/main/java/com/example/jackson/trades.json");
    File tradesFancy = new File("src/main/java/com/example/jackson/tradesFancy.json");
    File tradesFancier = new File("src/main/java/com/example/jackson/tradesFancier.json");

    // Serialized output JSON files
    File impTrades = new File("src/main/java/com/example/jackson/impTrades.json");
    File outputFile = new File("src/main/java/com/example/jackson/readOutput.json");

    // uncomment below line to run your manual json parsing logic
     //main.parseJsonManually(trades);

    /* 
     uncomment below line to use Jackson
     use json input files as said in instructions
     Files - trades/tradesFancy/tradesFancier
    */
     main.parseJsonJacksomatically(tradesFancier, outputFile);

    // uncomment this to execute writeImportantPurchasesToFile()
    // main.writeImportantPurchasesToFile(trades, impTrades);

    // Uncomment in Milestone 4
    //main.parseJsonJacksomaticallyPrivate(tradesFancy, outputFile);

    System.out.println("Running completed");
  }
}
