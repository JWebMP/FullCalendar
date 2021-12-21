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
		Map<String, Object> info = (Map<String, Object>) call.getValue()
		                                                     .getUnknownFields()
		                                                     .get("infoObj");
		ObjectMapper mapper = GuiceContext.get(DefaultObjectMapper);
		FullCalendarDateClickEventInfo el = mapper.convertValue(info, FullCalendarDateClickEventInfo.class);
		onDateClick(call, response, el);
	}
	
}
