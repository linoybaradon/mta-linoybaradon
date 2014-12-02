package com.mta.linoy;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.linoy.model.Stock;

public class ClassEx4Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		resp.setContentType("text/html");

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

		Stock option2 = new Stock();
		option2.setSymbol("AAL");
		option2.setAsk(5.5f);
		option2.setBid(5.78f);
		option2.setDate(update);

		Stock option3 = new Stock();
		option3.setSymbol("CAAS");
		option3.setAsk(31.5f);
		option3.setBid(31.2f);
		option3.setDate(update);

		resp.getWriter().println("The Stock is:<hr>");
		resp.getWriter().println(" "+option1.stockHtmlDetailsString()+" <br>");
		resp.getWriter().println(" "+option2.stockHtmlDetailsString()+" <br>");
		resp.getWriter().println(" "+option3.stockHtmlDetailsString()+" <br>");
	}
}