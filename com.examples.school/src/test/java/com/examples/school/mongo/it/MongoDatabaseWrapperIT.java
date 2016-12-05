package com.examples.school.mongo.it;

import java.net.UnknownHostException;

import com.examples.school.mongo.common.AbstractMongoDatabaseWrapperTest;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapperIT extends AbstractMongoDatabaseWrapperTest {

	@Override
	public MongoClient createMongoClient() throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient=new MongoClient();
		return mongoClient;
	}

}
