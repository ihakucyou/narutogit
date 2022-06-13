#!/bin/sh
d=`date +%Y-%m-%d`
d7=`date -d'7 day ago' +%Y-%m-%d`
cp /opt/tomcat/apache-tomcat-G2/logs/catalina.out  /opt/tomcat/apache-tomcat-G2/logs/catalina.${d}.log
echo "" > /opt/tomcat/apache-tomcat-G2/logs/catalina.out
rm -rf /opt/tomcat/apache-tomcat-G2/logs/catalina.${d7}.log