package com.reneeveit.concurrentcache.caches;

// import org.junit.Assert;
// import org.junit.Test;
import org.junit.jupiter.api.Test; //Junit 5, chose most recent stable version
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.locks.ReentrantLock;

import com.reneeveit.concurrentcache.models.Order;


public class LruCacheTests {

    // don't want other folks messing with the lock
	private final ReentrantLock lock = new ReentrantLock();

    // set capacity
    private final LruCache<String, Order> cache = new LruCache(3);

    
    // make sure eviction works as expected
    @Test
    void evictsLeastRecentlyUsedEntryWhenCapacityExceeded() {
        LruCache<String, Order> cache = new LruCache<>(3);

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
    
}
