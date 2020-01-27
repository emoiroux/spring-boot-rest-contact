package com.challenge.scheduler.task;

import org.springframework.scheduling.Trigger;

/**
 * A RunnableTimeTask is defined by a time and a runnable object.
 */

public interface RunnableTimeTask
{
	public Trigger getScheduleTime();

	public Runnable getRunnable();
}
