package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mongodb.client.MongoCursor;

import net.sf.json.JSONObject;

public class fabuzuoye extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<Document> documents = (ArrayList<Document>) request.getSession().getAttribute("documents");
		Document document = documents.get(0);
		JSONObject jsonObject = JSONObject.fromObject(document);
		String jsonString = jsonObject.getString("homework");
		net.sf.json.JSONArray homework = net.sf.json.JSONArray.fromObject(jsonString);
		out.print(homework);
		out.print(homework.get(0));
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<homework.size();i++)
		{
			String topicString = homework.get(i).toString();
			out.print(topicString);
			JSONObject topic = JSONObject.fromObject(topicString);
			String type =  topic.getString("type");
			out.print(type);
			sb.append(type);
		}
		out.print(sb);
		//homework.getString("");
		jsonObject.put("anwser", 1);
		out.print(documents.get(0));
		out.print(jsonObject);
		
//		for(int i = 0;i<documents.size();i++)
//		{
//			out.print(documents.get(i));
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	}

}
