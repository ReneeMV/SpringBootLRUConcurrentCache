# Concurrent LRU Cache Dev Spec

## Overview and Motivation
During the interview process, a hiring manager recommended I implement an LRU cache with concurrency to better understand the problem space of fixed income trading. The goal of this side project is to simulate an order management system, monitor / log how concurrency impacts latency, and build credible judgement by reasoning through tradeoffs. 

### References

### Requirements
* demonstrate thread-safety (and the absence of deadlocks) with accessing and updating data
* create JUnit tests that show concurrency safety

### In Scope Features
TODO

### Out of Scope Feautres
TODO

## Testing, Monitoring, and Deployment

### Local Testing
JUnit

## E2E Testing
run ``` mvn spring-boot:run``` then test via http://localhost:8080

### Logs
TODO: how to monitor latency

### Deployment
SpringBoot?

## Key Architectural Decisions
* The "caches" folder will have the Cache.java interface alongside different implementations of the cache
    1. non-thread-safe LRU
    2. manually thread-safe LRU
    3. thread-safe implementation using concurrent data structures
* The "controller" folder will store different controllers
    * TODO: what other types of controllers?
* The "models" folder will store the data structures (orders, instruments, etc.)
    * TODO: finish "study guide" write up of OMS to gain clarity on which data structures I need and why

## Technical Design

### What data I'm dealing with

### How often it changes

### Where data is coming from

### Common Scenarios