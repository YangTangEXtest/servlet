package com.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class test extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String acceptjson = "";
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while((temp = br.readLine())!=null)
		{
			sb.append(temp);
		}
		br.close();
		acceptjson = sb.toString();
		if(acceptjson!=""){
			
			JSONObject json = JSONObject.fromObject(acceptjson);
			json.put("status","200");
			json.put("reason","成功发布");
			out.println(json);
			request.getSession().setAttribute("jsonStr", acceptjson);
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doGet(request, response);
		

	}

}
