package com.studentManagement;

import java.sql.SQLException;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Integer id;
		String name ;
		
		int userChoice = 1;
		
		DBHandling db = new DBHandling();
		ResultManager result = new ResultManager(db);
		
		while(userChoice != 7) {
			System.out.println(""
					+ "1.Add student \n"
					+ "2.Search student\n"
					+ "3.Remove student\n"
					+ "4.Update student marks\n"
					+ "5.Rank students \n"
					+ "6.Display students \n"
					+ "7.Exit"
					+ "");
			
			System.out.println("Enter your choice: ");
			
			try {
				userChoice = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a number ");
				sc.nextLine();
				continue;
			}
			
			switch(userChoice) {
			case 1:
					try {
						//id input
						System.out.println("Enter id : ");
						id = sc.nextInt();
						
						//flushes out the buffer
						sc.nextLine();
						
						//name input
						System.out.println("Enter name: ");
						name = sc.nextLine();
						
						//marks input
						Integer[] arr = new Integer[3];
						System.out.println("Enter marks: ");
						for(int i = 0; i < 3; i++) {
							arr[i] = sc.nextInt();
						}
						
						//student object creation
						Student s = new Student(id, name, arr);
						result.addStudent(s);
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);						
						//remove the invalid input
						sc.nextLine();
					}
					break;
					
			case 2:
					try {
						System.out.println("Enter the student Id to be searched: ");
						id = sc.nextInt();
						//sc.nextInt() returns primitive int
						//autoboxing will happen here
						//primitive int -> Integer object
						
						//search
						System.out.println(result.searchStudent(id));
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);
						
						//remove the invalid input
						sc.nextLine();
					}
					break;
				
			case 3:
					try {
						System.out.println("Enter the student Id: ");
						id = sc.nextInt();
						
						//delete
						result.removeStudent(id);
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);
						
						//remove the invalid input
						sc.nextLine();
					}
					break;
					
			case 4:
					System.out.println("Enter marks to be updated: ");
					Integer[] marks = new Integer[3];
					try {
						for(int i = 0; i < marks.length; i++)
							marks[i] = sc.nextInt();
					
						System.out.println("Enter id of the student: ");
						id = sc.nextInt();
						
						//update
						result.updateStudent(id, marks);
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);
						
						//remove the invalid input
						sc.nextLine();
					}
					break;
					
			case 5:
					try {
						System.out.println("Students ranked by average of marks: ");
						result.rankStudents();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);
						
						//remove the invalid input
						sc.nextLine();
					}
					break;
				
			case 6:
					try {
						System.out.println("Student details: ");
						result.displayAll();
					}
					catch(SQLException e) {
						System.out.println("SQL Exception occured: "+ e);
						
						//remove the invalid input
						sc.nextLine();
					}
					break;
					
			case 7:
					System.out.println("Exiting and saving data...");
					break;
						
			default: 
					System.out.println("Invalid choice");

			}
		}
		sc.close();
	}
}



