package com.example.demo;

public class LruCacheWrapper<K,V> implements Cache<K,V> {
    private final LruCache<K, V> delegate;

    public LruCacheWrapper(int capacity) {
        this.delegate = new LruCache<>(capacity);
    }

    @Override
    public V getValue(K key) {
        return delegate.get(key);
    }

    @Override
    public void setKvp(K key, V value) {
        delegate.put(key, value);
    }

    @Override
    public int size() {
        return delegate.size();
    }
}
