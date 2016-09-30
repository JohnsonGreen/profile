<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
  	<title>用户注册</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/register.css"/>
  </head>
  <body>
  <div class="biaoti">
    <h>微博传播节点分析及可视化系统账号注册</h>
</div>

<div id="register">
    <div class="empty">
        <h>W&nbsp;e&nbsp;l&nbsp;c&nbsp;o&nbsp;m&nbsp;e</h>
    </div>
    <form action="registerOK.jsp" method="POST">
        <input type="text" required="required" placeholder="用户名" name="username"></input>
        <input type="password" required="required" placeholder="密码" name="password"></input>
        <button class="denglu" type="submit">确定</button>
        <button class="zhuce" type="reset">取消</button>
    </form>
</div>

  </body>
</html>