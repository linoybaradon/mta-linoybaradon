package com.mta.linoy.service;

import java.util.Date;

import com.mta.linoy.model.Portfolio;
import com.mta.linoy.model.Stock;

/**
 * 
 * @author Linoy Baradon
 * @description this class defines new Portfolio and sets values into Portfolio.
 * 
 */
public class PortfolioService {

	public Portfolio getPortfolio(){

		Portfolio myPortfolio = new Portfolio();
		Date update = new java.util.Date ();

		

		update.setDate(15);
		update.setMonth(10);
		update.setYear(114);
		update.setHours(0);
		update.setMinutes(0);
		update.setSeconds(0);

		
		Stock option1 = new Stock ("PIH", 12.4f, 13.1f, update);
		Stock option2 = new Stock ("AAL",5.5f,5.78f,update);
		Stock option3 = new Stock ("CAAS",31.5f,31.2f,update);

		myPortfolio.addStock(option1);
		myPortfolio.addStock(option2);
		myPortfolio.addStock(option3);

		myPortfolio.setTitle("<h1>Portfolio 1</h1>");

		return myPortfolio;
	}
}
