#-*-coding:utf-8 -*-

import urllib

#print dir(urllib)
#help(urllib.urlopen)
#gb2312 =>>>>  gbk
url = "http://www.iplaypython.com"
def callback(a,b,c):
	"""
	@a:目前为止传递的书库快数量
	@b:每个数据快的嗲小，单位byte
	@c:远程文件的大小
	"""
	down_progress = 100.0 * a * b / c
	if down_progress > 100:
		down_progress = 100
	print "%.2f%" % down_progress,        # ','让这些数据在一行中显示


#网页抓取
urllib.urlretrieve(url,"/home/cyh/桌面/abc.html",callback)



