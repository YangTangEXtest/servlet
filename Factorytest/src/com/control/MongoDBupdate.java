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

public class MongoDBupdate extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ConnectMongoDB connectMongoDB = new ConnectMongoDB();
		MongoDatabase mongoDatabase = connectMongoDB.getConnect();
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("Zuoye");
		
		mongoCollection.updateOne(Filters.eq("deltest", 1), new Document("$set",new Document("deltest",66)));
		out.println("更新单个完成");
		mongoCollection.updateMany(Filters.eq("deltest", 3), new Document("$set",new Document("deltest",999)));
		out.println("更新多个完成");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
