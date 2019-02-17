package com.example.stockapisearch;

public class IexTradingStocks {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String iexRealTimePrice;

    public IexTradingStocks(String symbol, String companyName, String primaryExchange, String iexRealTimePrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.primaryExchange = primaryExchange;
        this.iexRealTimePrice = iexRealTimePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getIexRealTimePrice() {
        return iexRealTimePrice;
    }

    public void setIexRealTimePrice(String iexRealTimePrice) {
        this.iexRealTimePrice = iexRealTimePrice;
    }
}
