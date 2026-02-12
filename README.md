# Concurrent LRU Cache

Concurrent LRU Cache implementation on SpringBoot

## Description

The goal of this project is to understand an order management system (OMS) by implementing a LRU cache with concurrency. This will also show a basic understanding of thread-safety and JUnit.

## Getting Started

### Dependencies

* Project: Maven
* Language: Java: 17
* Spring Boot: latest stable
* JUnit 5
* Dependencies:
    * Spring Web

### Installing

* Pull git branch (main)

### Executing program

* cd into root folder (where pom.xml is located: ...\springbootrepos\ConcurrentCacheProject)

then run 
```
mvn spring-boot:run
```
OR 

run mvn clean install, change the artifactId in pom.xml from 
```<artifactId>demo</artifactId> ``` to ```<artifactId>lru-cache</artifactId> ```, then run

```
java -jar target/lru-cache-0.0.1-SNAPSHOT.jar

```
* you'll see the project on http://localhost:8080
    * check the controller @RequestMapping for base path and endpoint methods URLs 


## Author
Renee Veit 

## Version History

* 0.2
    * Lightweight OMS
    * TODO: switch to "sandbox" branch before merging with main
    * See [commit change]() or See [release history]()
* 0.1
    * MVP: 3 caches (all on main branch)


## Acknowledgments

Huge shout-out to my mentors [Gabrielle Sweet](https://www.linkedin.com/in/gabrielle-sweet/), [Norman Nunley](https://www.linkedin.com/in/norman-nunley/), and [Briana Bradshaw](https://www.linkedin.com/in/brianabradshaw/)! 
