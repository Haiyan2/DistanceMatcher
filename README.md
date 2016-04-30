# Distance Matcher  #

### Description ###
This project reads a JSON file and prints the list of customer information that are within the specified distance threshold.  

### Usage ###

Pre-requisites: Java 8 and maven v3.3.3 or greater.

The input file named <<customers.json>> is under the root of the project. If you would like to change the contenet, make sure the same file name is used.

To execute the main class from Maven, use the command:
```
mvn exec:java -Dexec.mainClass="distancematcher.FindMatchesWithinDistance"
```

A set of unit tests are provided.
