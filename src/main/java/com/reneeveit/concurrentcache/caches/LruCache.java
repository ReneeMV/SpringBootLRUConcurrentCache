package com.reneeveit.concurrentcache.caches;

// preserver *ORDER* for eviction
import java.util.LinkedHashMap;
//interface for *LOOKUPS*: can use HashMap, ConcurrentHashMap, etc.
import java.util.Map;

public class LruCache<K, V> extends LinkedHashMap<K, V> 
{
    // set capacity on initialization
    private final int capacity;

    public LruCache(int capacity)
    {       
        // call superclass methods, resize when 75% full (balance memory and speed), entries by access NOT insertion (FIFO)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // LinkedHashMap does 90% of the work (reuse well tested data structure), I need to define when to evict
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) 
    {
        return size() > capacity;
    }
    
}
