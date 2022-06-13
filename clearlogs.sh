#/bin/sh
cd  /opt/tomcat/apache-tomcat-G2/testclear/
echo " " > catalina.out
curDate=$(date "+%Y-%m-%d")
cd /opt/tomcat/apache-tomcat-G2/testclear/
sleep 2
tar -czvf $curDate.tar.gz ./*
sleep 5
mv $curDate.tar.gz /opt/tomcat/apache-tomcat-G2/logsHistory/
sleep 10
ls  | grep -v catalina.out |xargs rm -rf;
ls | grep -r [0-9] |xargs rm -rf;
