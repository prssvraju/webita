package com.ita;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {
	
	public Connection getConn()throws Exception
	{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/IT","root","password");
			return con;
	}

	public static void main(String args[])throws IOException,Exception
	{
		StudentDao dao= new StudentDao();
		if(dao.getConn()!=null)
		{
			System.out.println("Connection create");
		}
		
//		Student st = new Student();
//		st.setID(2);
//		st.setROLLNO("1205");
//		st.setNAME("siva");
//		
//		boolean res= dao.insertStudent(st);
//		if(!res)
//		{
//			System.out.println("One record is inserted");
//		}
//		
		
		ArrayList al = dao.dispalyRecords("select * from student");
		for(int i=0;i<al.size();i++)
		{
			Student st= (Student)al.get(i);
			System.out.println("---------------");
			System.out.println(st.getID());
			System.out.println(st.getROLLNO());
			System.out.println(st.getNAME());
		}
	}
	
	boolean insertStudent(Student st) throws Exception
	{
		Connection con = getConn();
		
		String querry="insert into student"
				+ "(ID,ROLLNO,NAME)"
				+ "values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(querry);
		ps.setInt(1, st.getID());
		ps.setString(2, st.getROLLNO());
		ps.setString(3, st.getNAME());
		
		boolean res= ps.execute();
		
		
		ps.close();
		con.close();
		return res;
	}
	
	
	
	public ArrayList dispalyRecords(String sql) throws Exception
	{
		
		Connection con = getConn();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList al  = new ArrayList();
		
		
		while(rs.next())
		{
			Student st = new Student();
			st.setID(rs.getInt("ID"));
			st.setROLLNO(rs.getString("ROLLNO"));
			st.setNAME(rs.getString("NAME"));
			al.add(st);
			
		}
		
		ps.close();
		con.close();
		
		
		return al;

	}
	
}














