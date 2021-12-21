package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.json.*;
import com.jwebmp.core.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.events.click.*;

public abstract class FullCalendarEventDropEvent extends ClickAdapter<FullCalendarEventDropEvent>
{
	public FullCalendarEventDropEvent()
	{
	}
	
	public abstract void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
	
	}
	
}
