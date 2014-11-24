package com.mta.linoy;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Mta_linoybaradonServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");

		
	//exercise 2:// 
		
	int num1 = 4 ;
	int num2 = 3 ;
	int num3 = 7 ;	
	
	int result1 = (num1+num2)* num3; 
	
	String resultStr1 = new String ("<h1>Result of ( "+num1+"+"+num2+") * "+num3+" = "+result1+"</h1>");
	
	resp.getWriter().println(resultStr1);
	
	
	
	//exercise 3: //
	
	int radius = 50;
		double area = radius*radius*Math.PI;
		
		String line1 = new String("Calculation 1: Area of circle with radius "+radius+" is: "+area+" square cm");
		
		
		double opposite;
		double hypotenuse = 50;
		double angleB = 30;
		double angleBInRadian = angleB*(Math.PI/180);
		
		opposite = Math.sin(angleBInRadian)*hypotenuse;
		
		String line2 = new String("Calculation 2: Length of opposite where angle B is "+angleB+" degrees and hypotenuse length is "+hypotenuse+" cm is: "+opposite+" ");
		
		
		double exp = 13;
		double base = 20;
		float result;
		
		result = (long) Math.pow(base, exp);
		
		String line3 = new String("Calculation 3: Power of  20 with exp of 13 is "+result+" ");


		
		String resultStr = line1 + "<br>" + line2 + "<br>" +line3;
		
		resp.getWriter().println(resultStr);

	}
}
