package com.modesttree.contact.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modesttree.contact.model.UserContact;

/**
 * Repository for UserMessage.
 */

@Repository
public interface UserContactRepository extends CrudRepository<UserContact, Long>
{

}