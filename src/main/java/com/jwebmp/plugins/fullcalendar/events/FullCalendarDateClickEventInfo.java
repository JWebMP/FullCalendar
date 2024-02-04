package com.jwebmp.plugins.fullcalendar.events;

import com.google.common.base.Strings;
import com.guicedee.services.jsonrepresentation.IJsonRepresentation;
import com.guicedee.services.jsonrepresentation.json.LocalDateTimeDeserializer;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarEventResourceInfo;

import java.time.LocalDateTime;

public class FullCalendarDateClickEventInfo implements IJsonRepresentation<FullCalendarDateClickEventInfo>
{
	private LocalDateTime date;
	private String dateStr;
	private Boolean allDay;
	
	private FullCalendarEventResourceInfo resource;
	
	public LocalDateTime getDate()
	{
		return date;
	}
	
	public FullCalendarDateClickEventInfo setDate(LocalDateTime date)
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
	
	public void updateDates()
	{
		if (!Strings.isNullOrEmpty(dateStr))
		{
			date = new LocalDateTimeDeserializer().convert(dateStr);
		}
	}
}
