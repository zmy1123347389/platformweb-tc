#!/bin/sh
#
#用于监管微服务启动
#部署时需根据实际情况修改该脚本内相应信息
################################


ADATE=`date +%Y%m%d%H%M%S`
APP_NAME=platformweb-tc-1.2.6

APP_HOME=`pwd`
dirname $0|grep "^/" >/dev/null
if [ $? -eq 0 ];then
   APP_HOME=`dirname $0`
else
    dirname $0|grep "^\." >/dev/null
    retval=$?
    if [ $retval -eq 0 ];then
        APP_HOME=`dirname $0|sed "s#^.#$APP_HOME#"`
    else
        APP_HOME=`dirname $0|sed "s#^#$APP_HOME/#"`
    fi
fi

if [ ! -d "$APP_HOME/logs" ];then
  mkdir $APP_HOME/logs
fi

LOG_PATH=logs/$APP_NAME-$ADATE.log
GC_LOG_PATH=logs/gc-$APP_NAME-$ADATE.log

#JVM参数
JVM_OPTS="-Dname=$APP_NAME -Ddefault.client.encoding=UTF-8 -Ddefault.override.encoding=UTF-8 -Dfile.encoding=UTF-8 -Duser.region=CH  -Duser.timezone=Asia/Shanghai -Xms512M -XX:PermSize=256M -XX:MaxPermSize=1024m -Xmx1024m  -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$GC_LOG_PATH -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"
JAR_FILE=$APP_NAME.jar
pid=0

start(){
  checkpid
  if [ ! -n "$pid" ]; then
	chmod +x $JAR_FILE
	nohup java -jar $JVM_OPTS $JAR_FILE > $LOG_PATH 2>&1 &
    echo "---------------------------------"
    echo "$APP_NAME start..............."
    echo "---------------------------------"
    sleep 2s
    tail -f $LOG_PATH
  else
      echo "$APP_NAME is runing PID: $pid"   
  fi

}

status(){
   checkpid
   if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
   else
     echo "$APP_NAME is runing PID: $pid"
   fi 
}

checkpid(){
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
    else
      echo "$APP_NAME stop..."
      kill -9 $pid
    fi 
}

restart(){
    stop 
    sleep 1s
    start
}

case $1 in  
          start) start;;  
          stop)  stop;; 
          restart)  restart;;  
          status)  status;;   
              *)  echo "require start|stop|restart|status"  ;;
esac 
