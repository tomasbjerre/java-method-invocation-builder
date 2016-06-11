#!/bin/bash
./gradlew clean

cd example-gradle
./gradlew clean cE
cd ..

cd example-maven
mvn clean
cd ..

