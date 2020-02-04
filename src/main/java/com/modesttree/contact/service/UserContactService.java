package com.modesttree.contact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modesttree.contact.model.UserContact;
import com.modesttree.contact.repository.UserContactRepository;

/**
 * User contact service.
 * Implementation of methods that interacts with the repository.
 */

@Service
public class UserContactService implements IUserContactService
{

	@Autowired
	private UserContactRepository repository;

	public UserContactService()
	{
	}

	/**
	 * Save the given user contact.
	 * @param userContact the user contact to be saved.
	 */
	
	@Override
	public UserContact save(UserContact userMessage)
	{
		return repository.save(userMessage);
	}

	/**
	 * Find the given user contact by id.
	 * @param id the user contact id of to user message that we are looking for.
	 */
	
	@Override
	public Optional<UserContact> findById(long id)
	{
		return repository.findById(id);
	}

	@Override
	public List<UserContact> findAll()
	{
	    // Create an empty list 
        List<UserContact> list = new ArrayList<UserContact>(); 
  
        // Add each element of iterator to the List 
        Iterable<UserContact> iterable = repository.findAll();
        
        iterable.forEach(list::add); 
        
		return list;
	}

	@Override
	public void deleteById(long id)
	{
		repository.deleteById(id);	
	}
}
