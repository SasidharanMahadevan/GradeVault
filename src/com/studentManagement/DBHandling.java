package com.studentManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandling {
	private String url = "jdbc:mysql://localhost:3306/students";
	private String username = "root";
	private String pass = "password";
	
	public void insertStudent(Student student) throws SQLException {
		//student data
		Integer id = student.getId();
		String name = student.getName();
		Integer[] marks = student.getMarks();
		
		//create connection between java and database
		Connection con = getConnection();
		
		//sql query to be executed
		//use prepared statement for security
		String query = "INSERT INTO student_details VALUES (?,?,?,?,?) ;";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2,name);
		pst.setInt(3, marks[0]);
		pst.setInt(4, marks[1]);
		pst.setInt(5, marks[2]);
		
		//insert the values
		//returns the no of rows affected
		int rows = pst.executeUpdate();
		
		System.out.println("No of rows affected: "+ rows);
		
		//close the resource
		con.close();
		
	}
	
	public int deleteStudent(Integer id) throws SQLException {
		Connection con = getConnection();
		String query = "DELETE FROM student_details WHERE student_id = (?)";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		int rows = pst.executeUpdate();
		con.close();
		
		//no of rows affected
		return rows;
	}
	
	public int updateStudent(Integer id, Integer[] marks) throws SQLException {
		Connection con = getConnection();
		String query = "UPDATE student_details SET mark1 = (?), mark2 = (?), mark3 = (?) WHERE student_id = (?)";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, marks[0]);
		pst.setInt(2, marks[1]);
		pst.setInt(3, marks[2]);
		pst.setInt(4, id);
		
		int rows = pst.executeUpdate();
		con.close();
		
		//no of rows affected
		return rows;
	}
	
	public Student searchStudent(Integer id) throws SQLException {
		Connection con = getConnection();
		String query = "SELECT * FROM student_details WHERE student_id = (?)";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		
		Student s = null;
		
		//target student found
		if(rs.next())
			s = mapToStudent(rs);
		
		con.close();
		return s;
	}
	
	public List<Student> getAllStudents() throws SQLException {
		Connection con = getConnection();
		String query = "SELECT * FROM student_details";
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		List<Student> students = new ArrayList<>();
		while(rs.next()) {
			Student s = mapToStudent(rs);
			students.add(s);
		}
		
		con.close();
		return students;
	}
	
	private Student mapToStudent(ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		String name = rs.getString(2);
		Integer[] marks = {rs.getInt(3), rs.getInt(4), rs.getInt(5)};
		Student s = new Student(id, name, marks);
			
		return s;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, pass);
	}
	
}
