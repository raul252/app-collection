package com.book.app.servlet;

import javax.servlet.http.HttpServletRequest;

import entities.Item;

public class HttpHelper {

	
	/*public static Item getParameterBook(HttpServletRequest request) { 
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String desc = request.getParameter("description");
		String bookId = request.getParameter("bookId");
		
		Item book = new Item(); 
		book.setId(bookId);  
		book.setAuthor(author);
		book.setTitle(title);
		book.setDescription(desc);		
		return book; 
	}*/
	
	public static String getStyleTable(){
		return "<style>" 
		+"table {" 
		+"    font-family: arial, sans-serif;"
		+"    border-collapse: collapse;"
		+"    width: 100%;"
		+"}"

		+"td, th {"
		+"    border: 1px solid #dddddd;"
		+ "   text-align: left;"
		+ "   padding: 8px;"
		+"}"

		+"tr:nth-child(even) {"
		+"    background-color: #dddddd;"
		+"}"
		+"</style>"; 
	}
	
	
}
