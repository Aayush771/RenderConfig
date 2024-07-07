
package com.example.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trade {
  
 // @JsonProperty("1. symbol")
  public String symbol;
 // @JsonProperty("2. quantity")
  public int quantity;

 // @JsonProperty("3. purchaseDate")
  public String purchaseDate;

  
  public Trade() {
  }
  
  public Trade(String symbol, int quantity, String purchaseDate) {
    this.symbol = symbol;
    this.quantity = quantity;
    this.purchaseDate = purchaseDate;
  }
   
public String getSymbol() {

  return symbol;

}


public void setSymbol(String symbol) {

  this.symbol = symbol;

}

  @Override
  public String toString() {
    return "Trade [purchaseDate=" + purchaseDate + ", quantity=" + quantity + ", symbol=" + symbol + "]";
  }
}
