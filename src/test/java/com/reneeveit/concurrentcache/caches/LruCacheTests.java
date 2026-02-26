package com.reneeveit.concurrentcache.caches;

import org.junit.jupiter.api.Test; //Junit 5, chose most recent stable version
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.reneeveit.concurrentcache.models.Order;

public class LruCacheTests
{
    private LruCache<String, Order> cache;
    private static Logger logger = LoggerFactory.getLogger(LruCacheTests.class);
    
    // ARRANGE
    @BeforeEach
    void setUp()
    {
        // set capacity
        cache = new LruCache<>(3);
    }
    
    @Test 
    void putAndGetValue() 
    {
        //ACT
        cache.put("A", new Order(1));
        Order result = cache.get("A");

        //ASSERT
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test 
    void returnNullForMissingKey() 
    {
        assertNull(cache.get("missing"));
    }

    @Test
    void overwriteShouldNotIncreaseSize() 
    {
        cache.put("A", new Order(1));
        cache.put("A", new Order(2));

        assertEquals(1, cache.size());
        assertEquals(2, cache.get("A").getId());
    }

    @Test 
    void respectCacheCapacity()
    {
        cache.put("A", new Order(1));
        cache.put("B", new Order(2));
        cache.put("C", new Order(3));
        cache.put("D", new Order(4));

        assertEquals(3, cache.size());
    }

    @Test 
    void respectCacheCapacityWhenCapacityIsOne()
    {
        LruCache<String, Order> smallCache = new LruCache<>(1);
        
        smallCache.put("A", new Order(1));
        smallCache.put("B", new Order(2));

        assertNull(smallCache.get("A"));
        assertNotNull(smallCache.get("B"));
        assertEquals(1, smallCache.size());
    }

    // testing eviction
    @Test 
    void evictLRUWhenFull()
    {
        // populate cache
        cache.put("A", new Order(1));
        cache.put("B", new Order(2));
        cache.put("C", new Order(3));

        // Access A to make it most recently used
        cache.get("A");

        // Add D -> should evict B
        cache.put("D", new Order(4));

        assertNull(cache.get("B"));
        assertNotNull(cache.get("A"));
        assertNotNull(cache.get("C"));
        assertNotNull(cache.get("D"));
    }

    @Test 
    void sizeNeverExceedsCapacity()
    {
        for (int i = 0; i < 100; i++) 
        {
            cache.put("K" + i, new Order(i));
            assertTrue(cache.size() <= 3);
        }
    }   

    @Test
    void nonThreadSafeLruCache_shouldFailUnderConcurrency() throws InterruptedException
    // throws when thread is waiting, sleeping, or occupied and is then interrupted (before or during) 
    {
        int capacity = 50;
        LruCache<Integer, Integer> stressTestCache = new LruCache<>(capacity);

        int threadCount = 10;//20
        int operationsPerThread = 10;//10000

        // executes tasks + lifecycle control (shutdown / futures)
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        // coordinates threads, allows one thread to wait until other threads complete work
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                Random random = new Random();
                for (int j = 0; j < operationsPerThread; j++) {

                    int key = random.nextInt(100);

                    // simulate real world usage: multiple threads reading / writing at the same time
                    if (random.nextBoolean()) {
                        // modify map and linked list
                        stressTestCache.put(key, key);
                    } else {
                        // modify order (linked list)
                        stressTestCache.get(key);
                    }
                }
                latch.countDown();
            });
        }

        // make sure stress test finishes before assertions
        latch.await();
        // no new tasks accepted
        executor.shutdown();

        // Invariants that should ALWAYS hold
        assertTrue(stressTestCache.size() <= capacity);
    }
}