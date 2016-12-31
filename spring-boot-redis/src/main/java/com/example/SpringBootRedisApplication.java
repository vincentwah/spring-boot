package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.domain.Book;
import com.example.service.BookService;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
@EnableCaching
public class SpringBootRedisApplication implements CommandLineRunner{

	@Autowired
	private BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Book b1 = new Book("Waiting","Kevin Henkes");
		Book b2 = new Book("Roller Girl","Victoria Jamieson");
		Book b3 = new Book("The Detective's Assistant","Kate Hannigan");
		
		bookService.save(b1);
		bookService.save(b2);
		bookService.save(b3);
		
	}
}
