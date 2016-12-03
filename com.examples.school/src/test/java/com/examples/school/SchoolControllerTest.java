package com.examples.school;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SchoolControllerTest {
	private Database database;
	private List<Student> students;
	private SchoolController schoolController;
	
	@Before 
	public void setup() throws Exception{
		database=mock(Database.class);
		schoolController=new SchoolController();
		students=new ArrayList<Student>();
		when(database.getAllStudentsList()).thenReturn(students);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}