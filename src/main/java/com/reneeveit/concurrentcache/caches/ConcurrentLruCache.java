package com.reneeveit.concurrentcache.caches;

public class ConcurrentLruCache<K,V> implements Cache<K,V> {
    private final LruCache<K, V> lruCache;

    public ConcurrentLruCache(int capacity) {
        this.lruCache = new LruCache<>(capacity);
    }

    @Override
    public V getValue(K key) {
        return lruCache.get(key);
    }

    @Override
    public void setKvp(K key, V value) {
        lruCache.put(key, value);
    }

    @Override
    public int size() {
        return lruCache.size();
    }
}
