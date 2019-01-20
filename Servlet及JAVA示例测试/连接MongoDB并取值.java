package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.bson.Document;

import com.domian.Choice;
import com.domian.ConnectMongoDB;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;


public class MonogoDBconn extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ConnectMongoDB connmgdb = new ConnectMongoDB();
		MongoDatabase mgdb = connmgdb.getConnect();
		out.println("连接mongoDB成功");
		MongoCollection<Document> collection = mgdb.getCollection("Zuoye");
		String json =  (String) request.getSession().getAttribute("jsonStr");
		out.println(json);
/*		DBObject dbObject = (DBObject) JSON.parse(json);*/
		if(json!=null)
		{
			Document document = Document.parse(json);
			collection.insertOne(document);
			out.println("插入成功！");
			out.println("插入的文件流为"+json);
		}

		String jsonMql = (String) request.getSession().getAttribute("JsonTest");
	    
		if(jsonMql!=null)
		{
	        Document documentMql = Document.parse(jsonMql);
	        collection.insertOne(documentMql);
	        out.println("插入成功！");
	        out.println("插入的文件流为"+jsonMql);
	        }
        
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
