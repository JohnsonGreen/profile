#-*-coding:utf-8 -*-

# import urllib

# #print dir(urllib)
# #help(urllib.urlopen)
# #gb2312 =>>>>  gbk
# url = "http://www.iplaypython.com"
# def callback(a,b,c):
# 	"""
# 	@a:目前为止传递的书库快数量
# 	@b:每个数据快的嗲小，单位byte
# 	@c:远程文件的大小
# 	"""
# 	down_progress = 100.0 * a * b / c
# 	if down_progress > 100:
# 		down_progress = 100
# 	print "%.2f%" % down_progress,        # ','让这些数据在一行中显示


# #网页抓取
# urllib.urlretrieve(url,"/home/cyh/桌面/abc.html",callback)

# #————————————————————————————————————————————————————————————————


# import urllib 

# url = "http://www.tudou.com"
# info = urllib.urlopen(url).info()
# print info.getparam('charset')   #返回网页编码类型，而有的网页没有在头部设置网页编码

# print info.getparam('Last-Modified') 

# #————————————————————————————————————————————————————————————————




# import urllib
# import chardet  #字符集检测

# def automatic_detect(url):
# 	'''
#       自动检测网页编码
# 	'''
# 	content = urllib.urlopen(url).read()
# 	result = chardet.detect(content)
# 	encoding = result['encoding']
# 	return encoding


# urls = ["http://www.iplaypython.com",
#         "http://www.baidu.com",
#         "http://www.163.com",
#         "http://www.jd.com",
#         "http://www.dangdang.com"
#          ]
         
# for url in urls:
# 	print url,automatic_detect(url)

# #content = urllib.urlopen(url).read()
# # print chardet.detect(content)  #返回:{'confidence': 0.938125, 'encoding': 'utf-8'}
# #————————————————————————————————————————————————————————————————


# import urllib2

# url = "http://blog.csdn.net/happydeer"



# # #模仿浏览器访问网站，防止有些网站限制爬虫
# # req.add_header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
# # req.add_header("GET",url)
# # req.add_header("Host","blog.csdn.net")
# # req.add_header("Referer","http://blog.csdn.net/")

# my_headers={
# 	"User-Agent": " Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36",
# 	"GET": url,
# 	"Host":"blog.csdn.net",
# 	"Referer":"http://blog.csdn.net/"
# }

# req = urllib2.Request(url,headers=my_headers)     #可接受第二个参数


# html = urllib2.urlopen(req)


# #print html.read()
# print req.headers.items()

# #————————————————————————————————————————————————————————————————

#对函数的封装
import urllib2
import random

url = "http://blog.csdn.net/happydeer"
my_headers=[
 	" Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36"
]



def get_content(url,headers):
	"""
	@获取403禁止访问的网页
	"""
	random_header = random.choice(headers)
	req = urllib2.Request(url)
	req.add_header("User-Agent",random_header)
	req.add_header("Host","blog.csdn.net")
	req.add_header("Referer","http://blog.csdn.net/")
	req.add_header("GET",url)

	content = urllib2.urlopen(req).read()
	return content

print get_content(url,my_headers)