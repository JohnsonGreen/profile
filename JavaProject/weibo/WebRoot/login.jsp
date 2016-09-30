<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<script language="javascript" type="text/javascript">
		function validate(){
			window.location.href="register.jsp";
		}
 	</script>
	<title>登录</title>
</head>
<body>
<div class="biaoti">
    <h>微博传播节点分析及可视化系统登陆</h>
</div>

<div id="login">
    <div class="empty">
        <h>W&nbsp;e&nbsp;l&nbsp;c&nbsp;o&nbsp;m&nbsp;e</h>
    </div>
    <form action="loginOK.jsp" method="post">
        <input type="text" required="required" placeholder="Username" name="username"></input>
        <input type="password" required="required" placeholder="password" name="password"></input>
        <button class="denglu" type="submit">login</button>
        <button class="zhuce" type="button" onclick="validate()">register</button>
    </form>
</div>
<%
   // 判断是否有错误信息，如果有则打印
   // 如果没有此段代码，则显示时会直接打印null
   if(request.getAttribute("err")!=null)
   {
%>
	<center>
   		 <h4><font color="red"><%=request.getAttribute("err")%></font></h4>
    </center>
<%
   }
%>
</body>
</html>