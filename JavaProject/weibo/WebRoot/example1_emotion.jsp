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
			<li><a href="example1_spread.jsp">传播分析</a></li>
			<li><a href="example1_emotion.jsp" class="current">情感分析</a></li>
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
			    	text: '情感分析',
			    	textStyle: {
			            color: '#eee'
			        }
			    },
			    tooltip : {
			        formatter: "{a} <br/>{c} {b}"
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			        }
			    },
			    series : [
			        {
			            name:'情感',
			            type:'gauge',
			            min:0,
			            max:100,
			            splitNumber:5,
			            radius: '80%',
			            axisLine: {            // 坐标轴线
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: [[0.09, 'lime'],[0.82, '#1e90ff'],[1, '#ff4500']],
			                    width: 3,
			                    shadowColor : '#fff', //默认透明
			                    shadowBlur: 10
			                }
			            },
			            axisLabel: {            // 坐标轴小标记
			                textStyle: {       // 属性lineStyle控制线条样式
			                    fontWeight: 'bolder',
			                    color: '#fff',
			                    shadowColor : '#fff', //默认透明
			                    shadowBlur: 10
			                }
			            },
			            axisTick: {            // 坐标轴小标记
			                length :15,        // 属性length控制线长
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'auto',
			                    shadowColor : '#fff', //默认透明
			                    shadowBlur: 10
			                }
			            },
			            splitLine: {           // 分隔线
			                length :25,         // 属性length控制线长
			                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			                    width:3,
			                    color: '#fff',
			                    shadowColor : '#fff', //默认透明
			                    shadowBlur: 10
			                }
			            },
			            pointer: {           // 分隔线
			                shadowColor : '#fff', //默认透明
			                shadowBlur: 5
			            },
			            title : {
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    fontSize: 20,
			                    fontStyle: 'italic',
			                    color: '#fff',
			                    shadowColor : '#fff', //默认透明
			                    shadowBlur: 10
			                }
			            },
			            detail : {
			                backgroundColor: 'rgba(30,144,255,0.8)',
			                borderWidth: 1,
			                borderColor: '#fff',
			                shadowColor : '#fff', //默认透明
			                shadowBlur: 5,
			                offsetCenter: [0, '50%'],       // x, y，单位px
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontWeight: 'bolder',
			                    color: '#fff'
			                }
			            },
			            data:[{value: 40, name: '情感值'}]
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