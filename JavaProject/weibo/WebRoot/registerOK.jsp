<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>

<%!
	//Mysql中SQL中的单引号需要转化为\'
	public String forSQL(String sql){
		return sql.replace("'", "\\'");
	}
 %>
 <%
 	request.setCharacterEncoding("utf-8");
 	String name = request.getParameter("username");
 	String pwd = request.getParameter("password");
 	
 	String sql = "INSERT INTO user " +
 				 "(name, password) values " +
 				 "('" + name + "', '" + pwd + "')";
 	Connection conn = null;
 	Statement stmt = null;
 	int result = 0;
 	try{
 		DriverManager.registerDriver(new com.mysql.jdbc.Driver());  //注册mysql驱动
 		//创建conn
 		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?characterEncoding=UTF-8","root","new_pass");
 		
 		stmt = conn.createStatement(); //创建Statement
 		result = stmt.executeUpdate(sql); //执行sql语句，返回影响行数
 	}catch(SQLException e){
 		out.println("执行SQL\"" + sql + "\"时发生异常：" + e.getMessage());
 		return;
 	}finally{
 		if(stmt != null) stmt.close();
 		if(conn != null) conn.close(); 
 	}
  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册成功</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta  http-equiv="refresh" content="0;url=/weibo/index_third.jsp">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  </body>
</html>
