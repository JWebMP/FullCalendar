/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.fullcalendar.options.toolbars;

import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.htmlbuilder.javascript.*;

/**
 * buttonIcons
 * <p>
 * Determines which icons are displayed in buttons of the header.
 * <p>
 * Object, default:
 * {
 * prev: 'left-single-arrow',
 * next: 'right-single-arrow',
 * prevYear: 'left-double-arrow',
 * nextYear: 'right-double-arrow'
 * }
 * This setting only takes affect when theme is false. If you want to change icons when theme is true, use themeButtonIcons instead.
 * <p>
 * A hash must be supplied that maps button names (from the header) to icon strings. These icon string are transformed into classNames which are styled by FullCalendar's CSS.
 * <p>
 * If a button does not have an entry, it falls back to using buttonText.
 * <p>
 * If you would prefer not to display any icons and would rather use buttonText instead, you can set the buttonIcons option to false.
 *
 * @author GedMarc
 * @since 04 Feb 2017
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)

public class FullCalendarHeaderButtonText
		extends JavaScriptPart<FullCalendarHeaderButtonText>
{


	/**
	 * The class name for the previous item icon
	 */
	private String today;
	/**
	 * The class name for the next item icon
	 */
	private String month;
	/**
	 * The previous year icon class
	 */
	private String week;
	/**
	 * The next year icon class
	 */
	private String day;
	/**
	 * The next year icon class
	 */
	private String list;
	/**
	 * Constructs a new button icon configuration
	 */
	public FullCalendarHeaderButtonText()
	{
		//Nothing Needed
	}

	/**
	 * Gets the previous class icon
	 *
	 * @return
	 */
	public String getToday()
	{
		return today;
	}

	/**
	 * Sets the previous class icon
	 *
	 * @param today
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonText setToday(String today)
	{
		this.today = today;
		return this;
	}

	/**
	 * Gets the next class icon
	 *
	 * @return
	 */
	public String getMonth()
	{
		return month;
	}

	/**
	 * Sets the next class icon
	 *
	 * @param month
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonText setMonth(String month)
	{
		this.month = month;
		return this;
	}

	/**
	 * Gets the previous year provider
	 *
	 * @return
	 */
	public String getWeek()
	{
		return week;
	}

	/**
	 * Sets the previous year provider
	 *
	 * @param week
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonText setWeek(String week)
	{
		this.week = week;
		return this;
	}

	/**
	 * Gets the next year class icon
	 *
	 * @return
	 */
	public String getDay()
	{
		return day;
	}

	/**
	 * Sets the next year class icon
	 *
	 * @param day
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonText setDay(String day)
	{
		this.day = day;
		return this;
	}
	
	/**
	 * Text that will be displayed on buttons of the headerToolbar/footerToolbar.
	 * @return
	 */
	public String getList()
	{
		return list;
	}
	
	/**
	 * Text that will be displayed on buttons of the headerToolbar/footerToolbar.
	 * @param list
	 * @return
	 */
	public FullCalendarHeaderButtonText setList(String list)
	{
		this.list = list;
		return this;
	}
}
