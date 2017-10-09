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


public class ServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private BookService service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		   String option = request.getParameter("option");  
		   	   
		   if(option.equals("insert")){			   
			   Book book = HttpHelper.getParameterBook(request);  
			   service.add(book);	   
		   }else if(option.equals("delete")){
			   String bookId = request.getParameter("bookId");
			   if(bookId!=null && !bookId.equals(""))
				   service.remove(bookId); 
			   else{/*TODO*/}
		   }
		   
		   List<Book> list = service.getAll();
		   
		   
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(HttpHelper.getStyleTable()); 
	        out.println("<title>Catalogo de libros</title>");
	        out.println("</head>");
	        out.println("<body>");
	       
	        
	        out.println("<h1>Catalogo de libros</h1>");
	        
	        out.println("<table>"); 
	        out.println("<tr><th>ID </th>"
	        		+ "<th>Titulo </th>"
	        		+ "<th>Autor </th>"
	        		+ "<th>Descripcion</th>"
	        		+ "<th>Editar</th>"
	        		+ "<th>Eliminar</th>"
	        		+ "</tr>");      
	        
	        for (Book book : list) {
		        out.println("<tr>"); 
		         out.println("<td> " +book.getId()+" </td>"); 
		         out.println("<td> "+book.getTitle()+" </td>"); 
		         out.println("<td> "+book.getAuthor()+" </td>");
		         out.println("<td> "+book.getDescription()+" </td>"); 
		         out.println("<td> <a href=../servlet/edit?option=show&bookId="
		         		 +      book.getId() + ">Editar</a>"
		        		 +     "</td>"); 
		         out.println("<td> <a href=../servlet/book?option=delete&bookId="
		         		 +      book.getId() + ">Eliminar</a>"
		        		 +     "</td>"); 
		         
		         
		         
		         
		        out.println("</tr> "); 
	        }
	        out.println("</table>"); 
	        
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