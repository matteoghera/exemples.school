package com.examples.school.mongo.helper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoTestHelper {

	private DBCollection students;

	public MongoTestHelper(MongoClient mongoClient){
		DB db = mongoClient.getDB("school");
		db.getCollection("student").drop();
		
		students = db.getCollection("student");
	}
	
	public void addStudent(String id, String name) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		students.insert(document);
	}
}

