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
		schoolController=new SchoolController(database);
		students=new ArrayList<Student>();
		when(database.getAllStudentsList()).thenReturn(students);
	}
	
	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		assertGetAllStudents(0);
	}

	private void assertGetAllStudents(int expected) {
		List<Student> allStudents=schoolController.getAllStudents();
		verify(database).getAllStudentsList();
		assertEquals(expected, allStudents.size());
	}
	
	@Test
	public void testGetAllStudentsWhenThereIsOneStudents(){
		students.add(new Student("1", "test"));
		assertGetAllStudents(1);
		
	}
	
	@Test
	public void testGetStudentsByIdWhenStudentsInNotThere(){
		students.add(new Student("1", "test"));
		Student student=schoolController.getStudentById("2");
		verify(database).findStudentById("2");
		assertNull(student);
	}
	
	@Test
	public void testGetStudentByIdWhenStudentIsThere(){
		students.add(new Student("1", "test"));
		when(database.findStudentById("1")).thenReturn(students.get(0));
		Student student=schoolController.getStudentById("1");
		verify(database).findStudentById("1");
		assertNotNull(student);
	}

}
