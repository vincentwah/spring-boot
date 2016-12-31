package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Book;
import com.example.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	@Override
	@Transactional
	public void deleteByAuthor(String author) {
		bookRepository.deleteByAuthor(author);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	

}
