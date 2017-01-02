package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ComputeController {

	@Autowired
	private DiscoveryClient client;
	
	@GetMapping("/add")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        System.out.println("/add, host:" + instance.getHost() + ":"+instance.getPort()+", service_id:" + instance.getServiceId() + ", result:" + r);
        return "/add, host:" + instance.getHost() + ":"+instance.getPort()+", service_id:" + instance.getServiceId() + ", result:" + r;
        

    }
}
