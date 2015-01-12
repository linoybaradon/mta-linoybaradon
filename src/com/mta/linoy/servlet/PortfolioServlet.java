package com.mta.linoy.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import com.mta.linoy.model.Portfolio;
import com.mta.linoy.model.Stock;
import com.mta.linoy.service.PortfolioService;

import exception.BalanceException;
import exception.PortfolioFullException;
import exception.StockAlreadyExistsException;
import exception.StockNotExistException;
/**
 * 
 * @author Linoy Baradon
 * @description this class gets Portfolio and print the values.
 */

public class PortfolioServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		resp.setContentType("text/html");

		PortfolioService portfolioService = new PortfolioService(); 
		Portfolio portfolio;

		try {

			portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlString()+ "<br>");
		
		}  catch (Exception e) {
			resp.getWriter().println(e.getMessage());
		}
	}

}

