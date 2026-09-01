package com.studentManagement;

import java.util.Arrays;

public class Student extends Person implements Comparable<Student>{
	private Integer[] marks;
	
	
	//constructor
	Student(Integer id, String name, Integer[] marks){
		super(id, name);
		this.marks = marks;
	}
	
	//getters
	public String getRole() {
		return "Student";
	}
	
	public Integer[] getMarks() {
		return marks;
	}
	
	//setters
	public void setMarks(Integer[] marks) {
		this.marks = marks;
	}
	
	//find the average of marks
	public double computeAverage() {
		double avg = 0;
		for(Integer num : marks)
			avg += num;
		return avg/marks.length;
	}
	
	//display details
	public String displayDetails() {
		return this.toString();
	}
	
	//overriding default toString method
	@Override
	public String toString() {
		return "Id: " + this.getId() + " Name: " + this.getName() + " Marks: " + Arrays.toString(marks);
	}

	@Override
	public int compareTo(Student s) {
		// TODO Auto-generated method stub
		return Double.compare(s.computeAverage(), this.computeAverage());

		/*	
		 * 	General form:
		  	Ascending
			Double.compare(this.value, s.value);
			
			Our modified version to sort in descending order
			Descending
			Double.compare(s.value, this.value);
		*/
	}
}
