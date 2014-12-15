package com.mta.linoy.model;

import java.util.Date;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize = 0; 


	//getters and setters: 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize(){
		return portfolioSize = 0;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	public Stock[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	/**
	 * constructor
	 *  portfolio 
	 */

	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus=new StockStatus[MAX_PORTFOLIO_SIZE];
	}	

	/**
	 * copy c'tor
	 */

	public Portfolio(Portfolio portfolio){
		this();
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();

		for(int i = 0; i<portfolio.portfolioSize; i++){

			stocks[i] = new Stock (portfolio.getStocks()[i]);
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
		}
	}

	/**
	 * constructor
	 **/               

	public Portfolio(String title1,Stock[] stocks1,StockStatus[] stockStatus1,int portfolioSize1){

		setTitle(title1);
		setStocks(stocks1);
		setStocksStatus(stockStatus1);
		setPortfolioSize(portfolioSize1);
	}

	/**
	 * Adds stock to the stocks array.
	 * portfolioSize is a counter of stocks in the array.
	 **/

	public void addStock(Stock stock){
		if(portfolioSize < MAX_PORTFOLIO_SIZE)
		{
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
	}

	/**
	 * Remove stock 
	 **/

	public void removeStock(int ind){
		if(ind == portfolioSize)
			this.portfolioSize--;
		else 
		{
			this.portfolioSize--;
			for(int i = ind; i <= portfolioSize-1; i++)
			{
				this.stocks[i] = this.stocks[i+1];
			}
		}
	}	

	/**
	 * Returns the description portfolio.
	 * prints stock's name
	 **/


	public String getHtmlString(){
		String getHtmlString = getTitle() + "<br>";

		for(int i = 0; i < portfolioSize; i++)
			getHtmlString += "<b>Stock</b> " + (i+1) + ": " +stocks[i].stockHtmlDetailsString() +"<br><br>";

		return getHtmlString;
	}


	/**
	 * An inner class
	 * @author Linoy Baradon
	 **/

	public class StockStatus {
		public final static int DO_NOTHING = 0;
		public final static int BUY = 1;
		public final static int SELL = 2;
		private String symbol;
		private float currentBid , currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;


		/**
		 *An empty constructor
		 *stockStatus 
		 **/
		public StockStatus(){
		}

		
		/**
		 * copy constructor
		 **/

		public StockStatus(StockStatus stockStatus){
			{
				if(this.symbol != null){
					symbol = stockStatus.symbol;
					this.currentAsk = stockStatus.currentAsk;
					this.currentAsk = stockStatus.currentBid;
					date = new Date (stockStatus.date.getTime());
					this.recommendation = stockStatus.recommendation;
					this.stockQuantity = stockStatus.stockQuantity;

				}
			}
		}

		
		
		
		/**
		 * constructor stockStatus
		 */

		public StockStatus (String symbol1,float currentBid1,float currentAsk1, Date date1, int recommendation1, int stockQuantity1 ){

			setSymbol(symbol);
			setCurrentBid(currentBid1);
			setCurrentAsk(currentAsk1);
			setDate(date1);
			setRecommendation(recommendation1);
			setStockQuantity(stockQuantity1);

		}


		//getters and setters : 

		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

	}
}

