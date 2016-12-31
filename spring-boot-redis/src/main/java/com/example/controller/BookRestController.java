package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Book;
import com.example.service.BookService;

@RestController
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> findAll(){
		return new ResponseEntity<List<Book>>(bookService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		return new ResponseEntity<Book>(bookService.save(book),HttpStatus.OK);
	}
	
	@PostMapping("/book/{author}")
	public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable("author") String author){
		return new ResponseEntity<List<Book>>(bookService.findByAuthor(author),HttpStatus.OK);
	}
}
