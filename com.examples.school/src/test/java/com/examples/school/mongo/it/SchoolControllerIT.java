package com.examples.school.mongo.it;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.examples.school.SchoolController;
import com.examples.school.Student;
import com.examples.school.mongo.MongoDatabaseWrapper;
import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class SchoolControllerIT {

	private MongoDatabaseWrapper database;
	private DBCollection students;
	private SchoolController schoolController;

	@Before
	public void setUp() throws Exception {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();

		DB db = mongoClient.getDB("school");
		db.getCollection("student").drop();

		database = new MongoDatabaseWrapper(mongoClient);
		students = db.getCollection("student");
		
		schoolController=new SchoolController(database);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Student> allStudents=schoolController.getAllStudents();
		assertEquals(0, allStudents.size());
	}

}
