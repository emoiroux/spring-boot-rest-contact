package com.challenge.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.scheduler.model.UserMessage;

/**
 * Repository of UserMessage
 */

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessage, Long> {

}