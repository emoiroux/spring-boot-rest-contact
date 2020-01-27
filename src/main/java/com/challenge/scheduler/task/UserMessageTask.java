package com.challenge.scheduler.task;

import org.springframework.scheduling.Trigger;

/**
 * An implementation of a RunnableTimeTask for user message.
 */

public class UserMessageTask implements RunnableTimeTask
{

	// The message to be printed.
	private String message;

	// The next execution time of the task.
	private Trigger trigger;

	public UserMessageTask(String message, Trigger trigger)
	{
		this.message = message;
		this.trigger = trigger;
	}

	@Override
	public Trigger getScheduleTime()
	{

		return trigger;
	}

	@Override
	public Runnable getRunnable()
	{
		return new Runnable()
		{

			@Override
			public void run()
			{
				// Print the message in the console.
				System.out.println(message);
			}
		};
	}

}
