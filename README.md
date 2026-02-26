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

run 
```
mvn clean install
```
then change the artifactId in pom.xml from 
```<artifactId>demo</artifactId> ``` to ```<artifactId>lru-cache</artifactId> ```, then run

```
java -jar target/lru-cache-0.0.1-SNAPSHOT.jar

```
* you'll see GET requests on http://localhost:8080, but you must use a API tool like ThunderClient or Bruno for the POST requests
    * check the controller @RequestMapping for base path and endpoint methods URLs 

#### PRO-TIP
Spring doesn't "hot-reload" by default. After making changes you must stop (Ctrl-C) and restart (mvn spring-boot:run)


## Author
Renee Veit 

## Version History
* 0.3
    * include Linters and formatting (ESLint, Prettier)
    * add UI via REACT. Think through user roles, gating, and security considerations as well as authentication flows and accessibility checks
* 0.2
    * Lightweight OMS
    * See [commit change]() or See [release history]()
* 0.1
    * TODO: switch to "sandbox" branch before merging with main
    * MVP: 3 caches (all on main branch)


## Acknowledgments

Huge shout-out to my mentors [Briana Bradshaw](https://www.linkedin.com/in/brianabradshaw/), [Gabrielle Sweet](https://www.linkedin.com/in/gabrielle-sweet/), and [Norman Nunley](https://www.linkedin.com/in/norman-nunley/)! 
