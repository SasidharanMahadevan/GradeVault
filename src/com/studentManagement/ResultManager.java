package com.studentManagement;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ResultManager {
	private DBHandling db;
		
	ResultManager(DBHandling db){
		this.db = db;
	}
	
	public void addStudent(Student student) throws SQLException {
		//check if the id is already present
		if(db.searchStudent(student.getId()) != null) {
			System.out.println("Id already exists. Try using other Id's");
			return;
		}
		
		//check if the mark is within the range 
		if(!withinRange(student.getMarks())) {
			System.out.println("Marks should be in range(0,100).");
			return;
		}
		
		//add new student
		db.insertStudent(student);
		//System.out.println(student.getName() + " is added to the list");
	}
	
	public void removeStudent(Integer id) throws SQLException {
		//remove the target
		int rows = db.deleteStudent(id);
		if(rows == 0)
			System.out.println("Student not found");
		
		System.out.println("No of rows affected: "+ rows);
	}
	
	public void updateStudent(Integer id, Integer[] marks) throws SQLException {
		//Range check for marks
		if(!withinRange(marks)) {
			System.out.println("Marks should be in range(0,100).");
			return;
		}
			
		//updating
		int rows = db.updateStudent(id, marks);
		if(rows == 0)
			System.out.println("Student not found");
		
		System.out.println("No of rows affected: "+ rows);
	}
	
	public Student searchStudent(Integer id) throws SQLException {
		Student s = db.searchStudent(id);
		
		//target not found
		if(s == null) 
			System.out.println("Student not found");
		
		//Not found -> return null
		//Found	-> return student
		return s;
		
	}
	
	private boolean withinRange(Integer[] marks) {
		for(Integer mark : marks) {
			//out of range
			if(mark < 0 || mark > 100) 
				return false;
		}
		
		//within range
		return true;
	}

	public void rankStudents() throws SQLException {
		//sort students based on rank
		List<Student> students = db.getAllStudents();
		Collections.sort(students); 
		
		for(Student s : students)
			System.out.println(s.displayDetails());
	}
	
	public void displayAll() throws SQLException {
		List<Student> students = db.getAllStudents();
		
		for(int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
		
		System.out.println("----------------------------------");
	}
	
}
