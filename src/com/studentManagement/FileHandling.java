package com.studentManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandling{
	//absolute path of file
	private String studentDirectory = "studentData.txt";
	private String rankedDirectory = "rankedData.txt";

	private File studentFile = new File(studentDirectory);
	private File rankedFile = new File(rankedDirectory);
	
	public void loadFromFile(ResultManager result) {
		String data;
		
		//read from file
		try(Scanner sc = new Scanner(studentFile)){
			while(sc.hasNextLine()) {
				try {
					data = sc.nextLine();
					String[] text = data.split(",");
					Integer id = Integer.parseInt(text[0]);
					String name = text[1];
					Integer[] marks = new Integer[3];
					marks[0] = Integer.parseInt(text[2]);
					marks[1] = Integer.parseInt(text[3]);
					marks[2] = Integer.parseInt(text[4]);
					Student s = new Student(id,name,marks);
					result.addStudent(s);
				}
				catch(Exception e) {
					System.out.println("Skipping corrupted line " + e);
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println("File not found ");
		}catch(Exception e) {
			System.out.println("Error loading file");
		}
	}
	
	public void exportStudents(Student[] student) {
		saveToFile(studentFile, student);
	}
	
	public void exportRankings(Student[] student) {
		saveToFile(rankedFile, student);
	}
	
	public void saveToFile(File file, Student[] student) {
		//FileWriter uses \n separately to insert new line
		//try(FileWriter writer = new FileWriter(file);)
		
		//PrintWriter appends a newline at the end of each line
		try(PrintWriter writer = new PrintWriter(new FileWriter(file));){
			for(Student s: student) {
				//convert the student data to a string
				String data = s.getId() + "," + s.getName() + "," + s.getMarks()[0] + "," + s.getMarks()[1] + "," + s.getMarks()[2];
				writer.println(data);
				
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	
}
