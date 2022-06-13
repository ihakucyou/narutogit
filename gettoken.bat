@echo off
start chrome.exe http://117.73.254.120:19081/G2/extract/login.do?systemId=402c81c8573c558401573d4516bb001c
ping -n 60 127.0.0.1
start chrome.exe http://www.baidu.com
ping -n 60 127.0.0.1 
taskkill /im chrome.exe.exe /f