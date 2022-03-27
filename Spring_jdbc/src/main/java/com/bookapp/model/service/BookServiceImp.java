package com.bookapp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookapp.model.dao.Book;
import com.bookapp.model.dao.BookDao;
import com.bookapp.model.dao.BookDaoImpl;
import com.bookapp.model.service.aspects.MyLogger;
@Service(value = "bookservice")
public class BookServiceImp implements BookService {

	private BookDao dao;
	
	@Autowired
	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	@MyLogger
	@Override
	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}

	@Override
	public void addBook(Book book) {
		 dao.addBook(book);
	}

	@Override
	public void deleteBook(int id) {
		dao.deleteBook(id);
	}

	@Override
	public void updateBook(int id, Book book) {
		dao.updateBook(id, book);
	}

	@Override
	public Book getBookById(int id) {
		return dao.getBookById(id);
	}

}
