<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			<li><a href="example1.jsp" class="current" id="first">案例一</a></li>
			<li><a href="example2.jsp" id="second">案例二</a></li>
			<li><a href="example3.jsp" id="third">案例三</a></li>
		</ul>
	</div>
	<div class="cleaner"></div>
	<div id="example_menu">
		<ul>
			<li><a href="example1.jsp">总体</a></li>
			<li><a href="example1_spread.jsp" class="current">传播分析</a></li>
			<li><a href="example1_emotion.jsp">情感分析</a></li>
			<li><a href="example1_join.jsp">参与信息</a></li>
		</ul>
	</div>
	<!--<div class="cleaner"></div>-->
	<div id="content_wrapper">
		<div id="chart">
			<h2>这里是图表</h2>
			<script type="text/javascript">
				var myChart = echarts.init(document.getElementById('chart'));

				option = {
					backgroundColor: '#161627',
					title: {
						text: '路径分析',
						textStyle: {
				            color: '#eee'
				        }
					},
					tooltip: {},
					animationDurationUpdate: 1500,
					animationEasingUpdate: 'quinticInOut',
					series : [
						{
							type: 'graph',
							layout: 'none',
							symbolSize: 50,
							roam: true,
							label: {
								normal: {
									show: true
								}
							},
							edgeSymbol: ['circle', 'arrow'],
							edgeSymbolSize: [4, 10],
							edgeLabel: {
								normal: {
									textStyle: {
										fontSize: 20
									}
								}
							},
							data: [{
								name: '节点1',
								x: 300,
								y: 300
							}, {
								name: '节点2',
								x: 800,
								y: 300
							}, {
								name: '节点3',
								x: 550,
								y: 100
							}, {
								name: '节点4',
								x: 550,
								y: 500
							}],
							// links: [],
							links: [{
								source: 0,
								target: 1,
								
							},  {
								source: '节点1',
								target: '节点3'
							}, {
								source: '节点2',
								target: '节点3'
							}, {
								source: '节点2',
								target: '节点4'
							}, {
								source: '节点1',
								target: '节点4'
							}],
							lineStyle: {
								normal: {
									opacity: 0.9,
									width: 2,
									curveness: 0
								}
							}
						}
					]
				};

				myChart.setOption(option);
			</script>
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