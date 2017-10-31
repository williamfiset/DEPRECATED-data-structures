#!/bin/sh                                                                     
javac *.java                                                                  
javac -d . -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar tests/*Test.java
java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar org.junit.runner.JUnitCore SizeBalancedTreeTest
rm *.class 2> /dev/null
