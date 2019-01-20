package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.domian.Choice;

public class JsonTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//json往前端传值。
	/*	String jsonStr = "{\"success\":false,\"message\":\"账号已被注册\"}";
		out.write(jsonStr);
		out.close();*/	
		
		List<Choice> choices = new ArrayList<Choice>();
		Choice c1 = new Choice();
		c1.setAnwser("A");
		c1.setOptionA("this is A");
		c1.setOptionB("this is B");
		c1.setOptionC("this is C");
		c1.setOptionD("this is D");
		c1.setRightanwser("B");
		c1.setStem("this is test");
		choices.add(c1);
		
		String jsonStr =JSON.toJSONString(choices); //JSON.toJSONString(c1);
		out.printf(jsonStr);
		
		String arryjson = JSON.toJSONString(choices);
		out.println(arryjson);
		
		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(choices);
		jsonArray.toString();
		out.println(jsonArray);
		
		List<Choice> choicesmysql = (List<Choice>) request.getSession().getAttribute("choices");
		String jsonchoices = JSON.toJSONString(choicesmysql);
		String jsonchoicesS = "{\"homework\""+":"+jsonchoices+"}";
		out.println(jsonchoicesS);
		//request.getSession().setAttribute("JsonTest", jsonArray);
		//request.getSession().setAttribute("JsonTest", jsonStr);
		request.getSession().setAttribute("JsonTest", jsonchoicesS);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
