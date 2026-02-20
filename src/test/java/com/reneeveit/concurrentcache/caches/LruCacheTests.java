package com.reneeveit.concurrentcache.caches;

import org.junit.jupiter.api.Test; //Junit 5, chose most recent stable version
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import com.reneeveit.concurrentcache.models.Order;

public class LruCacheTests
{
    private LruCache<String, Order> cache;
    
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
}