package com.gahlls.example.jdbctemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gahlls.example.jdbctemplate.model.Book;
import com.gahlls.example.jdbctemplate.repository.BookRepository;

@SpringBootApplication
public class JdbctemplateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbctemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("Inserting -> {}", repository.insert(new Book(10010L, "Brave New Word", 50.00)));
		logger.info("Inserting -> {}", repository.insert(new Book(10012L, "Blade Runner", 30.00)));
		
		logger.info("Book id 10001 -> {}", repository.findById(10010L));
		
		logger.info("Book id 10012L -> {}", repository.findById(10012L));

		logger.info("Update 10003 -> {}", repository.update(new Book(10010L, "Brave New Word", 35.00)));

		repository.deleteById(10012L);

		logger.info("All users -> {}", repository.findAll());
	}
}
