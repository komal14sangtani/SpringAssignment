package com.bookapp.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book>{
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book temp=new Book();
		temp.setId(rs.getInt(1));
		temp.setTitle(rs.getString(2));
		temp.setAuthor(rs.getString(3));
		temp.setPrice(rs.getDouble(4));
		return temp;
	}

}
