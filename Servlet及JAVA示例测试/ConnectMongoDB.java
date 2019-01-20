package com.domian;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;



public class ConnectMongoDB {
	
	
	public MongoDatabase getConnect(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("Zuoye");
		return mongoDatabase; 
		
	}
}
