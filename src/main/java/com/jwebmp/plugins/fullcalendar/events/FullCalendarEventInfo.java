package com.jwebmp.plugins.fullcalendar.events;

import com.google.common.base.*;
import com.guicedee.guicedinjection.json.*;
import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.time.*;

public class FullCalendarEventInfo implements IJsonRepresentation<FullCalendarEventInfo>
{
	private String id;
	private LocalDateTime start;
	private LocalDateTime end;
	private String startStr;
	private String endStr;
	private Boolean allDay;
	
	private FullCalendarEventResourceInfo resource;
	
	public LocalDateTime getStart()
	{
		return start;
	}
	
	public FullCalendarEventInfo setStart(LocalDateTime start)
	{
		this.start = start;
		return this;
	}
	
	public LocalDateTime getEnd()
	{
		return end;
	}
	
	public FullCalendarEventInfo setEnd(LocalDateTime end)
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
	
	public void updateDates()
	{
		if(!Strings.isNullOrEmpty(startStr))
		{
			start = new LocalDateTimeDeserializer().convert(startStr);
		}
		if(!Strings.isNullOrEmpty(endStr))
		{
			end = new LocalDateTimeDeserializer().convert(endStr);
		}
	}
	
	public String getId()
	{
		return id;
	}
	
	public FullCalendarEventInfo setId(String id)
	{
		this.id = id;
		return this;
	}
}
