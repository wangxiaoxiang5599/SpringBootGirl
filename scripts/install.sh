#!/bin/bash
CURR_PATH=$(cd "$(dirname "$0")"; pwd)
APP_DIR=$(dirname $CURR_PATH)
cd $APP_DIR
chmod 700 -R *
find -name *.sh -exec chmod 500 {} \;
find -name *.exe -exec chmod 500 {} \;
find -name *.jar -exec chmod 500 {} \;
find -name *.bat -exec chmod 500 {} \;
find -name *.xml -exec chmod 600 {} \;
find -name *properties* -exec chmod 600 {} \;
find -name *.json -exec chmod 600 -R {} \;
find -name *.log -exec chmod 600 -R {} \;
find -name *.txt -exec chmod 600 -R {} \;
find -name *.class -exec chmod 500 {} \;
find -name *.so -exec chmod 500 {} \;
cd $APP_DIR/lib
chmod 500 -R *;
cd $APP_DIR/bin/
dos2unix *.sh