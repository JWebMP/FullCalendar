package com.jwebmp.plugins.fullcalendar.options.resources;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.guicedee.services.jsonrepresentation.IJsonRepresentation;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarBusinessHours;

import java.util.Map;
import java.util.Set;

public class FullCalendarEventResourceInfo implements IJsonRepresentation<FullCalendarEventResourceInfo>
{
	private String id;
	private String title;
	
	private Map<String,Object> extendedProps;
	
	@JsonAnyGetter
	@JsonAnySetter
	private Map<String,Object> unknownProperties;
	
	private Set<String> eventClassNames;
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
	
	public Map<String, Object> getExtendedProps()
	{
		return extendedProps;
	}
	
	public FullCalendarEventResourceInfo setExtendedProps(Map<String, Object> extendedProps)
	{
		this.extendedProps = extendedProps;
		return this;
	}
	
	public Set<String> getEventClassNames()
	{
		return eventClassNames;
	}
	
	public FullCalendarEventResourceInfo setEventClassNames(Set<String> eventClassNames)
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
	
	public Map<String, Object> getUnknownProperties()
	{
		return unknownProperties;
	}
	
	public FullCalendarEventResourceInfo setUnknownProperties(Map<String, Object> unknownProperties)
	{
		this.unknownProperties = unknownProperties;
		return this;
	}
}
