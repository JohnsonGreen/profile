<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import="weibo4j.org.json.*"%>
<%@ page import="weibo4j.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("utf-8");
String a = request.getParameter("a");
String b = request.getParameter("b");
String c = request.getParameter("c");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>微博解析</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="header">
		<div id="header_title">
			<h1>
				<a href="index_third.jsp">
					<strong>微博</strong>解析
					<span>一个简单的微博分析网站</span>
				</a>
			</h1>
		</div>
		<div class="cleaner"></div>
	</div>
	<div>
		<div id="menu">
			<ul>
				<li><a href="introduction.jsp">使用说明</a></li>
				<li><a href="example.jsp">案例参考</a></li>
				<li><a href="analyze.jsp">进行分析</a></li>
				<li><a href="contact.jsp">联系我们</a></li>
			</ul>
		</div>
		<div class="cleaner"></div>
	<div>
	<div id="footer">
		<ul class="footer_menu">
			<li><a href="">这里是footer</a></li>
		</ul>
	</div>
</body>
</html>