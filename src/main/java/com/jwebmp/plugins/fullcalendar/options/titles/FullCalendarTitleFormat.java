package com.jwebmp.plugins.fullcalendar.options.titles;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarTitleFormat extends JavaScriptPart<FullCalendarTitleFormat>
{
	private FullCalendarTitleFormatTypes year;
	private FullCalendarTitleFormatTypes month;
	private FullCalendarTitleFormatTypes day;
	private FullCalendarTitleFormatTypes weekday;
	private FullCalendarTitleFormatTypes hour;
	private FullCalendarTitleFormatTypes minute;
	private FullCalendarTitleFormatTypes second;
	private FullCalendarTitleFormatTypes hour12;
	private FullCalendarTitleFormatTypes timeZoneName;
	
	public FullCalendarTitleFormatTypes getYear()
	{
		return year;
	}
	
	public FullCalendarTitleFormat setYear(FullCalendarTitleFormatTypes year)
	{
		this.year = year;
		return this;
	}
	
	public FullCalendarTitleFormatTypes getMonth()
	{
		return month;
	}
	
	public FullCalendarTitleFormat setMonth(FullCalendarTitleFormatTypes month)
	{
		this.month = month;
		return this;
	}
	
	public FullCalendarTitleFormatTypes getDay()
	{
		return day;
	}
	
	public FullCalendarTitleFormat setDay(FullCalendarTitleFormatTypes day)
	{
		this.day = day;
		return this;
	}
}
