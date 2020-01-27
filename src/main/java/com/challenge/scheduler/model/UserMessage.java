package com.challenge.scheduler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * User message model.
 * I choose not to use DTO because I find the model simple enough.
 */

@Entity
@Table(name = "SCHEDULED_MESSAGES")
public class UserMessage
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Min(0)
	@Max(23)
	private int hour;

	@Min(0)
	@Max(59)
	private int minute;

	@Min(0)
	@Max(59)
	private int second;

	@NotEmpty(message = "message must not be null")
	private String message;

	public UserMessage()
	{
	}

	public UserMessage(int hour, int minute, int second, String message)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	public int getSecond()
	{
		return second;
	}

	public void setSecond(int second)
	{
		this.second = second;
	}

	public long getId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return "UserMessage [id=" + id + ", hour=" + hour + ", minute=" + minute + ", second=" + second + ", message="
				+ message + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMessage other = (UserMessage) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
