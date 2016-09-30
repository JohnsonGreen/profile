#-*-coding:utf-8 -*-

import urllib

#print dir(urllib)
#help(urllib.urlopen)
#gb2312 =>>>>  gbk
url = "http://www.baidu.com"

def callback(a,b,c):
	"""
	@a:
	@b:
	@c:
	"""
	down_progress = 1000 * a * b * c
	if down_progress > 100:
		down_progress = 100
	print "%.2f%%" % down_progress,        # ','让这些数据在一行中显示


#网页抓取
urllib.urlretrieve(url,"/home/cyh/桌面/abc.html",callback)



