#!/bin/bash
./gradlew clean publishToMavenLocal || exit 1

cd example-gradle
./gradlew uD
./gradlew clean build || exit 1
cd ..

cd example-maven
./mvnw versions:update-properties -DallowSnapshots=true
./mvnw clean package eclipse:eclipse || exit 1
cd ..
