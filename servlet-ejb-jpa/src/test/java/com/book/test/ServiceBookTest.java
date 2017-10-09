package com.book.test;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.book.app.business.BookService;


import entities.Book;

public class ServiceBookTest {
	
	
	@EJB
	private BookService service; 
	
	
	@Before
	public void before() throws NamingException{ 
		  EJBContainer  ejbContainer = TestEjbHelper.getEjbContainer();
		  ejbContainer.getContext().bind("inject", this);	
		  service.deleteAll();
		  
		  //ejbContainer.getContext().lookup("java:/gobla/app-book/BookService")
	}
	
	
	@Test
	public void findByAuthor(){
		
		ArrayList<Book> listBlue = getMockBook("Blue", "Author blue", 10);  
		for (Book book : listBlue)
			service.add(book); 
				
		ArrayList<Book> listRed = getMockBook("Red", "Author Red", 8); 
		for (Book book : listRed)
			service.add(book); 
		
		
		List<Book> results = service.getBookByAuthor("Author Red");  
		
		Assert.assertEquals(8,results.size()); 
		
	}
	
	
	@Test
	public void findByTitle(){
		
		ArrayList<Book> listBlue = getMockBook("Blue", "Author blue", 2);  
		for (Book book : listBlue)
			service.add(book); 
		listBlue = getMockBook("Blue", "Author blue", 2);  
		for (Book book : listBlue)
			service.add(book); 
				
		List<Book> results = service.getBookByTitle("Blue 0");  
		
		Assert.assertEquals(2,results.size()); 
		
	}
	

	

	
	
	public ArrayList<Book> getMockBook(String title, String author, int n){		
		ArrayList<Book> list = new ArrayList<>(); 		
		for(int i=0; i<n; i++){
			Book book = new Book(); 
			book.setTitle(title + " " + i); 
			book.setAuthor(author);
			book.setDescription("Este es un libro de test, item " + i); 
			list.add(book); 
		}	
		return list; 
	}
}
