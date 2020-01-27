package com.challenge.scheduler.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.scheduler.model.UserMessage;
import com.challenge.scheduler.repository.UserMessageRepository;

/**
 * User message service.
 * Implementation of methods that interacts with the repository.
 */

@Service
public class UserMessageService implements IUserMessageService
{

	@Autowired
	private UserMessageRepository repository;

	public UserMessageService()
	{
	}

	/**
	 * Save the given user message.
	 * @param userMessage the user message to be saved.
	 */
	
	@Override
	public UserMessage save(UserMessage userMessage)
	{
		return repository.save(userMessage);
	}

	/**
	 * Find the given user message by id.
	 * @param id the user message id of to user message that we are looking for.
	 */
	
	@Override
	public Optional<UserMessage> findById(long id)
	{
		return repository.findById(id);
	}
}
