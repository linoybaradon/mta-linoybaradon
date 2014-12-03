package com.mta.linoy.model;

import java.util.Date;
/**
 * 
 * @author Linoy Baradon
 * @description this class defines variables and methods like addStock, 
 * it also has an inner class StockStatus.
 */
public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;	
	public String title;
	public int portfolioSize = 0 ;
	public Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	public StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	
	public void addStock (Stock stock){ 		
		stocks[portfolioSize] = stock ;
		portfolioSize++;	
	}
	
	public Stock[] getStocks (){
		return stocks; 
	}
	
	public String getHtmlString(){
		String getHtmlString = "<h1>portfolio title</h1>";
		for (int i=0; i< portfolioSize; i++)
		getHtmlString += stocks[i].stockHtmlDetailsString() +"<br>";
		
		return getHtmlString ;		
	}
	
	public class StockStatus{
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2 ;
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;

	}	
}