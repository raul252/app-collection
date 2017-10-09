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
			   Book book = getParameterBook(request);  
			   service.add(book);	   
		   }
		   
		   List<Book> list = service.getAll();
		   
		   
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(getSytele()); 
	        out.println("<title>Catalogo de libros</title>");
	        out.println("</head>");
	        out.println("<body>");
	       
	        
	        out.println("<h1>Catalogo de libros</h1>");
	        
	        out.println("<table>"); 
	        out.println("<tr><th>ID </th><th>Titulo </th><th>Autor </th><th>Descripcion </th></tr>");         
	        for (Book book : list) {
		        out.println("<tr>"); 
		         out.println("<td> " +book.getId()+" </td>"); 
		         out.println("<td> "+book.getTitle()+" </td>"); 
		         out.println("<td> "+book.getAuthor()+" </td>"); 
		         out.println("<td> "+book.getDescription()+" </td>"); 
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
	
	
	
	
	private Book getParameterBook(HttpServletRequest request) { 
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String desc = request.getParameter("description");
				
		Book book = new Book(); 		
		book.setAuthor(author);
		book.setTitle(title);
		book.setDescription(desc);		
		return book; 
	}
	
	
	private String getSytele(){
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