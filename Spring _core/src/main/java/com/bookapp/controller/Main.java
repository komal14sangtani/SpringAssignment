package com.bookapp.controller;
import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bookapp.config.AppConfig;
import com.bookapp.model.dao.Book;
import com.bookapp.model.service.BookService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext
				(AppConfig.class);
		
		BookService bookService=(BookService) ctx.getBean("bookservice");
		List<Book>books=bookService.getAllBooks();
		for(Book book: books) {
			System.out.println(book);
		}
	}
}
