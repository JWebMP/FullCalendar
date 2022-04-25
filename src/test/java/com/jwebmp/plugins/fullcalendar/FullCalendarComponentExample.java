package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.angular.services.annotations.*;
import com.jwebmp.plugins.fullcalendar.events.*;
import com.jwebmp.plugins.fullcalendar.options.*;

import java.time.*;

@NgComponent("full-cal-jwebmp")
public class FullCalendarComponentExample extends FullCalendar<FullCalendarComponentExample>
{
	public FullCalendarComponentExample()
	{
		super("fcjweb");
		setSelectEvent(new FullCalendarSelectEventTest());
	}
	
	@Override
	public FullCalendarEventsList getInitialEvents()
	{
		FullCalendarEventsList fullCalendarEventsList = new FullCalendarEventsList();
		fullCalendarEventsList.getEvents()
		                      .add(new FullCalendarEvent().setId("1")
		                                                  .setTitle("Event 1")
		                                                  .setStart(LocalDateTime.now()));
		return fullCalendarEventsList;
	}
}
