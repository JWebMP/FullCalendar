package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.time.*;

public class FullCalendarDateClickEventInfo implements IJsonRepresentation<FullCalendarDateClickEventInfo>
{
	private OffsetDateTime date;
	private String dateStr;
	private Boolean allDay;
	
	private FullCalendarEventResourceInfo resource;
	
	public OffsetDateTime getDate()
	{
		return date;
	}
	
	public FullCalendarDateClickEventInfo setDate(OffsetDateTime date)
	{
		this.date = date;
		return this;
	}
	
	public String getDateStr()
	{
		return dateStr;
	}
	
	public FullCalendarDateClickEventInfo setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
		return this;
	}
	
	public Boolean getAllDay()
	{
		return allDay;
	}
	
	public FullCalendarDateClickEventInfo setAllDay(Boolean allDay)
	{
		this.allDay = allDay;
		return this;
	}
	
	public FullCalendarEventResourceInfo getResource()
	{
		return resource;
	}
	
	public FullCalendarDateClickEventInfo setResource(FullCalendarEventResourceInfo resource)
	{
		this.resource = resource;
		return this;
	}
}
