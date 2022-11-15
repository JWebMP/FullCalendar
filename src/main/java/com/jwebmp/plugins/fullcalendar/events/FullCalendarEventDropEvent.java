package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.*;
import com.guicedee.guicedinjection.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.util.*;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.*;

public abstract class FullCalendarEventDropEvent extends ClickAdapter<FullCalendarEventDropEvent>
{
	public FullCalendarEventDropEvent()
	{
	}
	
	public abstract void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response,
	                                 FullCalendarResourceItem oldResource,FullCalendarResourceItem newResource,
	                                 FullCalendarEventInfo oldEvent,FullCalendarEventInfo newEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
		LinkedHashMap map = (LinkedHashMap) call.getUnknownFields().get("infoObj");
		LinkedHashMap<String,String> oldEventMap = (LinkedHashMap<String, String>) map.get("oldEvent");
		LinkedHashMap<String,String> eventMap = (LinkedHashMap<String, String>) map.get("event");
		LinkedHashMap<String,String> oldResourceMap = (LinkedHashMap<String, String>) map.get("oldResource");
		LinkedHashMap<String,String> newResourceMap = (LinkedHashMap<String, String>) map.get("newResource");
		
		ObjectMapper mapper = GuiceContext.get(DefaultObjectMapper);
		FullCalendarEventInfo el = mapper.convertValue(oldEventMap, FullCalendarEventInfo.class);
		FullCalendarEventInfo el2 = mapper.convertValue(eventMap, FullCalendarEventInfo.class);
		
		FullCalendarResourceItem ri1 =mapper.convertValue(oldResourceMap, FullCalendarResourceItem.class);
		FullCalendarResourceItem ri2 =mapper.convertValue(newResourceMap, FullCalendarResourceItem.class);
		el.updateDates();
		el2.updateDates();
		
		
		onEventDrop(call, response, ri1,ri2, el,el2);
	}
	
}
