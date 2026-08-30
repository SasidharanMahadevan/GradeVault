package com.studentManagement;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name ;
		
		
		int userChoice = 1;
		
		
		ResultManager result = new ResultManager();
		
		FileHandling file = new FileHandling();
		file.loadFromFile(result);
		
		while(userChoice != 6) {
			System.out.println("1.Add student \n2.Search student \n3.Remove student \n4.Rank students \n5.Display students \n6.Exit");
			System.out.println("Enter your choice: ");
			userChoice = sc.nextInt();
			switch(userChoice) {
			case 1:
					int id;
					System.out.println("Enter id : ");
					id = sc.nextInt();
					
					//flushes out the buffer
					sc.nextLine();
					System.out.println("Enter name: ");
					name = sc.nextLine();
					
					int[] arr = new int[3];
					System.out.println("Enter marks: ");
					for(int i = 0; i < 3; i++) {
						arr[i] = sc.nextInt();
					}
					
					Student s = new Student(id, name, arr);
					result.addStudent(s);
					break;
					
			case 2:
					System.out.println("Enter the student to be searched: ");
					
					//flushes out the buffer
					sc.nextLine();
					
					name = sc.nextLine();
					System.out.println(result.searchByName(name));
					break;
				
			case 3:
					System.out.println("Enter the student to be removed: ");
					
					//flushes out the buffer
					sc.nextLine();
					
					name = sc.nextLine();
					result.removeStudent(name);
					//result.displayAll();
					break;
					
			case 4:
					System.out.println("Students ranked by average of marks: ");
					result.rankStudents();
					break;
				
			case 5:
					System.out.println("Student details: ");
					result.displayAll();
					break;
			
			case 6:
					System.out.println("Exiting and saving data...");
					break;
					
			default: 
					System.out.println("Invalid choice");
	
			}
		}
		
		file.saveToFile(result);
	}
}



