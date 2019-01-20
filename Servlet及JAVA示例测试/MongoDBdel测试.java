package com.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.domian.ConnectMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.connection.Connection;
import com.sun.net.httpserver.Filter;

public class MongoDBdel extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ConnectMongoDB connectMongoDB = new ConnectMongoDB();
		MongoDatabase mongoDatabase = connectMongoDB.getConnect();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("Zuoye");
		mongoCollection.deleteOne(Filters.eq("deltest", 1));
		out.println("单个删除成功");
		mongoCollection.deleteMany(Filters.eq("deltest", 2));
		out.println("多个删除成功");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

	}

}
