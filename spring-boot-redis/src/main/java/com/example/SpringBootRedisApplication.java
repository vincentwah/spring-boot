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
import com.example.service.RedisService;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
@EnableCaching
public class SpringBootRedisApplication implements CommandLineRunner{

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootRedisApplication.class);

	@Autowired
	private BookService bookService;
	
	@Autowired
	private RedisService redisService;
	

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		bookService.clearCache();
		
		Book b1 = new Book("Waiting","Kevin Henkes");
		Book b2 = new Book("Roller Girl","Victoria Jamieson");
		Book b3 = new Book("The Detective's Assistant","Kate Hannigan");
		
		bookService.save(b1);
		bookService.save(b2);
		bookService.save(b3);
		
		
		List<Book> books = bookService.findByAuthor(b1.getAuthor());
		LOG.info("#Book written by "+b1.getAuthor()+" "+books.toString());
		
		bookService.deleteByAuthor(b1.getAuthor());
		LOG.info("#Deleting book "+b1.getAuthor());

		bookService.findAll();
		bookService.findAll();

		
		Book b4 = new Book("Waiting","Kevin Henkes");
		b4.setId(9999);
		redisService.save(b4);
		
		LOG.info("#Fetching book id 9999 "+redisService.findById(9999));
		
	}
}
