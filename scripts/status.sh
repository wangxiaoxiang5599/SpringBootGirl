#!/bin/bash

PROG=girl
PIDFILE=/tmp/$PROG.pid
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
    echo "$PROG is running , PID:$PID"
else
    echo "$PROG is not running "
fi