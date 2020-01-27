package com.challenge.scheduler.task;

import java.util.TimeZone;

import org.springframework.scheduling.support.CronTrigger;

import com.challenge.scheduler.model.UserMessage;

/**
 * A task builder.
 */

public class UserMessageTaskFactory
{

	/**
	 * build a new task for a given message.
	 * @param userMessage The message to be used.
	 */

	public static RunnableTimeTask build(UserMessage userMessage)
	{

		UserMessageTask userMessageTask = null;
		try
		{
			// Set the next execution time of the task.
			CronTrigger cronTrigger = new CronTrigger(
					userMessage.getSecond() + " " + userMessage.getMinute() + " " + userMessage.getHour() + " * * ?",
					TimeZone.getTimeZone(TimeZone.getDefault().getID()));

			userMessageTask = new UserMessageTask(userMessage.getMessage(), cronTrigger);
		} 
		catch (Exception e)
		{
			// log error (for example if userMessage is null or if CronTrigger throw an exception).
			System.err.println("Can not schedule message : " + userMessage + ", e=" + e.toString());
		}

		return userMessageTask;
	}
}
