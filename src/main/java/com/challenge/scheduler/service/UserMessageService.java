package com.challenge.scheduler.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.scheduler.model.UserMessage;
import com.challenge.scheduler.repository.UserMessageRepository;

/**
 * Service to save, get the userMessage from, to the DB.
 */

@Service
public class UserMessageService implements IUserMessageService {

	@Autowired
	private UserMessageRepository repository;

	public UserMessageService() {
	}

	/**
	 * Save the given user message.
	 * 
	 * @param userMessage the user message to be saved
	 */
	@Override
	public UserMessage save(UserMessage userMessage) {
		return repository.save(userMessage);
	}

	/**
	 * find the given user message id.
	 * 
	 * @param id the user message id to looking for.
	 */
	@Override
	public Optional<UserMessage> findById(long id) {
		return repository.findById(id);
	}
}
