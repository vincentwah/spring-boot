package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ConsumerController {
	@Autowired
    private RestTemplate restTemplate;
	@GetMapping(value = "/add")
	@HystrixCommand(fallbackMethod = "addServiceFallback")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a="+a+"&b="+b, String.class).getBody();
    }
	
	public String addServiceFallback(Integer a, Integer b) {
        return "service is not available";
    }
}
