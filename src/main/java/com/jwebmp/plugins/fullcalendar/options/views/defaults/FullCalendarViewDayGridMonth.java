package com.jwebmp.plugins.fullcalendar.options.views.defaults;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.plugins.fullcalendar.options.views.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarViewDayGridMonth extends FullCalendarView<FullCalendarViewDayGridMonth>
{
	public FullCalendarViewDayGridMonth()
	{
		setType(FullCalendarDefaultViews.dayGridMonth);
	}
	
}
