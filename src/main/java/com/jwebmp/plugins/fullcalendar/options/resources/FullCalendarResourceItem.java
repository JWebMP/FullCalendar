package com.jwebmp.plugins.fullcalendar.options.resources;

import com.jwebmp.core.htmlbuilder.javascript.*;
import com.jwebmp.plugins.fullcalendar.options.*;

import java.util.*;


public class FullCalendarResourceItem extends JavaScriptPart<FullCalendarResourceItem>
{
	private UUID id;
	private String title;
	private String groupName;
	private String eventColor;
	private FullCalendarBusinessHours businessHours;
	private List<FullCalendarResourceItem> children = new ArrayList<>();
	
	public UUID getId()
	{
		return id;
	}
	
	public FullCalendarResourceItem setId(UUID id)
	{
		this.id = id;
		return this;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public FullCalendarResourceItem setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public FullCalendarResourceItem setGroupName(String groupName)
	{
		this.groupName = groupName;
		return this;
	}
	
	public String getEventColor()
	{
		return eventColor;
	}
	
	public FullCalendarResourceItem setEventColor(String eventColor)
	{
		this.eventColor = eventColor;
		return this;
	}
	
	public List<FullCalendarResourceItem> getChildren()
	{
		return children;
	}
	
	public FullCalendarResourceItem setChildren(List<FullCalendarResourceItem> children)
	{
		this.children = children;
		return this;
	}
	
	public FullCalendarBusinessHours getBusinessHours()
	{
		if (businessHours == null)
		{
			businessHours = new FullCalendarBusinessHours();
		}
		return businessHours;
	}
	
	public FullCalendarResourceItem setBusinessHours(FullCalendarBusinessHours businessHours)
	{
		this.businessHours = businessHours;
		return this;
	}
}
