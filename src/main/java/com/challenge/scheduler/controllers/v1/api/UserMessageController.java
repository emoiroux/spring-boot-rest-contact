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
 * UserMessageController, this controller use api v1.
 */

@RequestMapping("/api/v1/messages/message")
@Controller
public class UserMessageController {

	@Autowired
	private IUserMessageService userMessageService;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE)

	/**
	 * Schedule a new messages.
	 * 
	 * @param userMessage The message to schedule in JSon.
	 * @return the user response.
	 */
	@ResponseBody
	public ResponseEntity<Response> newSchedule(@Valid @RequestBody UserMessage userMessage) throws ParseException {

		/* Save the message to the DB and get the entity */
		UserMessage userMessageEntity = userMessageService.save(userMessage);

		/*
		 * Schedule the message in the system for future execution, if time before now
		 * => scheduled for next day
		 */
		Response response = ScheduleTaskService.getInstance().schedule(UserMessageTaskFactory.build(userMessageEntity));

		/* Response */
		return new ResponseEntity<Response>(response, response.getStatus());
	}
}
