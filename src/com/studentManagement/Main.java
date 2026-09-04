package com.studentManagement;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Integer id;
		String name ;
		
		int choice = 1;
		int userChoice = 1;
		
		ResultManager result = new ResultManager();
		
		FileHandling file = new FileHandling();
		file.loadFromFile(result);
		
		while(userChoice != 8) {
			System.out.println(""
					+ "1.Add student \n"
					+ "2.Search student\n"
					+ "3.Remove student\n"
					+ "4.Update student marks\n"
					+ "5.Rank students \n"
					+ "6.Display students \n"
					+ "7.Export the ranked list\n"
					+ "8.Exit"
					+ "");
			System.out.println("Enter your choice: ");
			try {
				userChoice = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Please enter a number ");
				sc.nextLine();
				continue;
			}
			
			switch(userChoice) {
			case 1:
					try {
						System.out.println("Enter id : ");
						id = sc.nextInt();
						
						//flushes out the buffer
						sc.nextLine();
						System.out.println("Enter name: ");
						name = sc.nextLine();
						
						Integer[] arr = new Integer[3];
						System.out.println("Enter marks: ");
						for(int i = 0; i < 3; i++) {
							arr[i] = sc.nextInt();
						}
						
						Student s = new Student(id, name, arr);
						result.addStudent(s);
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					
					break;
					
			case 2:
					System.out.println("1.Search by name\n2.Search by Id");
					try {
						choice = sc.nextInt();
						if(choice == 1) {
							System.out.println("Enter the student name to be searched: ");
							
							//flushes out the buffer
							sc.nextLine();
							
							name = sc.nextLine();
							System.out.println(result.searchStudent(name));
						}
						else if(choice == 2){
							System.out.println("Enter the student Id to be searched: ");
							
							id = sc.nextInt();
							//sc.nextInt() returns primitive int
							//if id is declared as int
							//autoboxing will happen here
							//primitive int -> Integer object
							System.out.println(result.searchStudent(id));
						}
						else {
							System.out.println("Invalid choice");
						}
					}catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					
					break;
				
			case 3:
					System.out.println("1.Remove by name\n2.Remove by Id");
					try {
						choice = sc.nextInt();
						if(choice == 1) {
							//flushes out the buffer
							sc.nextLine();
							
							System.out.println("Enter the student name: ");
							name = sc.nextLine();
							result.removeStudent(name);
						}
						else if(choice == 2){
							System.out.println("Enter the student Id: ");
							
							id = sc.nextInt();
							//same as case 2 concept
							result.removeStudent(id);
						}
						else {
							System.out.println("Invalid choice");
						}
					}catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					//result.displayAll();
					break;
					
			case 4:
					System.out.println("Enter marks to be updated: ");
					Integer[] updateMarks = new Integer[3];
					try {
						for(int i = 0; i < updateMarks.length; i++)
							updateMarks[i] = sc.nextInt();
						
						System.out.println("1.Update by name\n2.Update by Id");
						choice = sc.nextInt();
						
						if(choice == 1) {
							//flushes out the buffer
							sc.nextLine();
							
							System.out.println("Enter name of the student: ");
							name = sc.nextLine();
							result.updateStudent(name, updateMarks);
						}
						else if(choice == 2){
							System.out.println("Enter id of the student: ");
							id = sc.nextInt();
							result.updateStudent(id, updateMarks);
						}
						else {
							System.out.println("Invalid choice");
						}
					}
					catch(InputMismatchException e) {
						System.out.println("Data type is not valid. Provide correct data");
						
						//remove the invalid input
						sc.nextLine();
					}
					
					break;
					
			case 5:
					System.out.println("Students ranked by average of marks: ");
					result.rankStudents();
					break;
				
			case 6:
					System.out.println("Student details: ");
					result.displayAll();
					break;
			
			case 7:
					System.out.println("Exporting ranked list");
					//sort the student array using id
					//send the sorted array as an argument
					//load the sorted list to file
					file.exportRankings(result.sortStudents());
					break;
					
			case 8:
					System.out.println("Exiting and saving data...");
					break;
						
			default: 
					System.out.println("Invalid choice");

			}
		}
		
		file.exportStudents(result.getStudents());
		sc.close();
	}
}



