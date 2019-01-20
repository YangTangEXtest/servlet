package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domian.Choice;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class MySqlGet extends HttpServlet {


	@SuppressWarnings({ "null", "null" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/choicetest";
		String user = "root";
		String pass = "sa";
		
		MySqlConn mysqlconn = null;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL,user,pass);
			stmt = (Statement) conn.createStatement();
			String sql;
			sql = "SELECT answer,optionA,optionB,optionC,optionD,stem FROM choice";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			List<Choice> choices = new ArrayList<Choice>();
			while(rs.next())
			{
				Choice choice = new Choice() ;
				String anwser = rs.getString("answer");
				String optionA = rs.getString("optionA");
				String optionB = rs.getString("optionB");
				String optionC = rs.getString("optionC");
				String optionD = rs.getString("optionD");
				String stem = rs.getString("stem");
				out.println(anwser+"          "+optionA+"          "+optionB+"          "+optionC+"          "+optionD+"          "+stem);
				out.println("-----------分割线--------------");
				choice.setAnwser(anwser);
				choice.setOptionA(optionA);
				choice.setOptionB(optionB);
				choice.setOptionC(optionC);
				choice.setOptionD(optionD);
				choice.setStem(stem);
				choices.add(choice);
			}
			out.println("-----------这是链表---------------"+choices);
			request.getSession().setAttribute("choices", choices);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}

}
