package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.BookService;

import entities.Book;


public class ServletEditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private BookService service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		   String option = request.getParameter("option");  
		   Book book=null; 	 
		   
		   if(option.equals("show")){			   
			   String bookId = request.getParameter("bookId");
			   book = service.find(bookId);
			   if(book==null){/*TODO */}
		   }else if(option.equals("edit")){
			   book = HttpHelper.getParameterBook(request); 
			   	service.update(book);
			   	response.sendRedirect("../servlet/book?option=list"); 
			   	return; 
		   }
		     
		   
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(HttpHelper.getStyleTable()); 
	        out.println("<title>Catalogo de libros</title>");
	        out.println("</head>");
	        out.println("<body>");
	      
	        
        	out.println("<body>");
        		
			out.println("<h1>Editar Libro</h1>");
	              
			 out.println("<form action='../servlet/edit?option=edit' method='Post'>");
			 out.println("Titulo:<br>");
			 out.println(" <input type='text' value='"+book.getTitle()+"' name='title'>");
			 out.println(" <br>");
			 out.println(" Autor:<br>");
			 out.println(" <input type='text' value='"+book.getAuthor()+"' name='author'>");
		     out.println(" <br>");
			 out.println(" Descripcion:<br>");
			 out.println(" <input type='text' value='"+book.getDescription()+ "' name='description'>");
			 out.println(" <input type='hidden' value='"+book.getId()+ "' name='bookId'>");
			 out.println(" <br><br>");
			 out.println(" <input type='submit' value='Editar'>");
			 out.println("</form> ");
	        
	
	        out.println("<p>");    
	        	out.println("<a href=../index.html> Menu </a>");
	        out.println("<p>");
	        
	        out.println("</body>");
	        out.println("</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}