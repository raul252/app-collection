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
    	
    	 removeAllUser(); 
    }
   
   
  


	/*@Test
     public void injectTest(){
    	 Assert.assertNotNull(service); 
     }*/
     
     
     @Test
     public void  addCollection(){
    	  User user = mockSingUpUSer();  
    	
    	  Collection collection = new Collection();
    	  collection.setDescription("Esta es una coleccion de test"); 
    	  collection.setName("Test coleccion"); 
    	 
		  service.addCollection(user.getId(),collection); 
		  
		  
		  User result = service.find(User.class, user.getId());  
		  
		  Assert.assertEquals(1, result.getCollections().size()); 
		  ArrayList<Collection> list =  new ArrayList<>(result.getCollections()); 		  
		  Assert.assertEquals(collection.getName(),list.get(0).getName() ); 
		  
		 // Assert.assertEquals(collection.
		  
     }
     
     
     
     
     
     
     
     
     private User mockSingUpUSer(){
    	 User user = new User();
    	 user.setName("Test user"); 
    	 user.setEmail(TEST_USER_EMAIL);     	 
    	 service.signUpUser(user);
    	 return user; 
     }
     
     
     private void removeAllUser() { 	
    	 
    	 List<Collection> listCollection = service.getAll(Collection.class);
    	 	service.deleteAll(Collection.class); 
    	 
    	 List<User> listUser = service.getAll(User.class);  
    	 	service.deleteAll(User.class); 
    		
 	}
     

}
