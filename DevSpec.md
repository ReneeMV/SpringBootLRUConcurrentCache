# Concurrent LRU Cache Dev Spec

## Overview and Motivation
During the interview process, a hiring manager recommended I implement an LRU cache with concurrency to better understand the problem space of fixed income trading. The goal of this side project is to simulate an order management system, monitor / log how concurrency impacts latency, and build credible judgement by reasoning through tradeoffs. 

### References
- mvn logs: https://mvnrepository.com/artifact/org.slf4j/slf4j-api/2.0.17
- quicker way to run app: java [$JAVA_OPTS] -jar [jar_file_name].jar (see README for detailed instructions)

### Requirements
* demonstrate thread-safety (and the absence of deadlocks) with accessing and updating data
* create JUnit tests that show concurrency safety

## Scope
### In Scope Features
v1. 
* Show tradeoffs between 
    - non-thread-safe LRU
    - manually locked LRU
    - and concurrency based LRU
* Include benchmarks (metrics), JUnit tests, and stress tests (Postman / Thunderclient to spawn parallel requests) to measure differences 

### Out of Scope Feautres
* UI
* distributed system
v2. OMS + TTL (usually used alongside LRU eviction policy)
v3. Incorporate databases (SQL) to show OCC
v4. Incorporate "redlock" (TODO: research)

## Questions / TODOs
v1.
* how to write concurrency tests / spawn 100 threads?
    - asked chat gpt. TODO: outline in spec
* JMeter for stress testing?
* 2/11/26 - research ReentrantLock and synchronized blocks in Java, start Concurrent Lru Cache Implementation

v2.
- Domain model(s)? (What does an order book look like? What does an order consist of?)

## Testing 
### Standard Cache Tests
* Abstract CacheContractTest will have the following:
    - putAndGetValue
    - returnNullForMissingKey
    - respectCacheCapacity
        - respectCacheCapacityWhenCapacityIsOne
    - evictLRUWhenFull
    - sizeNeverExceedsCapacity


### Race Condition Test(s)
* These will test collisons (two threads try to add the same order (key) to cache at the same time) amd will fail for the non-thread-safe cache
    - lostUpdateUnderConcurrency 

### Concurrency Tests
* For manually locked and concurrent versions
    - concurrentPutsShouldNotCorruptState
    - concurrentGetsAndPutsShouldMaintainCorrectEviction
    - noDeadlockUnderHighContention
* TODO: more deadlock and performance comparision tests

### Local Testing
JUnit

## E2E Testing
run ``` mvn spring-boot:run``` then test via http://localhost:8080

## Monitoring
### Logs
TODO: understand how to use SLF4J API Module log package through maven
* log incoming requests and compare to cache updates 

## Deployment
TODO: SpringBoot? Docker containers?

## Key Architectural Decisions
* The "caches" folder will have the Cache.java interface alongside different implementations of the cache
    1. non-thread-safe LRU
    2. manually thread-safe LRU
    3. thread-safe implementation using concurrent data structures
* The "controller" folder will store different controllers
    * TODO: what other types of controllers?
* The "models" folder will store the data structures (orders, instruments, etc.)
    * TODO: finish "study guide" write up of OMS to gain clarity on which data structures I need and why
//NOTE: other controllers and domain model clarification will be part of v2

## Technical Design
I intentionally created the initial implementation as "unsafe" to demonstrate my understanding of specific failure modes. By proving the system fails under specific conditions, I get to show my knowledge of the weak points in my design.

TODO (for v2) FLESH OUT:
### What data I'm dealing with  

### How often it changes

### Where data is coming from

### Common Scenarios
