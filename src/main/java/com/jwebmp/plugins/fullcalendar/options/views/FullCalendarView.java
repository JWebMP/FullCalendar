package com.jwebmp.plugins.fullcalendar.options.views;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.*;
import com.jwebmp.plugins.fullcalendar.options.*;
import com.jwebmp.plugins.fullcalendar.options.titles.*;

import java.time.*;

@SuppressWarnings("JavaDoc")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarView<J extends FullCalendarView<J>> extends JavaScriptPart<J>
{
	@JsonIgnore
	private String name;
	
	private IFullCalendarViewType<?> type;
	private FullCalendarTitleFormat title;
	
	private LocalDateTime activeStart;
	private LocalDateTime activeEnd;
	private LocalDateTime currentStart;
	private LocalDateTime currentEnd;
	
	private FullCalendarViewDuration duration;
	private FullCalendarVisibleRange visibleRange;
	
	private Integer dayCount;
	private String buttonText;
	
	protected FullCalendarView()
	{
		//for json
	}
	
	public FullCalendarView(String name)
	{
		this.name = name;
	}
	
	/**
	 * Configured as a hashmap value for this view type
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Configured as a hashmap value for this view type
	 * @param name
	 * @return
	 */
	public FullCalendarView<J> setName(String name)
	{
		this.name = name;
		return this;
	}
	
	/**
	 * Name of one of the available views (a string).
	 * @return
	 */
	public IFullCalendarViewType<?> getType()
	{
		return type;
	}
	
	/**
	 * Name of one of the available views (a string).
	 * @param type
	 * @return
	 */
	public FullCalendarView<J> setType(IFullCalendarViewType<?> type)
	{
		this.type = type;
		return this;
	}
	
	/**
	 * Name of one of the available views (a string).
	 * @param type
	 * @return
	 */
	public FullCalendarView<J> setType(FullCalendarDefaultViews type)
	{
		this.type = type;
		return this;
	}
	
	
	/**
	 * Title text that is displayed at the top of the headerToolbar (such as “September 2009” or “Sep 7 - 13 2009”).
	 * @return
	 */
	public FullCalendarTitleFormat getTitle()
	{
		if (title == null)
		{
			title = new FullCalendarTitleFormat();
		}
		return title;
	}
	
	/**
	 * Title text that is displayed at the top of the headerToolbar (such as “September 2009” or “Sep 7 - 13 2009”).
	 * @param title
	 * @return
	 */
	public FullCalendarView<J> setTitle(FullCalendarTitleFormat title)
	{
		this.title = title;
		return this;
	}
	
	/**
	 * A Date that is the first visible day. In month view, this value is often before the 1st day of the month, because most months do not begin on the first day-of-week.
	 * @return
	 */
	public LocalDateTime getActiveStart()
	{
		return activeStart;
	}
	
	/**
	 * A Date that is the first visible day. In month view, this value is often before the 1st day of the month, because most months do not begin on the first day-of-week.
	 * @param activeStart
	 * @return
	 */
	public FullCalendarView<J> setActiveStart(LocalDateTime activeStart)
	{
		this.activeStart = activeStart;
		return this;
	}
	
	/**
	 * A Date that is the last visible day. Note: This value is exclusive. See how events are are parsed from a plain object for further details.
	 * @return
	 */
	public LocalDateTime getActiveEnd()
	{
		return activeEnd;
	}
	
	/**
	 * A Date that is the last visible day. Note: This value is exclusive. See how events are are parsed from a plain object for further details.
	 * @param activeEnd
	 * @return
	 */
	public FullCalendarView<J> setActiveEnd(LocalDateTime activeEnd)
	{
		this.activeEnd = activeEnd;
		return this;
	}
	
	/**
	 * A Date that is the start of the interval the view is trying to represent. For example, in month view, this will be the first of the month. This value disregards hidden days.
	 * @return
	 */
	public LocalDateTime getCurrentStart()
	{
		return currentStart;
	}
	
	/**
	 * A Date that is the start of the interval the view is trying to represent. For example, in month view, this will be the first of the month. This value disregards hidden days.
	 * @param currentStart
	 * @return
	 */
	public FullCalendarView<J> setCurrentStart(LocalDateTime currentStart)
	{
		this.currentStart = currentStart;
		return this;
	}
	
	/**
	 * A Date that is the end of the interval the view is trying to represent. Note: This value is exclusive. See how events are are parsed from a plain object for further details. For example, in month view, this will be the day after the last day of the month. This value disregards hidden days.
	 * @return
	 */
	public LocalDateTime getCurrentEnd()
	{
		return currentEnd;
	}
	
	/**
	 * A Date that is the end of the interval the view is trying to represent. Note: This value is exclusive. See how events are are parsed from a plain object for further details. For example, in month view, this will be the day after the last day of the month. This value disregards hidden days.
	 * @param currentEnd
	 * @return
	 */
	public FullCalendarView<J> setCurrentEnd(LocalDateTime currentEnd)
	{
		this.currentEnd = currentEnd;
		return this;
	}
	
	public FullCalendarViewDuration getDuration()
	{
		if (duration == null)
		{
			duration = new FullCalendarViewDuration();
		}
		return duration;
	}
	
	public FullCalendarView<J> setDuration(FullCalendarViewDuration duration)
	{
		this.duration = duration;
		return this;
	}
	
	public Integer getDayCount()
	{
		return dayCount;
	}
	
	public FullCalendarView<J> setDayCount(Integer dayCount)
	{
		this.dayCount = dayCount;
		return this;
	}
	
	public String getButtonText()
	{
		return buttonText;
	}
	
	public FullCalendarView<J> setButtonText(String buttonText)
	{
		this.buttonText = buttonText;
		return this;
	}
	
	public FullCalendarVisibleRange getVisibleRange()
	{
		if (visibleRange == null)
		{
			visibleRange = new FullCalendarVisibleRange();
		}
		return visibleRange;
	}
	
	public FullCalendarView<J> setVisibleRange(FullCalendarVisibleRange visibleRange)
	{
		this.visibleRange = visibleRange;
		return this;
	}
}
