#!/bin/bash

CURR_PATH=$(cd "$(dirname "$0")"; pwd)

APP_DIR=$(dirname $CURR_PATH)

cd $APP_DIR

PROG=girl
PIDFILE=/tmp/$PROG.pid

if [ ! -d $APP_DIR/logs/dump ]; then
    mkdir -p $APP_DIR/logs/dump
fi

if [ ! -d $APP_DIR/logs/gc/backup ]; then
    mkdir -p $APP_DIR/logs/gc/backup
fi

touch $APP_DIR/logs/gc/GcLog

JAVA_OPS="-Xmx4096m -XX:+UseG1GC -XX:MaxGCPauseMillis=200  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$APP_DIR/logs/dump -Xloggc:$APP_DIR/logs/gc/GcLog -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Dorg.terracotta.quartz.skipUpdateCheck=true"
RUN_JAR=girl-*.jar

status() {
    if [ -f $PIDFILE ]; then
        PID=$(cat $PIDFILE)
        if [ ! -x /proc/${PID} ]; then
            return 1
        else
            return 0
        fi
    else
        return 1
    fi
}


status
RETVAL=$?
if [ $RETVAL -eq 0 ]; then
    echo "$PIDFILE exists, process is already running or crashed"
    exit 1
fi
echo "Starting $PROG ..."
java -server $JAVA_OPS -jar $RUN_JAR >/dev/null &
RETVAL=$?
if [ $RETVAL -eq 0 ]; then
    echo "$PROG is started "
    echo $! > $PIDFILE
    exit 0
else
    echo "$PROG start failed"
    rm -f $PIDFILE
    exit 1
fi
