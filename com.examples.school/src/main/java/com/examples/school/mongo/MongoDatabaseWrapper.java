package com.examples.school.mongo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.examples.school.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapper implements Database{
	private DBCollection students;

	public MongoDatabaseWrapper(MongoClient mc) throws UnknownHostException {
		DB db =mc.getDB("school");
		students=db.getCollection("student");
	}

	public List<Student> getAllStudentsList() {
		DBCursor cursor=students.find();
		return StreamSupport.stream(cursor.spliterator(), false).map(e->new Student((String)e.get("id"), (String)e.get("name"))).collect(Collectors.toList());
	}

	public Student findStudentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
