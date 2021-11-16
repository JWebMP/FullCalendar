package com.jwebmp.plugins.fullcalendar.options;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.*;

import java.time.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarVisibleRange extends JavaScriptPart<FullCalendarVisibleRange>
{
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate start;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate end;
	
	public FullCalendarVisibleRange()
	{
	}
	
	public FullCalendarVisibleRange(LocalDate start, LocalDate end)
	{
		this.start = start;
		this.end = end;
	}
	
	public LocalDate getStart()
	{
		return start;
	}
	
	public FullCalendarVisibleRange setStart(LocalDate start)
	{
		this.start = start;
		return this;
	}
	
	public LocalDate getEnd()
	{
		return end;
	}
	
	public FullCalendarVisibleRange setEnd(LocalDate end)
	{
		this.end = end;
		return this;
	}
}
