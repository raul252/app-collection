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
	
	
	
	
	
	@Test
	public void deleteBook(){
		ArrayList<Book> listBlue = getMockBook("Blue", "Author blue", 2);  
		for (Book book : listBlue)
			service.add(book); 
		
		int id = listBlue.get(0).getId(); 
		
		service.remove(String.valueOf(id)); 
		
		List<Book> results = service.getAll();  	
		Assert.assertEquals(1,results.size()); 
		
	}
	

	@Test
	public void updateBook(){
		ArrayList<Book> listBlue = getMockBook("Blue", "Author blue", 1);  
		Book book = listBlue.get(0);
		service.add(book); 
		
		//int id = listBlue.get(0).getId(); 
		
		Book bookEdited = new Book(); 
		bookEdited.setId(book.getId());
		
		bookEdited.setTitle("Title edited"); 
		bookEdited.setDescription("Description Edited"); 
		bookEdited.setAuthor(null); 
		
		
		service.update(bookEdited);
		
		
		Book results = service.find(String.valueOf(book.getId()));  
		  	
		Assert.assertEquals("Title edited",results.getTitle()); 
		Assert.assertEquals("Description Edited",results.getDescription());
		Assert.assertEquals("Author blue",results.getAuthor());
		
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
