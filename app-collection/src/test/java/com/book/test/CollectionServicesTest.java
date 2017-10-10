package com.book.test;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.book.app.business.AppServices;
import com.book.app.business.ImageService;
import com.book.app.business.InfAppServices;
import com.book.test.tools.MockHelper;
import com.book.test.tools.TestEjbHelper;

import entities.Collection;
import entities.User;

public class CollectionServicesTest {
	
	
	
	private static final String TEST_USER_EMAIL = "qbit.player@gmail.com";
	
	@Inject
	private InfAppServices service; 
 
	/*@EJB
	private AppServices ejbServices;*/
	

   @Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this); 
    	
 
    }
   
   
  

     @Test
     public void  addCollection(){
    	  User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
    	
    	  Collection collection = MockHelper.mockCollection("Collection test"); 
    	 
		  service.addCollection(user.getId(),collection); 
		  
		  
		  User result = service.find(User.class, user.getId());  
		  
		  Assert.assertEquals(1, result.getCollections().size()); 
		  ArrayList<Collection> list =  new ArrayList<>(result.getCollections()); 		  
		  Assert.assertEquals(collection.getName(),list.get(0).getName() ); 
		  
		 // Assert.assertEquals(collection.
		  
     }
     
     


     

}
