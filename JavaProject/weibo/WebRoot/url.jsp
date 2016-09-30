<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>微博解析——进行分析</title>
	<link rel="stylesheet" type="text/css" href="css/introductionStyle.css">
</head>
<body>
	<div id="header_wrapper">
		<div id="header">
			<div id="header_title">
				<h1>
					<a href="index_third.html">
						<strong>微博</strong>解析
					</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="index_third.html">主页</a></li>
					<li><a href="introduction.html">使用说明</a></li>
					<li><a href="example.html">案例参考</a></li>
					<li><a href="analyze.html" class="current">进行分析</a></li>
					<li><a href="contact.html">联系我们</a></li>
				</ul>
			</div>
		</div>
		<div class="cleaner"></div>
	</div>
	<div class="container">
	  <div id="search">
	  	<form method="post" action="deal.jsp">
	    <label for="search">请输入要分析微博的URL地址:</label>
	    <input type="text" name="url">
	    <input class="button" type="submit" value="进行分析">
	    </form>
	  </div>
	</div>
	<div class="cleaner"></div>
	<div id="footer">
		<ul class="footer_menu">
			<li><a href="">这里是footer</a></li>
		</ul>
	</div>
</body>
</html>