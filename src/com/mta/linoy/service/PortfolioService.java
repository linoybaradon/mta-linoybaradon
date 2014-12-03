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

	Portfolio myPortfolio = new Portfolio();
	
	public Portfolio  getPortfolio() {
		
		
		
		Date update = new java.util.Date(); 	

		update.setDate(15);
		update.setMonth(10);
		update.setYear(114);
		update.setHours(0);
		update.setMinutes(0);
		update.setSeconds(0);

		Stock option1 = new Stock();
		option1.setSymbol("PIH");
		option1.setAsk(12.4f);
		option1.setBid(13.1f);
		option1.setDate(update);
		myPortfolio.addStock(option1);
		
		Stock option2 = new Stock();
		option2.setSymbol("AAL");
		option2.setAsk(5.5f);
		option2.setBid(5.78f);
		option2.setDate(update);
		myPortfolio.addStock(option2);
		
		Stock option3 = new Stock();
		option3.setSymbol("CAAS");
		option3.setAsk(31.5f);
		option3.setBid(31.2f);
		option3.setDate(update);
		myPortfolio.addStock(option3);
		
		return myPortfolio;
		
		
	}
}
