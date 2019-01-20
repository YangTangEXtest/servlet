package com.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.bson.Document;

import com.alibaba.fastjson.JSONObject;

public class GetHomework extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
//		1.请求作业内容：
//		(1).发送的数据：
//		jsonStr = {
//	                "username":"牛叉叉的秦神",
//	                "userID":"2018120264",
//	                "homeworkID":9
//	    };
		
		//获取前端发过来的Json值数。
		String accept = "";
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while((temp = br.readLine())!=null)
		{
			sb.append(temp);
		}
		br.close();
		//accept = sb.toString();
		accept = "111";
		//if(accept!="")
		//{
			//net.sf.json.JSONObject acceptjson = net.sf.json.JSONObject.fromObject(accept);
			
			//读取前端发过来的请求
			//String reqString = acceptjson.getString("homeworkID");
			//out.print(reqString);
			//int index = Integer.parseInt(reqString);
			
			//测试数据
			int index = 1;
			ArrayList<Document> homeworks = (ArrayList<Document>) request.getSession().getAttribute("documents");
			Document document = homeworks.get(index);
			out.print("-----------------------------------------------------------");
			out.print(document);
			out.print("-----------------------------------------------------------");
			out.print(document.toJson());
			net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(document.toJson());
			String hwString = obj.getString("homework");
			JSONArray hwArray = JSONArray.fromObject(hwString);
			StringBuffer typeBuffer = new StringBuffer();
			
			for(int i=0;i<hwArray.size();i++)
			{
				String get = hwArray.getString(i);
				net.sf.json.JSONObject topic = net.sf.json.JSONObject.fromObject(get);
				String type = topic.getString("type");
				typeBuffer.append(type);
				out.print(typeBuffer);
			}
			obj.put("typeString", typeBuffer.toString());
			out.print("-----------------------------------------------------------");
			out.print(obj);
		//}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
