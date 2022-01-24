package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.*;
import com.guicedee.guicedinjection.*;
import com.guicedee.guicedinjection.json.*;
import com.jwebmp.core.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.events.click.*;

import java.util.*;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.*;

public class FullCalendarDateClickEvent extends ClickAdapter<FullCalendarDateClickEvent>
{
	public FullCalendarDateClickEvent()
	{
	}
	
	public void onDateClick(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarDateClickEventInfo dateClickEvent)
	{
	
	}
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
		LinkedHashMap map = (LinkedHashMap) call.getValue().getUnknownFields().get("infoObj");
		LinkedHashMap<String,String> info = (LinkedHashMap<String, String>) map.get("resource");
		
		ObjectMapper mapper = GuiceContext.get(DefaultObjectMapper);
		FullCalendarDateClickEventInfo el = mapper.convertValue(info, FullCalendarDateClickEventInfo.class);
		el.updateDates();
		onDateClick(call, response, el);
	}
	
}
