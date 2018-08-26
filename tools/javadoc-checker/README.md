Javadoc-Checker
===============

A helper to check whether the docs contain only valid javadoc references.

Requirements
------------

* Java 10
* Maven 3.5

Usage
-----

Either use a precompiled jar:

````bash
mvn clean package
java -Dlog4j.skipJansi=false -jar target/javadock-checker....jar [path to SpongeDocs/source]
````

or run it directly from the command lines:

````bash
mvn
````