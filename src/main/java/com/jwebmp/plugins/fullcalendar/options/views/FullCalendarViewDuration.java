package com.jwebmp.plugins.fullcalendar.options.views;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarViewDuration extends JavaScriptPart<FullCalendarViewDuration>
{
	private String years;
	private String months;
	private String weeks;
	private Integer days;
	
	public String getYears()
	{
		return years;
	}
	
	public FullCalendarViewDuration setYears(String years)
	{
		this.years = years;
		return this;
	}
	
	public String getMonths()
	{
		return months;
	}
	
	public FullCalendarViewDuration setMonths(String months)
	{
		this.months = months;
		return this;
	}
	
	public String getWeeks()
	{
		return weeks;
	}
	
	public FullCalendarViewDuration setWeeks(String weeks)
	{
		this.weeks = weeks;
		return this;
	}
	
	public Integer getDays()
	{
		return days;
	}
	
	public FullCalendarViewDuration setDays(Integer days)
	{
		this.days = days;
		return this;
	}
}
