package com.mta.linoy.model;

import java.util.Date;

public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date date;


	//getters and setters:

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	/**empty c'tor
	 */

	public Stock(){}

	/**
	 * A copy constructor (Stock)
	 */
	public Stock (Stock stock){

		setSymbol(stock.getSymbol());
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		date = new java.util.Date(stock.date.getTime());

	}

	//constructor:

	public Stock(String stockSymbol1, float ask1, float bid1, Date date1) {

		{
			setSymbol(stockSymbol1);
			setAsk(ask1);
			setBid(bid1);
			setDate(date1);
		}
	}


	//print stock's name

	public String stockHtmlDetailsString() {

		String stockHtmlDetailsString = " <b>Stock symbol</b>:  "+getSymbol()+", <b>Ask</b>:  "+getAsk()+", <b>Bid</b>:  "+getBid()+",  <b>Date</b>:  "  + date;

		return stockHtmlDetailsString;
	}	


}
