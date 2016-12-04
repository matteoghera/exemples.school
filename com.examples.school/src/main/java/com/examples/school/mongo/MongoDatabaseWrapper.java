package com.examples.school.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapper {
	private DBCollection students;

	public MongoDatabaseWrapper(MongoClient mc) throws UnknownHostException {
		DB db =mc.getDB("school");
		students=db.getCollection("student");
	}

}
