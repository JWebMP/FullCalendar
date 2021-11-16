package com.jwebmp.plugins.fullcalendar.options.views;

import com.fasterxml.jackson.annotation.*;
import com.guicedee.guicedinjection.representations.*;
import com.jwebmp.plugins.fullcalendar.*;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarViews implements IJsonRepresentation<FullCalendarViews>
{
	@JsonIgnore
	private List<FullCalendarView<?>> views = new ArrayList<>();
	
	public FullCalendarViews addView(FullCalendarView<?> view)
	{
		views.add(view);
		return this;
	}
	
	@JsonValue
	public Map<String, FullCalendarView<?>> viewsMap()
	{
		Map<String, FullCalendarView<?>> viewsMap = new LinkedHashMap<>();
		for (FullCalendarView<?> view : views)
		{
			viewsMap.put(view.getName(), view);
		}
		return viewsMap;
	}
}
