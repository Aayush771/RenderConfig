package com.crio.warmup.stock.dto;

public class trades {
    private String symbol;
    private Integer quantity;
    private String tradeType;
    private String purchaseDate;
    
    public trades() {}
    public trades(String symbol, Integer quantity, String tradeType, String purchaseDate) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.purchaseDate = purchaseDate;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getTradeType() {
        return tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    public String getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
}
