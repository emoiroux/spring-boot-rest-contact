package com.challenge.scheduler.service;

import java.util.Optional;

import com.challenge.scheduler.model.UserMessage;

/**
 * User message service definition.
 */

public interface IUserMessageService {
	UserMessage save(UserMessage userMessage);

	Optional<UserMessage> findById(long id);
}
