package com.modesttree.contact.service;

import java.util.List;
import java.util.Optional;

import com.modesttree.contact.model.UserContact;

/**
 * User message service definition.
 * We use FindById only for test cases.
 */

public interface IUserContactService
{
	UserContact save(UserContact userContact);

	Optional<UserContact> findById(long id);

	List<UserContact> findAll();

	void deleteById(long id);
}
