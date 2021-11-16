package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.json.*;
import com.jwebmp.core.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.events.click.*;

public class FullCalendarDateClickEvent extends ClickAdapter<FullCalendarDateClickEvent>
{
	public FullCalendarDateClickEvent()
	{
	}
	
	public void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarDateClickEventInfo dateClickEvent)
	{
	
	}
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
		System.out.println("calendar date click");
	}
	
	public String getFunction()
	{
		Event<?,?> e = this;
		
		String command = com.jwebmp.core.utilities.StaticStrings.STRING_ANGULAR_EVENT_START +
				e.renderVariables() +
				StaticStrings.STRING_CLOSING_BRACKET_SEMICOLON;
		
		return command;
	}
}
