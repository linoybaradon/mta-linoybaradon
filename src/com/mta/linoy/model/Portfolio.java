package com.mta.linoy.model;

/**
 * This class represent portfolio of srocks
   it contains methods that performs various 
   operations on stocks 
   @author : Linoy Baradon
 */

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	public StockStatus[] arrayOfStocksStatus;
	private int portfolioSize = 0; 
	public enum ALGO_RECOMMENDATION{
		DO_NOTHING,
		BUY ,
		SELL ;	
	}
	private float balance = 0;


	/**
	 *  an empty constructor
	 *  Portfolio 
	 */

	public Portfolio(){
		arrayOfStocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}	


	/**
	 * Constructor
	 **/               

	public Portfolio(String title1,Stock[] stocks1,StockStatus[] stockStatus1,int portfolioSize1,float balance){

		setTitle(title1);
		setStocksStatus(stockStatus1);
		setPortfolioSize(portfolioSize1);		
		setBalance(balance);
	}


	/**
	 * Copy c'tor
	 */

	public Portfolio(Portfolio portfolio){
		this ();
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.getPortfolioSize();
		this.arrayOfStocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.balance = portfolio.balance;

		for(int i = 0; i < portfolio.portfolioSize; i++)
		{

			this.arrayOfStocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
		}
	}


	/**
	 * Updating the balance by adding positive or negative values
	 */

	public void updateBalance (float amount){

		this.balance += amount;

	}


	/**
	 * Adds stock to the stockstatus array,
	   portfolioSize is a counter of stocks in the array.
	 **/

	public void addStock(Stock stock){

		for(int i = 0; i < portfolioSize; i++){
			if(stock.getSymbol().equals(arrayOfStocksStatus[i].getSymbol()) ){

				System.out.println("The stock exists already");
				return;
			}
		}

		if(portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println ("Sorry can't add a new stock, portfolio can have only" +MAX_PORTFOLIO_SIZE+" stocks");
			return;
		}

		arrayOfStocksStatus[portfolioSize]= new StockStatus(stock.getSymbol(),stock.getAsk(),stock.getBid(),stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		portfolioSize++;
	}


	/**
	 * Remove stock
	 * first, sell the stocks
	   second removes stock from stockstatus array and reduce portfolio size 
	 **/

	public boolean removeStock (String symbol){

		for (int i = 0; i < portfolioSize; i++) {
			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){
				boolean sell = sellStock(symbol,arrayOfStocksStatus[i].getStockQuantity());
				if (sell == true){
					if (i!= portfolioSize){

						arrayOfStocksStatus[i] = arrayOfStocksStatus[portfolioSize];

						arrayOfStocksStatus[portfolioSize] = null;

					}else{

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


	/**
	 * sell stock only if the stock exists
	 */

	public boolean sellStock(String symbol, int quantity){

		if (quantity < -1){
			System.out.println("Quantity can't be negative");
			return false;
		}

		for (int i = 0; i < portfolioSize; i++) {
			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){
				if (quantity == -1 ){
					updateBalance(arrayOfStocksStatus[i].getStockQuantity()*arrayOfStocksStatus[i].getBid());
					arrayOfStocksStatus[i].setStockQuantity(0);
					return true;
				}
				else if(quantity!=-1 && arrayOfStocksStatus[i].getStockQuantity() <= quantity){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() - (quantity));
					updateBalance((quantity*arrayOfStocksStatus[i].getBid()));
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


	/**
	 * buy stock only if it exists and only if there is enough balance
	 */

	public boolean buyStock(String symbol,int quantity){

		int availableNumOfQuantity = (int) (balance / quantity) ;

		for (int i = 0; i < portfolioSize; i++) {

			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){

				if (quantity!=-1 && (balance-(arrayOfStocksStatus[i].getAsk() * quantity) >= 0)){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() + quantity);
					updateBalance(-(quantity*arrayOfStocksStatus[i].getAsk()));
					return true;
				}
				else if(quantity == -1 && availableNumOfQuantity >= 1){	
					updateBalance(-(availableNumOfQuantity * arrayOfStocksStatus[i].getAsk()));
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


	/**
	 * calculate the the total value of stocks 
	 */

	float getStocksValue (Stock stock[]){

		float stockValue = 0;
		for (int i=0; i < portfolioSize; i++){
			stockValue += arrayOfStocksStatus[i].getBid() * arrayOfStocksStatus[i].getStockQuantity(); 
		}
		return stockValue;
	}


	/**
	 * calculate total value of stocks and balance
	 */

	float getTotalValue (Stock stock[]){
		float totalValue = 0;
		totalValue = getStocksValue(arrayOfStocksStatus)+ getBalance();

		return totalValue;
	}


	/**
	 * Returns the description portfolio.
	 * prints stock's name
	 **/

	public String getHtmlString(){
		String getHtmlString = getTitle() + "<br>";

		getHtmlString += "<b> Total value of Portfolio: </b>"+ getTotalValue(arrayOfStocksStatus)+ "$ , <<b> Total Stocks value: </b>" + getStocksValue(arrayOfStocksStatus) +"$  , <b> Balance: </b>" + getBalance() + "$ <br><br>";

		for(int i = 0; i < portfolioSize; i++)
			getHtmlString += "<b>Stock</b> " + (i+1) + ": " +arrayOfStocksStatus[i].stockHtmlDetailsString() +"<br><br>";

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
	public StockStatus[] getStocksStatus() {
		return arrayOfStocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.arrayOfStocksStatus = stocksStatus;
	}


	public float getBalance() {
		return balance;
	}



}

