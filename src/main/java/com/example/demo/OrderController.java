package com.example.demo;
//learned: Spring doesn't "hot-reload" by default, must stop (Ctrl-C) and restart (mvn spring-boot:run) 

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
// base URL for all endpoints in controller (common URL prefix for grouped endpoints)
@RequestMapping("/orders")
public class OrderController 
{
    /// TODO: use cache interface instead
    //private final Cache<String, Order> cache;

    private final LruCache<String, String> cache =
            new LruCache<>(3);

    // when a GET request hits this URL, call this method
    @GetMapping("/{orderId}") // /orders/{orderId}
    // pull value from URL -> method variable
    public String getOrder(@PathVariable String orderId) 
    {
        // return value if key exists, else compute using lambda, store, and return (lazy population: only computed once, on demand)
        return cache.computeIfAbsent(
            orderId,
            // lambda expression: return "Order-" + id + "-DETAILS"
            id -> "Order-" + id + "-DETAILS"
        );
    }

    @GetMapping("/cache")
    public Map<String, String> cacheContents() 
    {
        return cache;
    }
}
