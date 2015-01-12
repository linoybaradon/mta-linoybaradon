package com.mta.linoy.model;

import exception.BalanceException;
import exception.PortfolioFullException;
import exception.StockAlreadyExistsException;
import exception.StockNotExistException;


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
	 * @throws StockAlreadyExistsExeption
	 * @throws PortfolioFullException 
	 **/

	public void addStock(Stock stock)throws StockAlreadyExistsException, PortfolioFullException{

		for(int i = 0; i < MAX_PORTFOLIO_SIZE; i++){
			StockStatus stockStatus = arrayOfStocksStatus[i];
			if(stockStatus == null) continue;
			
			if(stock.getSymbol().equals(stockStatus.getSymbol())){
				throw new StockAlreadyExistsException(stockStatus.getSymbol());
			}			
		}

		if(portfolioSize >= MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}

		arrayOfStocksStatus[portfolioSize]= new StockStatus(stock.getSymbol(),stock.getAsk(),stock.getBid(),stock.getDate(), ALGO_RECOMMENDATION.DO_NOTHING, 0);
		portfolioSize++;
	}


	/**
	 * Remove stock
	 * first, sell the stocks
	   second removes stock from stockstatus array and reduce portfolio size 
	 * @throws StockNotExistException 
	 **/

	public void removeStock (String symbol) throws StockNotExistException{

		for (int i = 0; i < portfolioSize; i++) {
			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){
				sellStock(symbol,arrayOfStocksStatus[i].getStockQuantity());

				if (i!= portfolioSize){

					arrayOfStocksStatus[i] = arrayOfStocksStatus[portfolioSize];

					arrayOfStocksStatus[portfolioSize] = null;

				}
				else{

					arrayOfStocksStatus[i] = null;
				}
				portfolioSize--;
				System.out.println("The stock " +arrayOfStocksStatus[i].getSymbol()+ " was removed sucessfully");
			}
			else{
				System.out.println("The stock can't be removed, the sell didn't success");

			}

		}

		throw new StockNotExistException();
	}


	/**
	 * sell stock only if the stock exists
	 */

	public void sellStock(String symbol, int quantity){

		if (quantity < -1){
			System.out.println("Quantity can't be negative");

		}

		for (int i = 0; i < portfolioSize; i++) {
			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){
				if (quantity == -1 ){
					updateBalance(arrayOfStocksStatus[i].getStockQuantity()*arrayOfStocksStatus[i].getBid());
					System.out.println(arrayOfStocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were sold");
					arrayOfStocksStatus[i].setStockQuantity(0);

				}
				else if(quantity!=-1 && arrayOfStocksStatus[i].getStockQuantity() <= quantity){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() - (quantity));
					updateBalance((quantity*arrayOfStocksStatus[i].getBid()));
					System.out.println(quantity + " Stocks of " +symbol+ " were sold");
				}
				else{ 
					System.out.println("You don't have enough stocks to sell");

				}
			}
		}

	}


	/**
	 * buy stock only if it exists and only if there is enough balance
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 */

	public void buyStock(String symbol,int quantity) throws BalanceException, StockNotExistException{

		int availableNumOfQuantity = (int) (balance / quantity) ;

		for (int i = 0; i < portfolioSize; i++) {

			if (arrayOfStocksStatus[i].getSymbol().equals(symbol)){

				if (quantity!=-1 && (balance-(arrayOfStocksStatus[i].getAsk() * quantity) >= 0)){
					arrayOfStocksStatus[i].setStockQuantity(arrayOfStocksStatus[i].getStockQuantity() + quantity);
					updateBalance(-(quantity*arrayOfStocksStatus[i].getAsk()));
					System.out.println(this.arrayOfStocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought");

				}
				else if(quantity == -1 && availableNumOfQuantity >= 1){	
					updateBalance(-(availableNumOfQuantity * arrayOfStocksStatus[i].getAsk()));
					arrayOfStocksStatus[i].setStockQuantity(availableNumOfQuantity);
					System.out.println(this.arrayOfStocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought");

				}

				else {
					float purchaseAmount = quantity*arrayOfStocksStatus[i].getAsk();
					throw new BalanceException(getBalance(),purchaseAmount); 
				}
			}
		}
		throw new StockNotExistException();
		//System.out.println("Sorry this stock is not exist in your portfolio");

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

	public void setBalance(float balance) {
		this.balance = balance;
	}

}

