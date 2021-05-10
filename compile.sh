#!/usr/bin/env bash
rm -rf $JAVA_OUTPUT_DIR;
find $JAVA_SRC_DIR -name "*.java" | xargs javac -cp $JAVA_VENDOR -d $JAVA_OUTPUT_DIR