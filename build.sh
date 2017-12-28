#!/bin/bash
./gradlew clean gitChangelogTask eclipse build install -i || exit 1

cd example-gradle
./gradlew clean cE build eclipse || exit 1
./gradlew clean cE build eclipse || exit 1
cd ..

cd example-maven
mvn clean eclipse:eclipse package eclipse:eclipse || exit 1
cd ..
