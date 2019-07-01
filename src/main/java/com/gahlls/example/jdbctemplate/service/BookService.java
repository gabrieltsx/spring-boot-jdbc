package com.gahlls.example.jdbctemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gahlls.example.jdbctemplate.model.Book;
import com.gahlls.example.jdbctemplate.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("asd"));
	}
	
	public int insertBook(Book book) {
		return bookRepository.insert(book);
	}
	
	public int deleteBook(Long id) {
		this.findById(id);
		return bookRepository.deleteById(id);
	}
	
	public int updateBook(Long id, Book book) {
		Book bookCurrent = this.findById(id);
		bookCurrent.setPrice(book.getPrice());
		bookCurrent.setTitle(book.getTitle());
		return bookRepository.update(bookCurrent);
	}
}
