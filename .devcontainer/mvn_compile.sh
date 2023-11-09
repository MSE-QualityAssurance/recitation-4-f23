#!/bin/bash
cd projects/ 
cd infer && mvn clean compile package 
cd ../pmd/torngest4ds/torngest && mvn clean compile package 
cd ../../../spotbugs && mvn clean compile package