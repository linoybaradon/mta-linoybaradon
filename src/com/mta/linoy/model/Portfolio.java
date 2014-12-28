package com.mta.linoy.model;

import java.util.Date;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private StockStatus[] arrayOfStocksStatus;
	private int portfolioSize = 0; 
	public enum ALGO_RECOMMENDATION{
		DO_NOTHING,
		BUY ,
		SELL ;	
	}
	private float balance = 0;


	/**
	 * constructor
	 *  portfolio 
	 */

	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		arrayOfStocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}	

	/**
	 * constructor
	 **/               

	public Portfolio(String title1,Stock[] stocks1,StockStatus[] stockStatus1,int portfolioSize1,float balance){

		setTitle(title1);
		setStocks(stocks1);
		setStocksStatus(stockStatus1);
		setPortfolioSize(portfolioSize1);		
		setBalance(balance);
	}

	/**
	 * copy c'tor
	 */

	public Portfolio(Portfolio portfolio){
		this ();
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.arrayOfStocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.balance = portfolio.balance;

		for(int i = 0; i < portfolio.portfolioSize; i++)
		{
			this.stocks[i] = new Stock (portfolio.getStocks()[i]);
			this.arrayOfStocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
		}
	}

	/*
	 * updating the balance by adding positive or negative values
	 */

	public void updateBalance (float amount){

		this.balance += amount;

	}

	/**
	 * Adds stock to the stocks array.
	 * portfolioSize is a counter of stocks in the array.
	 **/

	public void addStock(Stock stock){

		for(int i = 0; i < portfolioSize; i++){
			if(stock.getSymbol().equals(stocks[i].getSymbol()) ){

				System.out.println("The stock exists already");
				return;
			}
		}

		if(portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println ("Sorry can't add a new stock, portfolio can have only" +MAX_PORTFOLIO_SIZE+" stocks");
			return;
		}

		stocks[portfolioSize] = stock;
		arrayOfStocksStatus[portfolioSize]= new StockStatus(stock.getSymbol(),stock.getBid(),stock.getAsk(),stock.getDate());
		portfolioSize++;
	}

	/**
	 * Remove stock
	 * first, sell the stocks
	 * second removes stock from stocks array and reduce portfolio size 
	 **/


	public boolean removeStock (String symbol){

		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)){
				boolean sell = sellStock(symbol,arrayOfStocksStatus[i].getStockQuantity());
				if (sell == true){
					if (i!= portfolioSize){
						stocks[i]= stocks[portfolioSize];
						arrayOfStocksStatus[i] = arrayOfStocksStatus[portfolioSize];
						stocks[portfolioSize] = null;
						arrayOfStocksStatus[portfolioSize] = null;

					}else{
						stocks[i] = null;
						arrayOfStocksStatus[i] = null;
					}
					portfolioSize--;
					return true;
				}
				else{
					System.out.println("The stock can't be removed, the sell didn't success");
					return false;
				}
			}
		}
		System.out.println("The stock is not exists");
		return false;
	}

	/*
	 * sell stock only if the stock exists
	 */
	public boolean sellStock(String symbol, int quantity){

		if (quantity < -1){
			System.out.println("Quantity can't be negative");
			return false;
		}

		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)){
				if (quantity == -1 ){
					updateBalance(arrayOfStocksStatus[i].getStockQuantity()*arrayOfStocksStatus[i].getCurrentBid());
					arrayOfStocksStatus[i].setStockQuantity(0);
					return true;
				}
				else if(quantity!=-1 && arrayOfStocksStatus[i].getStockQuantity() <= quantity){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() - quantity);
					updateBalance((quantity*arrayOfStocksStatus[i].getCurrentBid()));
					return true;
				}
				else{ 
					System.out.println("You don't have enough stocks to sell");
					return false;
				}
			}
		}
		return false;
	}

	/*
	 * buy stock only if it exists and if there is enough balance
	 */
	public boolean buyStock(String symbol,int quantity){

		int availableNumOfQuantity = (int) (balance / quantity) ;

		for (int i = 0; i < portfolioSize; i++) {

			if (stocks[i].getSymbol().equals(symbol)){

				if (quantity!=-1 && (balance-(arrayOfStocksStatus[i].getCurrentAsk() * quantity) >= 0)){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() + quantity);
					updateBalance(-(quantity*arrayOfStocksStatus[i].getCurrentAsk()));
					return true;
				}
				else if(quantity == -1 && availableNumOfQuantity >= 1){	
					updateBalance(-(availableNumOfQuantity * arrayOfStocksStatus[i].getCurrentAsk()));
					arrayOfStocksStatus[i].setStockQuantity(availableNumOfQuantity);
					return true;
				}

				else {
					System.out.println("Not enough balance to complete purchase");
					return false;
				}
			}
		}
		System.out.println("Sorry this stock is not exist in your portfolio");
		return false;
	}

	/*
	 * calculate the the total value of stocks 
	 */
	float getStocksValue (Stock stock[]){

		float stockValue = 0;
		for (int i=0; i < portfolioSize; i++){
			stockValue += arrayOfStocksStatus[i].getCurrentBid() * arrayOfStocksStatus[i].getStockQuantity(); 
		}
		return stockValue;
	}

	/*
	 * calculate total value of stocks and balance
	 */
	float getTotalValue (Stock stock[]){
		float totalValue = 0;
		totalValue = getStocksValue(stocks)+ getBalance();

		return totalValue;
	}


	/**
	 * Returns the description portfolio.
	 * prints stock's name
	 **/

	public String getHtmlString(){
		String getHtmlString = getTitle() + "<br>";

		getHtmlString += "<b> Total value of Portfolio: </b>"+ getTotalValue(stocks)+ "$ , <<b> Total Stocks value: </b>" + getStocksValue(stocks) +"$  , <b> Balance: </b>" + getBalance() + "$ <br><br>";

		for(int i = 0; i < portfolioSize; i++)
			getHtmlString += "<b>Stock</b> " + (i+1) + ": " +stocks[i].stockHtmlDetailsString() +"<br><br>";

		return getHtmlString;
	}



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
		return arrayOfStocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.arrayOfStocksStatus = stocksStatus;
	}


	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * An inner class
	 * @author Linoy Baradon
	 **/

	public class StockStatus {
		public String symbol;
		public float currentAsk;
		public float currentBid;
		public Date date;
		public ALGO_RECOMMENDATION recommendation;
		public int stockQuantity;

		/**
		 *An empty constructor
		 *stockStatus 
		 * @return 
		 **/
		public  StockStatus()
		{
		}

		/**
		 * constructor stockStatus
		 */
		public  StockStatus (String symbol1,float currentBid1,float currentAsk1, Date date1){
			{
				setSymbol(symbol);
				setCurrentBid(currentBid1);
				setCurrentAsk(currentAsk1);
				setDate(date1);
				setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
				setStockQuantity(0);
			}
		}

		/**
		 * copy constructor
		 **/
		public  StockStatus(StockStatus stockStatus)
		{
			if(this.symbol != null)
			{
				symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentBid = stockStatus.currentBid;
				date = new Date (((Date) stockStatus.date).getTime());
				this.recommendation = stockStatus.recommendation;
				this.stockQuantity = stockStatus.stockQuantity;
			}
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

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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

