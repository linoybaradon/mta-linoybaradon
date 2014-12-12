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


		Portfolio portfolio2 = new Portfolio(); 

		resp.getWriter().println(portfolio.getHtmlString() + "<br>");
		portfolio2.setTitle("<h1>Portfolio 2</h1>");
		resp.getWriter().println(portfolio2.getHtmlString() + "<br>");

		portfolio.removeStock(0);
		resp.getWriter().println("                                                     ");
		resp.getWriter().println(portfolio.getHtmlString() + "<br>");
		resp.getWriter().println(portfolio2.getHtmlString() + "<br>");

		portfolio2.getStocks()[2].setBid(55.55f);
		resp.getWriter().println("                                                     ");
		resp.getWriter().println(portfolio.getHtmlString() + "<br>");
		resp.getWriter().println(portfolio2.getHtmlString() + "<br>");

	}

}

