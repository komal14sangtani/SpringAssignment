package com.bookapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bookapp.model.dao.BookDao;
import com.bookapp.model.dao.BookDaoImpl;
import com.bookapp.model.service.BookService;
import com.bookapp.model.service.BookServiceImp;

@Configuration
@ComponentScan(basePackages = {"com.bookapp"})
public class AppConfig {

	@Bean
	public BookDao getBookDao() {
		BookDao bookDao=new BookDaoImpl();
		return bookDao;
	}
	
	
	@Bean(name = "bookservice")
	public BookService getBookService(BookDao dao) {
		BookServiceImp bookService= new BookServiceImp();
		bookService.setDao(dao);
		return bookService;
	}
	
	
}
