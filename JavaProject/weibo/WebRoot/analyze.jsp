<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>微博解析——进行分析</title>
	<link rel="stylesheet" type="text/css" href="css/introductionStyle.css">
</head>
<body>
	<div id="header_wrapper">
		<div id="header">
			<div id="header_title">
				<h1>
					<a href="index_third.jsp">
						<strong>微博</strong>解析
					</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="index_third.jsp">主页</a></li>
					<li><a href="introduction.jsp">使用说明</a></li>
					<li><a href="example.jsp">案例参考</a></li>
					<li><a href="analyze.jsp" class="current">进行分析</a></li>
					<li><a href="contact.jsp">联系我们</a></li>
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
	    	<input type="submit" value="进行分析">
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