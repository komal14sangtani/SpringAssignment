package com.bookapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
//@Primary
public class BookDaoImplJdbc implements BookDao {

	private DataSource dataSource;

	public BookDaoImplJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<>();

		String sql = "select * from books";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				// Book book2=new Book(id, title, author, price)
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
				allBooks.add(book);
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return allBooks;
	}

	@Override
	public void addBook(Book book) {
		try {
			String add_book_query=
			"insert into books(title, author, price) values(?,?,?,?,?)";
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt=connection.prepareStatement(add_book_query, 
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setDouble(5, book.getPrice());
			
			int noOfRowsEffected=pstmt.executeUpdate();
			
			if(noOfRowsEffected>0) {
				ResultSet rs=pstmt.getGeneratedKeys();
				rs.next();
				book.setId(rs.getInt(1));
			}	
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void deleteBook(int id) {
		try {
			
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt= connection.prepareStatement("delete from books where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(int id, Book book) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt=connection.prepareStatement("update books set price=? where id=?");
			pstmt.setDouble(1, book.getPrice());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Book getBookById(int id) {
		Book book=null;
		try {
			String get_book_by_id="select * from books where id=?";
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt= connection.prepareStatement(get_book_by_id);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				book=new Book(rs.getInt("id") , rs.getString("title"),
						rs.getString("author"),  rs.getDouble("price"));
			}else {
				throw new BookNotFoundException("book with id "+ id +" is not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

}
