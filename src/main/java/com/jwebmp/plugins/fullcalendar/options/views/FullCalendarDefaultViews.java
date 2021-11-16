package com.jwebmp.plugins.fullcalendar.options.views;

public enum FullCalendarDefaultViews implements IFullCalendarViewType<FullCalendarDefaultViews>
{
	/**
	 *  options apply to dayGridDay and timeGridDay views
	 */
	day,
	/**
	 *   // options apply to dayGridMonth, dayGridWeek, and dayGridDay views
	 */
	dayGrid,
	dayGridDay,
	dayGridMonth,
	dayGridWeek,
	
	listYear,
	listMonth,
	listWeek,
	listDay,
	list,
	
	timeline,
	timelineWeek,
	timelineMonth,
	
	/**
	 * options apply to timeGridWeek and timeGridDay views
	 */
	timeGrid,
	timeGridDay,
	timeGridWeek,
	
	/**
	 * options apply to dayGridWeek and timeGridWeek views
	 */
	week,

	
	resourceTimeGrid,
	resourceTimeGridDay,
	resourceTimeGridWeek,
	
	resourceTimeline,
	resourceTimelineWeek,

	resourceDayGridDay,
	resourceDayGridWeek,
	resourceDayGridMonth,
}
