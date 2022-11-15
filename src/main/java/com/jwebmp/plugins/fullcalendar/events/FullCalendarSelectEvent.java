package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.*;
import com.guicedee.guicedinjection.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.*;

import java.util.*;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.*;


public abstract class FullCalendarSelectEvent extends ClickAdapter<FullCalendarSelectEvent>
{
	public FullCalendarSelectEvent()
	{
	}
	
	public abstract void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarSelectEventInfo selectEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
		LinkedHashMap map = (LinkedHashMap) call.getUnknownFields().get("infoObj");
		ObjectMapper mapper = GuiceContext.get(DefaultObjectMapper);
		FullCalendarSelectEventInfo el = mapper.convertValue(map, FullCalendarSelectEventInfo.class);
		el.updateDates();
		onSelect(call, response, el);
	}
	
}
