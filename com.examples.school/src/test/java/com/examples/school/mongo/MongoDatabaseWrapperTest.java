package com.examples.school.mongo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapperTest {
	private MongoDatabaseWrapper mongoDatabase;
	private DBCollection students;
	
	@Before
	public void initDB() throws UnknownHostException{
		Fongo fongo=new Fongo("mongo server 1");
		MongoClient mongoClient=fongo.getMongo();
		
		DB db=mongoClient.getDB("school");
		db.getCollection("student").drop();
		
		mongoDatabase=new MongoDatabaseWrapper(mongoClient);
		students=db.getCollection("student");
	}
	
	@Test
	public void testGetAllStudentsEmpty() {
		assertTrue(mongoDatabase.getAllStudentsList().isEmpty());
	}
	
	@Test
	public void testGetAllStudentsNotEmpty(){
		addStudent("1", "first");
		addStudent("2", "second");
		
		assertEquals(2, mongoDatabase.getAllStudentsList().size());
	}
	
	@Test
	public void testFindStudentByIdNotFound(){
		addStudent("1", "first");
		assertNull(mongoDatabase.findStudentById("2"));
	}
	
	private void addStudent(String id, String name){
		BasicDBObject document=new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		students.insert(document);
	}
}
