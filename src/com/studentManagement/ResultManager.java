package com.studentManagement;

import java.util.Arrays;

public class ResultManager {
	Student[] students = new Student[10];
	int top = -1;
	
	public void addStudent(Student student) {
		if(top == 9) {
			System.out.println("List is full");
			return;
		}
		students[++top] = student;
		System.out.println(student.getName() + " is added to the list");
	}
	
	public void removeStudent(String targetName) {
		//empty array
		if(top == -1) {
			System.out.println("There are no students");
			return;
		}
		
		//find the index of student to be removed
		int targetIndex = findIndex(targetName);
		
		//target not found
		if(targetIndex == -1) {
			System.out.println("Target not found");
			return;
		}
		
		//move the elements after target to its previous position
		for(int i = targetIndex; i < top; i++) {
			students[i] = students[i + 1];
		}
		
		//reduce the top as we removed an element
		top--;
	}
	
	public Student searchByName(String targetName) {
		int targetIndex = findIndex(targetName);
		
		//target not found
		if(targetIndex == -1) {
			System.out.println("Student not found");
			return null;
		}
		
		//target found
		return students[targetIndex];
		
	}
	
	public int findIndex(String targetName) {
		//find the index of student to be removed
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
	
	public void rankStudents() {
		//sort students based on rank
		Student[] ranked = students.clone();
		
		Arrays.sort(ranked, 0, top + 1);
		
		for(int i = 0; i <= top; i++)
			System.out.println(ranked[i]);
	}
	
	
	public void displayAll() {
		for(int i = 0; i <= top; i++) {
			System.out.println(students[i]);
		}
		System.out.println("----------------------------------");
	}
}
