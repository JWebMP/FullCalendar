package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.time.*;

public class FullCalendarSelectEventInfo implements IJsonRepresentation<FullCalendarSelectEventInfo>
{
	private OffsetDateTime start;
	private OffsetDateTime end;
	private String startStr;
	private String endStr;
	private Boolean allDay;
	
	private FullCalendarEventResourceInfo resource;
	
	public OffsetDateTime getStart()
	{
		return start;
	}
	
	public FullCalendarSelectEventInfo setStart(OffsetDateTime start)
	{
		this.start = start;
		return this;
	}
	
	public OffsetDateTime getEnd()
	{
		return end;
	}
	
	public FullCalendarSelectEventInfo setEnd(OffsetDateTime end)
	{
		this.end = end;
		return this;
	}
	
	public String getStartStr()
	{
		return startStr;
	}
	
	public FullCalendarSelectEventInfo setStartStr(String startStr)
	{
		this.startStr = startStr;
		return this;
	}
	
	public String getEndStr()
	{
		return endStr;
	}
	
	public FullCalendarSelectEventInfo setEndStr(String endStr)
	{
		this.endStr = endStr;
		return this;
	}
	
	public Boolean getAllDay()
	{
		return allDay;
	}
	
	public FullCalendarSelectEventInfo setAllDay(Boolean allDay)
	{
		this.allDay = allDay;
		return this;
	}
	
	public FullCalendarEventResourceInfo getResource()
	{
		return resource;
	}
	
	public FullCalendarSelectEventInfo setResource(FullCalendarEventResourceInfo resource)
	{
		this.resource = resource;
		return this;
	}
}
