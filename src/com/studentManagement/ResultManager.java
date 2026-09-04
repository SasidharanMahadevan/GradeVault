package com.studentManagement;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ResultManager {
	private List<Student> students = new ArrayList<Student>();
	
	public void addStudent(Student student) {
		//check if the id is already present
		boolean exists = false;
		if(findIndex(student.getId()) != -1)
			exists = true;
		
		if(exists) {
			System.out.println("Id already exists. Try using other Id's");
			return;
		}
		
		//check if the mark is within the range 
		if(!withinRange(student.getMarks())) {
			System.out.println("Marks should be in range(0,100).");
			return;
		}
		
		//add new student
		students.add(student);
		//System.out.println(student.getName() + " is added to the list");
	}
	
	//generic method
	public <T> void removeStudent(T target) {
		//empty array
		if(students.isEmpty()) {
			System.out.println("There are no students");
			return;
		}
		
		//find the index of student to be removed
		int targetIndex = resolveIndex(target);
		
		//target not found
		if(targetIndex == -1) {
			System.out.println("Student not found");
			return;
		}
		
		//remove the target
		students.remove(targetIndex);
	}
	
	//generic method
	public <T> Student searchStudent(T target) {
		int targetIndex = resolveIndex(target);
		
		//target not found
		if(targetIndex == -1) {
			System.out.println("Student not found");
			return null;
		}
		
		//target found
		return students.get(targetIndex);
		
	}
	
	
	public <T> void updateStudent(T target, Integer[] marks) {
		int targetIndex = resolveIndex(target);
		
		//target not found
		if(targetIndex == -1) {
			System.out.println("Student not found");
			return;
		}
		
		//updating
		if(!withinRange(marks)) {
			System.out.println("Marks should be in range(0,100).");
			return;
		}
			
		Student s = students.get(targetIndex);
		s.setMarks(marks);
		System.out.println("Updated student marks");
	}
	
	
	private <T> int resolveIndex(T  target) {
		int targetIndex = -1;
		if(target instanceof String)
			targetIndex = findIndex((String)target);
		else if(target instanceof Integer)
			targetIndex = findIndex((Integer)target);	
		return targetIndex;
	}
	
	
	private int findIndex(String targetName) {
		//find the index of student
		for(int i = 0; i < students.size(); i++) {
			String name = students.get(i).getName();
			//found
			if(name.equals(targetName)) {
				return i;
			}
		}
		
		//not found
		return -1;
	}
	
	
	//overloaded method for finding index
	private int findIndex(Integer id) {

		//find the index of student
		for(int i = 0; i < students.size(); i++) {
			//found
			// == will compare the obects, not the values when used with Integer object
			if(id.equals(students.get(i).getId()))
				return i;
		}
		
		//not found
		return -1;
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

	
	public void rankStudents() {
		//sort students based on rank
		List<Student> ranked = sortStudents();
		
		for(int i = 0; i < students.size(); i++)
			System.out.println(ranked.get(i).displayDetails());
	}
	
	
	public List<Student> sortStudents() {
		List<Student> ranked = new ArrayList<>(students);
		Collections.sort(ranked);
		return ranked;
	}
	
	
	public List<Student> getStudents() {
		return new ArrayList<>(students);
	}
	
	public void displayAll() {
		for(int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
		System.out.println("----------------------------------");
	}
	
}
