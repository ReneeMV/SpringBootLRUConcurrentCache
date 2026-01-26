// package namespace where file lives, prevents naming collisions, controls organization
package com.example.demo;
// imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// HTTP GET requests
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// tells Spring where to start scanning for components 
@SpringBootApplication
// auto converts return values -> HTTP responses (JSON or text), no manual serialization needed
@RestController
public class DemoApplication 
{
    // run mvn spring-boot:run in directory with pom.xml on http://localhost:8080
    public static void main(String[] args) 
	{
		//.run starts app, web server, loads configs -> handle requests
		SpringApplication.run(DemoApplication.class, args);
		
    }
	// when a GET request hits this URL, call this method
    @GetMapping("/hello")
	// @RequestParam binds a query parameter (name) from URL to this value, handles defauls / type conversion
	// 
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
}