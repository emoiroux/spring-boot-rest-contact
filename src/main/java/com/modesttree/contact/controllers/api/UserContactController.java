package com.modesttree.contact.controllers.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modesttree.contact.model.UserContact;
import com.modesttree.contact.service.IUserContactService;

/**
 * UserMessageController, this controller use API v1.
 * So in the future, it will be easy to add a new version without problem.
 */

@RequestMapping("/api/contacts")
@Controller
public class UserContactController
{

	@Autowired
	private IUserContactService userContactService;



	/**
	 *  Adds a new Contact to the data store and returns it with its new id
	 * @param userContact The contact to add (supposed to get a JSon representation of
	 *                    UserContact).
	 * @return the user response.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserContact> newContact(@Valid @RequestBody UserContact userContact) throws ParseException
	{

		// Adds a new Contact to the data
		UserContact userContactEntity = userContactService.save(userContact);

		// Return a user response.
	    return new ResponseEntity<UserContact>(userContactEntity, HttpStatus.ACCEPTED);
	}
	
	/**
	 *  // Return the list of contacts as an array
	 * @param userContact The contact to add (supposed to get a JSon representation of
	 *                    UserContact).
	 * @return the user response.
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<List<UserContact>> getContacts() throws ParseException
	{

		// Adds a new Contact to the data
		 List<UserContact> userContacts = userContactService.findAll();

		// Return a user response.
		return new ResponseEntity<List<UserContact>>(userContacts, HttpStatus.OK);
	}
	
	/**
	 *  // Return the list of contacts as an array
	 * @param userContact The contact to add (supposed to get a JSon representation of
	 *                    UserContact).
	 * @return the user response.
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<UserContact> getContact(@PathVariable("id") long id) throws ParseException
	{

		// Adds a new Contact to the data
		Optional<UserContact> userContact = userContactService.findById(id);

		// Return a user response.
		return new ResponseEntity<UserContact>(userContact.get(), HttpStatus.OK);
	}
	
	/**
	 *  // Update a contact
	 * @return the user response.
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<UserContact> updateContact(@Valid @RequestBody UserContact userContact, @PathVariable("id") long id) throws ParseException
	{

		userContact.setId(id);
		
		UserContact userContactToUpdate = getContact(id).getBody();
		
		if (userContactToUpdate == null)
		{
			// Return a user response.
			// TODO Write a Response Class because new UserContact() is a bad design.
			return new ResponseEntity<UserContact>(new UserContact(), HttpStatus.BAD_REQUEST);
		}
		else
		{
			UserContact userContactUpdated = userContactService.save(userContact);
			return new ResponseEntity<UserContact>(userContactUpdated, HttpStatus.OK);
		}

	}
	
	/**
	 *  // Update a contact
	 * @return the user response.
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<UserContact> deleteContact(@PathVariable("id") long id) throws ParseException
	{
		userContactService.deleteById(id);
		return new ResponseEntity<UserContact>(new UserContact(), HttpStatus.OK);
	}
}
