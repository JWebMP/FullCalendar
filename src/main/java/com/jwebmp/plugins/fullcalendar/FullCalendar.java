/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.fullcalendar;

import com.fasterxml.jackson.core.*;
import com.guicedee.guicedinjection.*;
import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.base.angular.services.annotations.references.*;
import com.jwebmp.core.base.angular.services.annotations.structures.*;
import com.jwebmp.core.base.angular.services.interfaces.*;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.htmlbuilder.javascript.*;
import com.jwebmp.core.plugins.ComponentInformation;
import com.jwebmp.plugins.fullcalendar.events.*;
import com.jwebmp.plugins.fullcalendar.options.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.util.*;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.*;

/**
 * An implementation of
 * <p>
 *
 * @author GedMarc
 * @version 1.0
 * @since 17 Jan 2017
 */
@ComponentInformation(name = "Full Calendar",
		description = "Display a full-size drag-n-drop event calendar",
		url = "https://fullcalendar.io/")
@NgImportReference(name = "CalendarOptions, DateSelectArg, EventClickArg, EventApi",reference = "@fullcalendar/angular")

@NgField("currentEvents: EventApi[] = [];")

@NgMethod("handleWeekendsToggle() {\n" +
          "    const { calendarOptions } = this;\n" +
          "    calendarOptions.weekends = !calendarOptions.weekends;\n" +
          "  }")
@NgMethod("handleDateSelect(selectInfo: DateSelectArg) {\n" +
          "  }")
@NgMethod("handleEventClick(selectInfo: EventClickArg) {\n" +
          
          "  }")
@NgMethod("handleEvents(events: EventApi[]) {\n" +
          "    this.currentEvents = events;\n" +
          "  }")
public class FullCalendar<J extends FullCalendar<J>>
		extends Div<FullCalendarChildren, FullCalendarAttributes, FullCalendarFeatures, FullCalendarEvents, J>
		implements INgComponent<J>
{
	/**
	 * The full calendar options list
	 */
	private FullCalendarOptions options;
	private String eventSource;
	private String externalEventContainerId;
	
	
	private FullCalendarDateClickEvent dateClickEvent;
	private FullCalendarEventClickEvent eventClickEvent;
	private FullCalendarEventReceiveEvent receiveEvent;
	private FullCalendarEventResizeEvent eventResizeEvent;
	private FullCalendarEventDropEvent eventDropEvent;
	private FullCalendarDropEvent dropEvent;
	private FullCalendarSelectEvent selectEvent;
	
	
	
	
	private List<FullCalendarResourceItem> resources;

	/**
	 * Constructs a new instance
	 */
	public FullCalendar(String id)
	{
		setID(id);
		setTag("full-calendar");
	}
	
	@Override
	public List<String> fields()
	{
		return List.of("calendarOptions: CalendarOptions = " + getOptions().toJson());
	}
	
	@Override
	public void init()
	{
		addAttribute("[options]", "calendarOptions");
		setInvertColonRender(true);
		if (dateClickEvent != null)
		{
			addAttribute("date-click", dateClickEvent.getClassCanonicalName());
		}
		if (eventClickEvent != null)
		{
			addAttribute("event-click", eventClickEvent.getClassCanonicalName());
		}
		if (receiveEvent != null)
		{
			addAttribute("event-receive", receiveEvent.getClassCanonicalName());
		}
		if (eventResizeEvent != null)
		{
			addAttribute("event-resize", eventResizeEvent.getClassCanonicalName());
		}
		if (eventDropEvent != null)
		{
			addAttribute("event-drop", eventDropEvent.getClassCanonicalName());
		}
		if (selectEvent != null)
		{
			addAttribute("select", selectEvent.getClassCanonicalName());
		}
		
		
		if (resources != null)
		{
			try
			{
				addAttribute("resources", GuiceContext.get(DefaultObjectMapper).writeValueAsString(resources));
			}
			catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
		}
		
		if (eventSource != null)
		{
			addAttribute("event-source", eventSource);
		}
		if (externalEventContainerId != null)
		{
			addAttribute("externalEvents", externalEventContainerId);
		}
		
		super.init();
	}
	
	/**
	 * Returns the options if any is required
	 *
	 * @return
	 */
	@Override
	public FullCalendarOptions getOptions()
	{
		if (options == null)
		{
			options = new FullCalendarOptions();
		}
		return options;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}
	
	public FullCalendarDateClickEvent getDateClickEvent()
	{
		return dateClickEvent;
	}
	
	public FullCalendar setDateClickEvent(FullCalendarDateClickEvent dateClickEvent)
	{
		this.dateClickEvent = dateClickEvent;
		return this;
	}
	
	public FullCalendar setOptions(FullCalendarOptions options)
	{
		this.options = options;
		return this;
	}
	
	public List<FullCalendarResourceItem> getResources()
	{
		if (resources == null)
		{
			resources = new ArrayList<>();
		}
		return resources;
	}
	
	public FullCalendar setResources(List<FullCalendarResourceItem> resources)
	{
		this.resources = resources;
		return this;
	}
	
	public String getEventSource()
	{
		return eventSource;
	}
	
	public FullCalendar setEventSource(String eventSource)
	{
		this.eventSource = eventSource;
		return this;
	}
	
	public String getExternalEventContainerId()
	{
		return externalEventContainerId;
	}
	
	public FullCalendar setExternalEventContainerId(String externalEventContainerId)
	{
		this.externalEventContainerId = externalEventContainerId;
		return this;
	}
	
	public FullCalendarEventClickEvent getEventClickEvent()
	{
		return eventClickEvent;
	}
	
	public FullCalendar setEventClickEvent(FullCalendarEventClickEvent eventClickEvent)
	{
		this.eventClickEvent = eventClickEvent;
		return this;
	}
	
	public FullCalendarEventReceiveEvent getReceiveEvent()
	{
		return receiveEvent;
	}
	
	public FullCalendar setReceiveEvent(FullCalendarEventReceiveEvent receiveEvent)
	{
		this.receiveEvent = receiveEvent;
		return this;
	}
	
	public FullCalendarEventResizeEvent getEventResizeEvent()
	{
		return eventResizeEvent;
	}
	
	public FullCalendar setEventResizeEvent(FullCalendarEventResizeEvent eventResizeEvent)
	{
		this.eventResizeEvent = eventResizeEvent;
		return this;
	}
	
	public FullCalendarEventDropEvent getEventDropEvent()
	{
		return eventDropEvent;
	}
	
	public FullCalendar setEventDropEvent(FullCalendarEventDropEvent eventDropEvent)
	{
		this.eventDropEvent = eventDropEvent;
		return this;
	}
	
	public FullCalendarSelectEvent getSelectEvent()
	{
		return selectEvent;
	}
	
	public FullCalendar setSelectEvent(FullCalendarSelectEvent selectEvent)
	{
		this.selectEvent = selectEvent;
		return this;
	}
	
	public FullCalendarDropEvent getDropEvent()
	{
		return dropEvent;
	}
	
	public FullCalendar setDropEvent(FullCalendarDropEvent dropEvent)
	{
		this.dropEvent = dropEvent;
		return this;
	}
}
