package com.jwebmp.plugins.fullcalendar.options.resources;

import com.fasterxml.jackson.annotation.*;
import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.options.*;

import java.util.*;

public class FullCalendarEventResourceInfo implements IJsonRepresentation<FullCalendarEventResourceInfo>
{
	private String id;
	private String title;
	
	@JsonAnyGetter
	@JsonAnySetter
	private Map<String,String> extendedProps;
	
	private String eventClassNames;
	private String eventTextColor;
	private String eventBorderColor;
	private String eventBackgroundColor;
	private String eventAllow;
	private String eventOverlap;
	private String eventConstraint;
	
	private FullCalendarBusinessHours businessHours;
	
	public String getId()
	{
		return id;
	}
	
	public FullCalendarEventResourceInfo setId(String id)
	{
		this.id = id;
		return this;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public FullCalendarEventResourceInfo setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public Map<String, String> getExtendedProps()
	{
		return extendedProps;
	}
	
	public FullCalendarEventResourceInfo setExtendedProps(Map<String, String> extendedProps)
	{
		this.extendedProps = extendedProps;
		return this;
	}
	
	public String getEventClassNames()
	{
		return eventClassNames;
	}
	
	public FullCalendarEventResourceInfo setEventClassNames(String eventClassNames)
	{
		this.eventClassNames = eventClassNames;
		return this;
	}
	
	public String getEventTextColor()
	{
		return eventTextColor;
	}
	
	public FullCalendarEventResourceInfo setEventTextColor(String eventTextColor)
	{
		this.eventTextColor = eventTextColor;
		return this;
	}
	
	public String getEventBorderColor()
	{
		return eventBorderColor;
	}
	
	public FullCalendarEventResourceInfo setEventBorderColor(String eventBorderColor)
	{
		this.eventBorderColor = eventBorderColor;
		return this;
	}
	
	public String getEventBackgroundColor()
	{
		return eventBackgroundColor;
	}
	
	public FullCalendarEventResourceInfo setEventBackgroundColor(String eventBackgroundColor)
	{
		this.eventBackgroundColor = eventBackgroundColor;
		return this;
	}
	
	public String getEventAllow()
	{
		return eventAllow;
	}
	
	public FullCalendarEventResourceInfo setEventAllow(String eventAllow)
	{
		this.eventAllow = eventAllow;
		return this;
	}
	
	public String getEventOverlap()
	{
		return eventOverlap;
	}
	
	public FullCalendarEventResourceInfo setEventOverlap(String eventOverlap)
	{
		this.eventOverlap = eventOverlap;
		return this;
	}
	
	public String getEventConstraint()
	{
		return eventConstraint;
	}
	
	public FullCalendarEventResourceInfo setEventConstraint(String eventConstraint)
	{
		this.eventConstraint = eventConstraint;
		return this;
	}
	
	public FullCalendarBusinessHours getBusinessHours()
	{
		if (businessHours == null)
		{
			return new FullCalendarBusinessHours();
		}
		return businessHours;
	}
	
	public FullCalendarEventResourceInfo setBusinessHours(FullCalendarBusinessHours businessHours)
	{
		this.businessHours = businessHours;
		return this;
	}
}
