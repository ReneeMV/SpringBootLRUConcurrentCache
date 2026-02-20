package com.reneeveit.concurrentcache.controllers;

import com.reneeveit.concurrentcache.caches.LruCache;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
// base URL for all endpoints in controller (common URL prefix for grouped endpoints)
@RequestMapping("/orders")
public class OrderController 
{
    private final LruCache<String, String> cache = new LruCache<>(3);

    @PostMapping("/{orderId}") // /orders/{orderId}
    // @PathVariable pull value from URL -> method variable
    public String getOrder(@PathVariable String orderId) 
    {
        // return value if key exists, else compute using lambda, store, and return (lazy population: only computed once, on demand)
        return cache.computeIfAbsent(
            orderId,
            // lambda expression: return "Order-" + id + "-DETAILS"
            id -> "Order-" + id + "-DETAILS"
        );
    }

    // http://localhost:8080/orders/cache
    @GetMapping("/cache")
    public Map<String, String> cacheContents() 
    {
        return cache;
    }
}
