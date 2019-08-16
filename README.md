## Overview
This repository contains a web crawler to crawl upto a specified depth (max limits apply) written in Java, Spring boot and Gradle.

## Building & Running The Program
In order to build the program, the following is required

- Java 8 JDK
- Gradle 5.4.x

The web crawler service is built by ; 
$ ./gradlew clean build && ./gradlew bootRun

Now the service is available at:
http://localhost:8080/

A quick test over the PostMan Rest Client:

http://localhost:8080/url=http://www.rescale.com/&depth=1