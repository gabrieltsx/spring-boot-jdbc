package com.gahlls.example.jdbctemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gahlls.example.jdbctemplate.model.Book;
import com.gahlls.example.jdbctemplate.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAll(){
		return ResponseEntity.ok(this.bookService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getOne(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.bookService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Integer> insertBook(@RequestBody Book book){
		return ResponseEntity.ok(bookService.insertBook(book));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteBook(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.bookService.deleteBook(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateBook(@PathVariable("id") Long id, @RequestBody Book book){
		return ResponseEntity.ok(this.bookService.updateBook(id, book));
	}
}
