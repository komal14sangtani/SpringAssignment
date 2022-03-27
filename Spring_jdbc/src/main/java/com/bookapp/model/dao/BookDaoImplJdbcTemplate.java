package com.bookapp.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class BookDaoImplJdbcTemplate implements BookDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDaoImplJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "select * from books";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
	}

	@Override
	public void addBook(Book book) {
		String sql = "INSERT INTO books (title, author, price ) VALUES ( ?, ?,?)";
		 jdbcTemplate.update(sql, new Object[] { book.getTitle(),book.getAuthor(), book.getPrice()});
	}

	@Override
	public void deleteBook(int id) {
		String sql = "delete from books where id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public void updateBook(int id, Book book) {
		String sql = "update books set price =? where id=?";
		jdbcTemplate.update(sql, new Object[] { book.getPrice(), id });
	}

	@Override
	public Book getBookById(int id) {
		return jdbcTemplate.queryForObject("select * from books where id=?", new BookRowMapper(), id);
	}

}
