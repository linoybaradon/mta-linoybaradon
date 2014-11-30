package com.mta.linoy;

public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date date;
	
	
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

	
	public String stockHtmlDetailsString() {
		 
		String stockHtmlDetailsString = "<b>Stock symbol</b>: "+getSymbol()+ ",<b>Ask</b>: "+getAsk()+ " ,<b>Bid</b>: "+getBid()+
				                         "<b>Date</b>: " + date;
	
		return stockHtmlDetailsString;
	}	
	
	
	
}
