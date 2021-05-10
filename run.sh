#!/usr/bin/env bash
chapter=$1
section=$2
exercise=$3

shift
shift
shift

java -cp $JAVA_VENDOR:$JAVA_OUTPUT_DIR algsex.chapter$chapter.section$section.Exercise$exercise $@