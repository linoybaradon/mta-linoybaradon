package com.mta.linoy;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Mta_linoybaradonServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		int num1;
		int num2;
		int num3;
		num1=4;
		num2=7;
		num3=3;
		int result= (num1+num3)*num2;

		String resultStr = new String("<h1>Result of ("+num1+"+"+num3+")*"+num2+" ="+result+"</h1>");
		
		resp.getWriter().println(resultStr);

	}
}
