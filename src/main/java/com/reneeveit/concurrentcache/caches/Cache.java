package com.reneeveit.concurrentcache.caches;

public interface Cache<K,V> {
    //accessor: returns the value
    V getValue(K key);
    //mutator: updates the value
    void setKvp(K key, V value);

    int size();
}
