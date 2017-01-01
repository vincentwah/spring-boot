package com.example.service;

import java.util.List;

import com.example.domain.Book;

public interface BookService {
	
	Book save(Book book);
	List<Book> findAll();
	List<Book> findByAuthor(String author);
	void deleteByAuthor(String author);
	void clearCache();
	
}
