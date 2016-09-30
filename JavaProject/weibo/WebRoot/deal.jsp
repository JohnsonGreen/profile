<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import="weibo4j.examples.timeline.*"%>
<%@ page import="weibo4j.examples.comment.*"%>
<%@ page import="weibo4j.org.json.*"%>
<%@ page import="weibo4j.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String url = request.getParameter("url");
	String mid = url.subSequence(28, 37).toString();
 %>
<%
	QueryId getid = new QueryId();
	getid.getId(mid);
	String id = getid.id2;
	MyClass my = new MyClass();
	my.myclass(id);
	int com1 = my.com1;
	int com2 = my.com2;
	int rep1 = my.rep1;
	int rep2 = my.rep2;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="example1_join.jsp">
		<jsp:param value="<%=com1 %>" name="com1"/>
		<jsp:param value="<%=com2 %>" name="com2"/>
		<jsp:param value="<%=rep1 %>" name="rep1"/>
		<jsp:param value="<%=rep2 %>" name="rep2"/>
	</jsp:forward>
</body>
</html>