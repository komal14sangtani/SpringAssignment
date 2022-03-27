package com.bookapp.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class BookDaoImpl implements BookDao {

	private SessionFactory sessionFactory;

	@Autowired
	public BookDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// helper method
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Book> getAllBooks() {
		return getSession().createQuery("from Book", Book.class).getResultList();
	}

	@Override
	public void addBook(Book book) {
		getSession().persist(book);
	}

	@Override
	public void deleteBook(int id) {
		Book bookToDelete= getBookById(id);
		getSession().delete(bookToDelete);
	}

	@Override
	public void updateBook(int id, Book book) {
		Book bookToUpdate= getBookById(id);
		bookToUpdate.setPrice(book.getPrice());
		getSession().merge(bookToUpdate);
	}

	@Override
	public Book getBookById(int id) {
		Book book = getSession().get(Book.class, id);
		if (book == null) {
			throw new BookNotFoundException("book with id " + id + " is not found");
		}
		return book;
	}

}
