#!/usr/bin/env python3
# python3 -m  pip install easyocr //使用pip工具进行easyocr的安装
import easyocr
import os
# print(os.getcwd())
current_path = os.path.dirname(__file__)  # 设置相对路径
print(current_path)
#合同图片
test_images1 = (current_path+'/source/T1.jpg')

test_images2 = (current_path+'/source/T2.jpg')

test_images3 = (current_path+'/source/T3.jpg')

test_images4 = (current_path+'/source/T4.jpg')
#设置识别中英文两种语言
reader = easyocr.Reader(['ch_sim','en'], gpu =  False) # need to run only once to load model into memory
#对T1进行测试
# result = reader.readtext((test_images1), detail = 0)
# print(result)
#对于T2进行测试
# result = reader.readtext((test_images2), detail = 0)
# print(result)
#对于T3进行测试
# result = reader.readtext((test_images3), detail = 0)
# print(result)
#对于T4进行测试
result = reader.readtext((test_images4), detail = 0)
print(result)
