package com.jwebmp.plugins.fullcalendar.options.views;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.guicedee.services.jsonrepresentation.IJsonRepresentation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
