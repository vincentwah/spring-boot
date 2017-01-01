package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.domain.Book;
import com.example.repository.BookRepository;

@Service
@CacheConfig(cacheNames={"books"})
public class BookServiceImpl implements BookService {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	@Cacheable(cacheNames={"books"},key="#author")
	public List<Book> findByAuthor(String author) {
		LOG.info("#Cache missing for "+author+" ,  fetch from DB");
		return bookRepository.findByAuthor(author);
	}

	@Override
	@Transactional
	@CacheEvict(key="#author")
	public void deleteByAuthor(String author) {
		bookRepository.deleteByAuthor(author);
	}

	@Override
	@Transactional
	@CachePut(key="#result.id")
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Cacheable("books")
	public List<Book> findAll() {
		LOG.info("#Cache missing, fetch from DB");
		return bookRepository.findAll();
	}

	@Override
	@CacheEvict(cacheNames={"books"},allEntries=true)
	public void clearCache() {
		
	}



}
