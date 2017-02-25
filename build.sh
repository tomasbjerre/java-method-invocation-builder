#!/bin/bash
./gradlew clean gitChangelogTask eclipse build install -i

cd example-gradle
./gradlew clean cE build eclipse
./gradlew clean cE build eclipse
cd ..

cd example-maven
mvn clean eclipse:eclipse package eclipse:eclipse
cd ..
