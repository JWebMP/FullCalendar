package com.jwebmp.plugins.fullcalendar.options;

import com.fasterxml.jackson.annotation.*;
import com.guicedee.guicedinjection.representations.*;

import java.time.*;
import java.time.format.*;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarTimeSlot implements IJsonRepresentation<FullCalendarTimeSlot>
{
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:SS");
	@JsonIgnore
	private LocalTime time;
	
	public LocalTime getTime()
	{
		return time;
	}
	
	public FullCalendarTimeSlot setTime(LocalTime time)
	{
		this.time = time;
		return this;
	}
	@JsonValue
	public String jsonRepresentation()
	{
		return formatter.format(time);
	}
}
