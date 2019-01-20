package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.domian.ConnectMongoDB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBget extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ConnectMongoDB connmgdb = new ConnectMongoDB();
		MongoDatabase mgdb = connmgdb.getConnect();
		out.println("连接mongoDB成功");
		MongoCollection<Document> mongoCollection = mgdb.getCollection("Zuoye");
		out.println("集合\"Zuoye\"选择成功!");
		
		FindIterable<Document> findIterable = mongoCollection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		int i = 0;
		ArrayList<Document> documents = new ArrayList<Document>();
		while(mongoCursor.hasNext())
		{	
			//Document document = Document.parse(mongoCursor.next());
			Document getJson = mongoCursor.next();
			documents.add(getJson);
			//i++;
			out.println(getJson);
		}
		request.getSession().setAttribute("documents", documents);
		out.print("*************************************************/n");
		out.print("成功存入session中");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
