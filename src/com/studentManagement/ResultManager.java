package com.studentManagement;

import java.util.Arrays;

public class ResultManager {
	private Student[] students = new Student[10];
	private int top = -1;
	
	public void addStudent(Student student) {
		if(top == students.length - 1) {
			System.out.println("List is full");
			return;
		}
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
		students[++top] = student;
		//System.out.println(student.getName() + " is added to the list");
	}
	
	//generic method
	public <T> void removeStudent(T target) {
		//empty array
		if(top == -1) {
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
		
		//move the elements after target to its previous position
		for(int i = targetIndex; i < top; i++) {
			students[i] = students[i + 1];
		}
		
		//remove last student
		students[top] = null;
		//reduce the top as we removed an element
		top--;
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
		return students[targetIndex];
		
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
			
		students[targetIndex].setMarks(marks);
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
	
	public int findIndex(String targetName) {
		//find the index of student
		for(int i = 0; i <= top; i++) {
			String name = students[i].getName();
			//found
			if(name.equals(targetName)) {
				return i;
			}
		}
		
		//not found
		return -1;
	}
	
	//overloaded method for finding index
	public int findIndex(Integer id) {
		//find the index of student
		for(int i = 0; i <= top; i++) {
			//found
			// == will compare the obects, not the values when used with Integer object
			if(id.equals(students[i].getId()))
				return i;
		}
		
		//not found
		return -1;
	}
	
	public boolean withinRange(Integer[] marks) {
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
		Student[] ranked = sortStudents();
		
		for(int i = 0; i <= top; i++)
			System.out.println(ranked[i].displayDetails());
	}
	
	public Student[] sortStudents() {
		Student[] ranked = Arrays.copyOf(students, top + 1);
		Arrays.sort(ranked);
		return ranked;
	}
	
	public Student[] getStudents() {
		return Arrays.copyOf(students, top + 1);
	}
	
	public void displayAll() {
		for(int i = 0; i <= top; i++) {
			System.out.println(students[i]);
		}
		System.out.println("----------------------------------");
	}
	
}
