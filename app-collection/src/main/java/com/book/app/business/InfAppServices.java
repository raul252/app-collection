package com.book.app.business;

import java.util.List;

import javax.ejb.Local;

import entities.Item;
import entities.User;
import entities.Image;
import entities.Collection;

@Local
public interface InfAppServices {
	
	// Library ------> Book --------> image
	
	
	public void signUpUser(User user); //
	public User signInUser(String email); 
	public void signOut(); 

	
	public void addCollection(String userId, Collection collection); //
	
	public void removeCollection(String collectionId); 
	
	
	public void addItem(String collectionId, Item item,byte[] bytes);
	public void addImage(String itemId, Image image);	
	public void updateItem(Item item, byte[] bytes); 
	public void removeItem(String  itemId); 
	
	/* for test */ 
	
	public  <T> T find(Class< T> clazz,Object id); 
	public void remove(Object entity);
	
	public <T> List<T> getAll(Class<T> clazz);  
	public <T> void deleteAll(Class<T> clazz); 
	
	
	
}
