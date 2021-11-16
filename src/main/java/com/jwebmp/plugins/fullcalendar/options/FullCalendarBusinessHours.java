package com.jwebmp.plugins.fullcalendar.options;

import com.guicedee.guicedinjection.representations.*;

import java.time.*;
import java.util.*;

public class FullCalendarBusinessHours implements IJsonRepresentation<FullCalendarBusinessHours>
{
	private List<Integer> daysOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public List<Integer> getDaysOfWeek()
	{
		return daysOfWeek;
	}
	
	public FullCalendarBusinessHours setDaysOfWeek(List<Integer> daysOfWeek)
	{
		this.daysOfWeek = daysOfWeek;
		return this;
	}
	
	public LocalTime getStartTime()
	{
		return startTime;
	}
	
	public FullCalendarBusinessHours setStartTime(LocalTime startTime)
	{
		this.startTime = startTime;
		return this;
	}
	
	public LocalTime getEndTime()
	{
		return endTime;
	}
	
	public FullCalendarBusinessHours setEndTime(LocalTime endTime)
	{
		this.endTime = endTime;
		return this;
	}
}
