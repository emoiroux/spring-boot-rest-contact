package com.challenge.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.challenge.scheduler.model.UserMessage;
import com.challenge.scheduler.repository.UserMessageRepository;
import com.challenge.scheduler.response.Response;
import com.challenge.scheduler.task.RunnableTimeTask;
import com.challenge.scheduler.task.UserMessageTaskFactory;

/**
 * A task scheduling service based on Spring framework.
 */

@Service
public class ScheduleTaskService
{

	private static final int POOL_SIZE = 5;
	private static final String THREAD_NAME_PREFIX = "ThreadPoolTaskScheduler";

	private TaskScheduler taskScheduler;
	private static ScheduleTaskService instance;

	@Autowired
	private UserMessageRepository schedulerDB;

	private ScheduleTaskService()
	{
		/* Create a new task scheduler */
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix(THREAD_NAME_PREFIX);
		scheduler.setPoolSize(POOL_SIZE);
		scheduler.initialize();

		this.taskScheduler = scheduler;
	}

	public static ScheduleTaskService getInstance()
	{
		if (instance == null)
			instance = new ScheduleTaskService();

		return instance;
	}

	/**
	 * Re schedule messages when application startup or refresh
	 */
	
	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent()
	{
		for (UserMessage message : schedulerDB.findAll())
		{
			schedule(UserMessageTaskFactory.build(message));
		}
	}

	/**
	 * Schedule the task in the system.
	 * @param task The task to be scheduled.
	 * @return the user response.
	 */
	
	public Response schedule(RunnableTimeTask task)
	{
		if (task == null || task.getRunnable() == null || task.getScheduleTime() == null)
			return Response.exception("The task is incorrect");
		else
		{
			taskScheduler.schedule(task.getRunnable(), task.getScheduleTime());
			System.out.println("Message scheduled at " + task.getScheduleTime().toString());
			return Response.accepted("The task will be performed as requested");
		}
	}
}