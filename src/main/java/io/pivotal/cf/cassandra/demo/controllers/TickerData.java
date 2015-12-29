package io.pivotal.cf.cassandra.demo.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerData {

    private String ticker;
    private String tickerName;
    private String price;
    private String recommendation;
    private String pe;
    private String qRevGrowth;
    private String divYield;
    private String isPeOk;
        
    public String getTicker() {
        return ticker;
    }
    
    public String getPrice() {
        return price;
    }
    
    /*
     * {
 "divYield": "1.80",
 "pegRatio": 1.11,
 "optimalDebtToEquity": 20.0,
 "qRevGrowth": "5.50",
 "isPeOk": true,
 "optimalPeRatio": 12.0,
 "debtToEquity": "23.36",
 "price": "26.05",
 "recommendation": "SELL",
 "isDebtOk": false,
 "optimalQRevGrowth": 10.0,
 "optimalYield": 0.0,
 "optimalBeta": 1.0,
 "optimalPegRatio": 1.0,
 "pe": 11.95,
 "isPegOk": false,
 "ticker": "emc",
 "isDivYieldOk": false,
 "isQRevGrowthOk": false,
 "tickerName": "EMC Corporation (EMC)",
 "kind": "stockAnalyzer#stockAnalyzerItem",
 "etag": "\"o6177jXhFSxesHm2A3gnb3ZOsbo/yr3M_CAOLru5C9XhFO8vEAUc4yo\""
}
     */
    public String getTickerName() {
        return tickerName;
    }
    
    public String getRecommendation() {
        return recommendation;
    }
    
    public String getPe() {
        return pe;
    }
    
    public String getQRevGrowth() {
        return qRevGrowth;
    }
    
    public String getDivYield() {
        return divYield;
    }
    
    public String getIsPeOk() {
        return isPeOk;
    }
    
    
      
}
