package com.challenge.scheduler.service;

import java.util.Optional;

import com.challenge.scheduler.model.UserMessage;

/**
 * User message service definition.
 * We use FindById only for test cases.
 */

public interface IUserMessageService
{
	UserMessage save(UserMessage userMessage);

	Optional<UserMessage> findById(long id);
}
