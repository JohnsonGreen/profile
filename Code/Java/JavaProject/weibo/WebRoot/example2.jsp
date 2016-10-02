<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>微博解析——案例参考</title>
	<link rel="stylesheet" type="text/css" href="css/example1.css">
		<script type="text/javascript" src="js/echarts.min.js"></script>
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
					<li><a href="introduction.jsp" >使用说明</a></li>
					<li><a href="example.jsp" class="current">案例参考</a></li>
					<li><a href="analyze.jsp">进行分析</a></li>
					<li><a href="contact.jsp">联系我们</a></li>
				</ul>
			</div>
		</div>
		<div class="cleaner"></div>
	</div>
	<div id="examples">
		<ul>
			<li><a href="example1.jsp"  id="first">案例一</a></li>
			<li><a href="example2.jsp" class="current" id="second">案例二</a></li>
			<li><a href="example3.jsp" id="third">案例三</a></li>
		</ul>
	</div>
	<div class="cleaner"></div>
	<div id="example_menu">
		<ul>
			<li><a href="example2.jsp" class="current">总体</a></li>
			<li><a href="example2_spread.jsp">传播分析</a></li>
			<li><a href="example2_emotion.jsp">情感分析</a></li>
			<li><a href="example2_join.jsp">参与信息</a></li>
		</ul>
	</div>
	<!--<div class="cleaner"></div>-->
	<div id="content_wrapper">
		<div id="chart">
			<h2>这里是图表</h2>
		</div>
		<div id="content">
			<h2>这里是正文</h2>
		</div>
	</div>
	<div id="footer">
		<ul class="footer_menu">
			<li><a href="">这里是footer</a></li>
		</ul>
	</div>
</body>
</html>