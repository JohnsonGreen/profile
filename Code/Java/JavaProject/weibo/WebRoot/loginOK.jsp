<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.*"%>

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
 	Connection conn = null;
 	ResultSet rs = null;
 	PreparedStatement pstmt = null;
 	boolean flag = false;
 	String sql = "SELECT * from user where name=? and password=?";
 
 	try{
 		DriverManager.registerDriver(new com.mysql.jdbc.Driver());  //注册mysql驱动
 		//创建conn
 		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?characterEncoding=UTF-8","root","new_pass");
 		pstmt = conn.prepareStatement(sql);//防止sql注入攻击
   		pstmt.setString(1,name);
  		pstmt.setString(2,pwd);
 		rs = pstmt.executeQuery();
 		if(rs.next()) {
     		flag = true;
   		} else {
    		request.setAttribute("err","用户名或密码错误!");
  		 }
 	}catch(SQLException e){
 		out.println("执行SQL\"" + sql + "\"时发生异常：" + e.getMessage());
 		return;
 	}finally{
 		if(conn != null) conn.close(); 
   		pstmt.close();
 	}
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script language="javascript" type="text/javascript">
	function login(){
		window.location.href="login.jsp";
	}
 </script>
</head>
<body>
	<%if(flag){ %>
	<jsp:forward page="index_third.jsp">
		<jsp:param value="<%=name %>" name="username"/>
	</jsp:forward>
	<%}else{ %>
		<jsp:forward page="login.jsp"></jsp:forward>
	<%} %>
</body>
</html>