package com.mta.linoy.model;

import java.util.Date;

import com.mta.linoy.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * This class inherited Stock class and also contains stock quantity and recommendation 
 * Author: Linoty Baradon
 */

public class StockStatus extends Stock {

	public  ALGO_RECOMMENDATION recommendation;
	public int stockQuantity;


	//constructor:

	public StockStatus(String stockSymbol1, float ask1, float bid1, Date date1,ALGO_RECOMMENDATION recommendation,int stockQuantity){
		setSymbol(stockSymbol1);
		setAsk(ask1);
		setBid(bid1);
		setDate(date1);
		setRecommendation(recommendation);
		setStockQuantity(stockQuantity);
	}


	// copy constructor:

	public StockStatus(StockStatus stockStatus){
		super (stockStatus.getSymbol(),stockStatus.getAsk(), stockStatus.getBid(),new Date(stockStatus.getDate().getTime()));	
		this.recommendation = stockStatus.recommendation;
		this.stockQuantity = stockStatus.stockQuantity;

	}

	//getters and setters

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
