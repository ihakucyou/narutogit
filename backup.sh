me:    mysql_backup.sh 
# Describe:    Used for database backup
# Revision:    1.0
# Date:        2021/01/07
# Update       2021/12/10 增加--skip-opt 参数，防止锁表，增加忽略表配置，在同级目录增加ignoreTables.txt 文件，文本格式为--ignore-table=sx_hlht.ge_t_avoid_org_info
# Author:      yanch

# 设置mysql的登录用户名和密码(根据实际情况填写)
db_host="127.0.0.1" 
db_port=3306
db_user="root" 
db_pwd="mYsQl8&!0%" 
backup_path="/data/mysqlBack"
#数据库名,可以定义多个数据库，中间以空格隔开，如：test test1 test2 【不建议多个库】
db_name='shand_expert'

output_type='view,function,procedure,event,trigger'  
today=`date +"%Y%m%d-%H%M%S"` 
data_file="$backup_path/$db_name$today.sql.gz"
object_file="$backup_path/obj_$db_name$today.sql" 
mysql_path="$backup_path/scripts"
log_file=${mysql_path}/mysql_backup.log
mysql_cmd="mysql -u${db_user} -p${db_pwd} -h${db_host} -P${db_port}" 
mysqldump_cmd="mysqldump -u${db_user} -p${db_pwd} -h${db_host} -P${db_port} --databases ${db_name} --no-create-db --skip-opt -q -e `cat ignoreTables.txt`" 

#Require root to run this script. 
[ $(id -u) -gt 0 ] && echo "请用root用户执行此脚本！" && exit 1 

[ -d $backup_path ] || mkdir -p $backup_path 

[ -d $mysql_path ] || mkdir -p $mysql_path 

function mysql_backup()  
{
	echo "" 
  echo -e "\033[33m***********************************************mysql数据库备份****************************************************\033[0m"  
  
  echo -e "\033[36m**************备份数据库数据到$data_file 开始时间：[$today]**************\033[0m" 
  #最简单的整库备份，不包含bin-log备份
  echo "执行命令：$mysqldump_cmd -R -E > $data_file"
  $mysqldump_cmd -R -E | gzip > $data_file 
  
  if [ $? -eq 0 ];then 
    #action "[$today]>>>完成数据库${db_name}数据备份" /bin/true 
    echo "[$today]>>>完成数据库${db_name}数据备份" >> ${log_file} 
  else  
    #action "[$today]>>>数据库${db_name}备份失败，请检查相关配置！" /bin/false 
    echo "[$today]>>>数据库${db_name}备份失败，请检查相关配置！" >> ${log_file} 
    exit 1 
  fi
  
  #清理过期备份 
  find ${backup_path}  -mtime +10  -type f -name '*.gz' -exec rm -f {} \;
   
  if [ $? -eq 0 ];then 
    #action "[$today]>>>完成数据库${db_name}过期备份清理" /bin/true
    echo "[$today]>>>完成数据库${db_name}过期备份清理" >> ${log_file} 
  else  
    #action "[$today]>>>数据库${db_name}过期备份清理失败，请检查相关配置！" /bin/false
    echo "[$today]>>>数据库${db_name}过期备份清理失败，请检查相关配置！" >> ${log_file} 
    exit 1 
  fi 
   
  echo -e "\033[33m**********************************************完成${db_name}数据库备份**********************************************\033[0m" 
	echo "mysql地址：${db_host} "
	echo "mysql端口：${db_port} "
	echo "mysql实例名：${db_name} "
	echo "数据备份文件：${data_file} "
	echo "定义备份文件：${object_file} "
  echo -e "\e[1;31m 日志文件输出在log.file文件中 \e[0m" 
  echo -e "\033[33m*******************************************************************************************************************\033[0m" 
  echo "" 
	
}
mysql_backup

#echo "Backup Begin Date:" $(date +"%Y-%m-%d %H:%M:%S")
#echo "---->执行命令:mysqldump -h${mysql_host} -u${mysql_user} -P${mysql_port} -p${mysql_password} $dbname | gzip > ${backup_dir}/$dbname_$(date +%Y%m%d_%H%M%S).sql.gz" 
# 备份全部数据库
#mysqldump -h${mysql_host} -u${mysql_user} -P${mysql_port} -p${mysql_password} $dbname | gzip > ${backup_dir}/$dbname_$(date +%Y%m%d_%H%M%S).sql.gz

#删除10天前备份的数据
#find $backup_dir -mtime +10 -name "*.gz" -exec rm -rf {} \;
#echo $dbname "Backup Succeed Date:" $(date +"%Y-%m-%d %H:%M:%S")
#exec echo debug



