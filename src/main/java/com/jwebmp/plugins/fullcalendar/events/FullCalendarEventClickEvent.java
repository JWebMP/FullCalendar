package com.jwebmp.plugins.fullcalendar.events;

import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.*;

public abstract class FullCalendarEventClickEvent extends ClickAdapter<FullCalendarEventClickEvent>
{
	public FullCalendarEventClickEvent()
	{
	}
	
	public abstract void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
	
	}
	
}
