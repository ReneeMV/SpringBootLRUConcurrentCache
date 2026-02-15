package com.reneeveit.concurrentcache.caches;

public abstract class CacheTests {
    void putAndGetValue(){}

    void returnNullForMissingKey(){}

    void respectCacheCapacity(){}

    void respectCacheCapacityWhenCapacityIsOne(){}

    void evictLRUWhenFull(){}

    void sizeNeverExceedsCapacity(){}
}
