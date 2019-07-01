package com.gahlls.example.jdbctemplate.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gahlls.example.jdbctemplate.model.Book;
import com.gahlls.example.jdbctemplate.repository.mapper.BookRowMapper;

@Repository
public class BookRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Book> findAll() {
		return namedParameterJdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
	}
	
	public Optional<Book> findById(long id) {
		return namedParameterJdbcTemplate.query("SELECT * FROM book WHERE id = :id", new MapSqlParameterSource()
				.addValue("id", id), new BookRowMapper())
				.stream()
				.filter(Objects::nonNull)
				.findFirst();
	}
	
	public int deleteById(long id) {
		return namedParameterJdbcTemplate.update("DELETE FROM book WHERE id = :id", new MapSqlParameterSource()
				.addValue("id", id));
	}
	
	public int insert(Book book) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO book  			");
		sql.append(" (id, title, price) 		");
		sql.append(" values(:id, :title, :price) ");
		
		return namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource()
				.addValue("id", book.getId())
				.addValue("title", book.getTitle())
				.addValue("price", book.getPrice()));
	}
	
	public int update(Book book) {
		return namedParameterJdbcTemplate.update("UPDATE book SET title = :title, price = :price WHERE id = :id",  
				new MapSqlParameterSource()
					.addValue("id", book.getId())
					.addValue("title", book.getTitle())
					.addValue("price", book.getPrice()));
	}
}
