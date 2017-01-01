package com.example.service;

import com.example.domain.Book;

public interface RedisService {
	void save(Book book);
	Book findById(int id);
}
