package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.time.*;

public class FullCalendarEventInfo implements IJsonRepresentation<FullCalendarEventInfo>
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
	
	public FullCalendarEventInfo setStart(OffsetDateTime start)
	{
		this.start = start;
		return this;
	}
	
	public OffsetDateTime getEnd()
	{
		return end;
	}
	
	public FullCalendarEventInfo setEnd(OffsetDateTime end)
	{
		this.end = end;
		return this;
	}
	
	public String getStartStr()
	{
		return startStr;
	}
	
	public FullCalendarEventInfo setStartStr(String startStr)
	{
		this.startStr = startStr;
		return this;
	}
	
	public String getEndStr()
	{
		return endStr;
	}
	
	public FullCalendarEventInfo setEndStr(String endStr)
	{
		this.endStr = endStr;
		return this;
	}
	
	public Boolean getAllDay()
	{
		return allDay;
	}
	
	public FullCalendarEventInfo setAllDay(Boolean allDay)
	{
		this.allDay = allDay;
		return this;
	}
	
	public FullCalendarEventResourceInfo getResource()
	{
		return resource;
	}
	
	public FullCalendarEventInfo setResource(FullCalendarEventResourceInfo resource)
	{
		this.resource = resource;
		return this;
	}
}
