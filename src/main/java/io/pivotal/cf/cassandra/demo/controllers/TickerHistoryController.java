package io.pivotal.cf.cassandra.demo.controllers;


import io.pivotal.cf.cassandra.demo.models.TickerHistory;
import io.pivotal.cf.cassandra.demo.repositories.TickerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import io.pivotal.cf.cassandra.demo.controllers.TickerData;


@Controller
public class TickerHistoryController {

    private TickerHistoryRepository tickerHistoryRepository;

    @Autowired
    public TickerHistoryController(TickerHistoryRepository tickerHistoryRepository) {
        this.tickerHistoryRepository = tickerHistoryRepository;
    }


    @RequestMapping("/index")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    /* Start */
    @RequestMapping("/submitTicker")
    public String submitTicker(@RequestParam(value="ticker", required=false, defaultValue="EMC") String ticker, Model model) {
        model.addAttribute("ticker", ticker);

        String url;
        String configType="growth";
        //String ticker="jpm";
        RestTemplate restTemplate = new RestTemplate();



        url = "https://analyze-stock.appspot.com/_ah/api/stockAnalyzer/v1/stockAnalyzer?configType="
                  + configType + "&ticker=" + ticker;
        TickerData td = restTemplate.getForObject(
                        url
          //            "https://analyze-stock.appspot.com/_ah/api/stockAnalyzer/v1/stockAnalyzer?configType=growth&ticker=emc"
                        , TickerData.class);
        System.out.println("*** TickerData for " + td.getTickerName() + " at  " + td.getPrice()
                          + ",yield=" + td.getDivYield()
                          + ",pe=" + td.getPe()
                          + ",isPeOk=" + td.getIsPeOk()
                          +",qRevGrowth= "+ td.getQRevGrowth()
                          + ", recommendation=" + td.getRecommendation()  );

        model.addAttribute("tickerName", td.getTickerName());
        model.addAttribute("price", td.getPrice());
        model.addAttribute("yield", td.getDivYield());
        model.addAttribute("pe", td.getPe());
        model.addAttribute("isPeOk", td.getIsPeOk());
        model.addAttribute("recommendation", td.getRecommendation());

        
        String id = UUID.randomUUID().toString();
        tickerHistoryRepository.save(new TickerHistory( ticker, id,  td.getTickerName()
                                                              , td.getPrice(),  td.getPe(), td.getRecommendation(), td.getDivYield() )) ;



        /*
        System.out.println("tickers found with findById():");
        System.out.println("-------------------------------");
        try {
                        TickerHistory tickerHistory = tickerHistoryRepository.findById(id) ;
                        System.out.println("Recommendation for  " + tickerHistory.getName() + ", @ " + tickerHistory.getPrice() + ", is " +  tickerHistory.getRecommendation());

                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        */



        return "results";
    }



    @RequestMapping("/history")
    public String history(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        System.out.println("tickers found with findAll():");
        System.out.println("-------------------------------");

        try {
                        model.addAttribute("tickerHistoryList", tickerHistoryRepository.findAll());
                        for  ( TickerHistory tickerHistory : tickerHistoryRepository.findAll() )
                        {
                          System.out.println("history: Recommendation for  " + tickerHistory.getName() + ", @ " + tickerHistory.getPrice() + ", is " +  tickerHistory.getRecommendation());

                        }
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        return "historyresults";
    }
   
    @RequestMapping("/filterHistory")
    public String filterHistory(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "underConstruction";
    }


}
