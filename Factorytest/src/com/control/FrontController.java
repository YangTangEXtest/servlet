package com.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.omg.CORBA.Request;

public class FrontController extends HttpServlet {


	public void service(HttpServletRequest request,HttpServletResponse response){
		
		String type = request.getParameter("type");
		if(type.endsWith("Zuoyefabu")){
			try {
				response.sendRedirect("/fabuzuoye");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(type.endsWith("Test")){
			try {
				response.sendRedirect("/test");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	

	

}
