package com.challenge.scheduler.controllers.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.scheduler.model.UserMessage;
import com.challenge.scheduler.response.Response;
import com.challenge.scheduler.service.IUserMessageService;
import com.challenge.scheduler.service.ScheduleTaskService;
import com.challenge.scheduler.task.UserMessageTaskFactory;

/**
 * UserMessageController, this controller use API v1.
 * So in the future, it will be easy to add a new version without problem.
 */

@RequestMapping("/api/v1/messages/message")
@Controller
public class UserMessageController
{

	@Autowired
	private IUserMessageService userMessageService;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE)

	/**
	 * Schedule a new message.
	 * @param userMessage The message to be scheduled (supposed to get a JSon representation of
	 *                    UserMessage).
	 * @return the user response.
	 */
	@ResponseBody
	public ResponseEntity<Response> newSchedule(@Valid @RequestBody UserMessage userMessage) throws ParseException
	{

		// Save the message in the DB
		UserMessage userMessageEntity = userMessageService.save(userMessage);

		// Schedule the message in the system for future execution.
		Response response = ScheduleTaskService.getInstance().schedule(UserMessageTaskFactory.build(userMessageEntity));

		// Return a user response.
		return new ResponseEntity<Response>(response, response.getStatus());
	}
}
