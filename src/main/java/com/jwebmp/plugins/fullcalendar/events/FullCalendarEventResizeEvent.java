package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.*;
import com.guicedee.guicedinjection.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.*;

import java.util.*;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.*;

public abstract class FullCalendarEventResizeEvent extends ClickAdapter<FullCalendarEventResizeEvent>
{
	public FullCalendarEventResizeEvent()
	{
	}
	
	public abstract void onEventResize(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo oldEvent, FullCalendarEventInfo selectEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
		LinkedHashMap map = (LinkedHashMap) call.getUnknownFields().get("infoObj");
		LinkedHashMap<String,String> info = (LinkedHashMap<String, String>) map.get("event");
		LinkedHashMap<String,String> info2 = (LinkedHashMap<String, String>) map.get("oldEvent");
		
		ObjectMapper mapper = GuiceContext.get(DefaultObjectMapper);
		FullCalendarEventInfo el = mapper.convertValue(info, FullCalendarEventInfo.class);
		FullCalendarEventInfo el2 = mapper.convertValue(info2, FullCalendarEventInfo.class);
		el.updateDates();
		el2.updateDates();
		onEventResize(call, response,el2, el);
	}
	
}
