package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.Item;
import entities.User;

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
	
	private static final String USER_EMAIL = "user_email";
	private static final String USER_ID = "user_id";
	private static final String USER_NAME = "user_name";


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
	
	
	
	
	public static User getUser(HttpServletRequest request) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		User sessionUser = new User();
		sessionUser.setEmail(httpSession.getAttribute(USER_EMAIL).toString());
		sessionUser.setId(httpSession.getAttribute(USER_ID).toString());
		sessionUser.setName(httpSession.getAttribute(USER_NAME).toString());
		return sessionUser; 
	}
	
	public static void saveSessionUser(HttpServletRequest request, User user) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute(USER_ID, user.getId());
		httpSession.setAttribute(USER_EMAIL, user.getEmail());
		httpSession.setAttribute(USER_NAME, user.getName());
	}
	
	public static User getSessionUser(HttpServletRequest request) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		String id = (String) httpSession.getAttribute(USER_ID);
		String email = (String) httpSession.getAttribute(USER_EMAIL);
		String name = (String) httpSession.getAttribute(USER_NAME);
		
		User user = null;
		if (id!=null && email !=null && name !=null) {
			user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setId(id);
		}
		return user;
	}
	
	public static byte[] inputStreamToByte(InputStream is){
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			
			buffer.flush();
		} catch (IOException e) {
			
		}

		return buffer.toByteArray();
	}
}
