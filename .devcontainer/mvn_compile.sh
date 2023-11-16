#!/bin/bash
cd projects/ 
cd infer && mvn clean compile package 
cd ../pmd-tools/ && mvn clean compile package 
cd ../spotbugs && mvn clean compile package

source ~/.bashrc