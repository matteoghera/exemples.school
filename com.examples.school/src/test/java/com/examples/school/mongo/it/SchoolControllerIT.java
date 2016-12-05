package com.examples.school.mongo.it;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.examples.school.SchoolController;
import com.examples.school.Student;
import com.examples.school.mongo.MongoDatabaseWrapper;
import com.examples.school.mongo.helper.MongoTestHelper;
import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class SchoolControllerIT {

	private MongoDatabaseWrapper database;
	private SchoolController schoolController;
	private MongoTestHelper mongoTestHelper;

	@Before
	public void setUp() throws Exception {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		mongoTestHelper=new MongoTestHelper(mongoClient);

		database = new MongoDatabaseWrapper(mongoClient);
		
		schoolController=new SchoolController(database);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Student> allStudents=schoolController.getAllStudents();
		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testGetAllStudentsWhenThereIsOneStudents(){
		mongoTestHelper.addStudent("1", "test");
		List<Student> allStudents=schoolController.getAllStudents();
		assertEquals(1, allStudents.size());
	}
	
	@Test
	public void testGetStudentByIdWhenStudentIsNotThere(){
		mongoTestHelper.addStudent("1", "test");
		Student student=schoolController.getStudentById("2");
		assertNull(student);
	}
	
	
	@Test
	public void testGetStudentByIdWhenStudentIsThere(){
		mongoTestHelper.addStudent("1", "test");
		Student student=schoolController.getStudentById("1");
		assertNotNull(student);
		assertEquals("test", student.getName());
	}
}
