package com.reneeveit.concurrentcache.caches;

public abstract class CacheTests 
{

    void putAndGetValue(){}

    void returnNullForMissingKey(){}

    void overwriteShouldNotIncreaseSize(){}

    void respectCacheCapacity(){}

    void respectCacheCapacityWhenCapacityIsOne(){}

    void evictLRUWhenFull(){}

    void sizeNeverExceedsCapacity(){}
}
