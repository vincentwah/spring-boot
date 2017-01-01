package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.domain.Book;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	public void save(Book book) {
		redisTemplate.opsForValue().set(String.valueOf(book.getId()),book);
	}
	@Override
	public Book findById(int id) {
		Book book = (Book)redisTemplate.opsForValue().get(String.valueOf(id));
		return book;
	}

}
