package com.mta.linoy.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.mta.linoy.model.Portfolio;
import com.mta.linoy.model.Stock;
import com.mta.linoy.service.PortfolioService;
/**
 * 
 * @author Linoy Baradon
 * @description this class gets Portfolio and print the values.
 */

public class PortfolioServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		resp.setContentType("text/html");

		PortfolioService portfolioService = new PortfolioService(); 
		Portfolio portfolio = portfolioService.getPortfolio(); 
		Stock[] stocks = portfolio.getStocks();
		
		resp.getWriter().println(portfolio.getHtmlString() + "<br>");
		
	}
	
}
