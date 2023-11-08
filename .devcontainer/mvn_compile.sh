#!/bin/bash
cd projects/ 
cd infer && mvn clean compile package 
cd ../pmd && mvn clean compile package 
cd ../spotbugs && mvn clean compile package