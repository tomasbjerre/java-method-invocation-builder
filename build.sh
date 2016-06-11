#!/bin/bash
./gradlew clean gitChangelogTask eclipse build install -i

cd example-gradle
./gradlew clean cE eclipse build
./gradlew clean cE eclipse build
./gradlew build
cd ..

cd example-maven
mvn clean package eclipse:eclipse
mvn clean package eclipse:eclipse
mvn clean package eclipse:eclipse
cd ..

