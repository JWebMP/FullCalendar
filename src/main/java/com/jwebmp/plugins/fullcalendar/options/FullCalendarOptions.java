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
package com.jwebmp.plugins.fullcalendar.options;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.core.base.servlets.interfaces.IDataComponent;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.fullcalendar.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;
import com.jwebmp.plugins.fullcalendar.options.titles.*;
import com.jwebmp.plugins.fullcalendar.options.toolbars.*;

import com.jwebmp.plugins.fullcalendar.options.views.*;
import com.jwebmp.plugins.fullcalendar.options.views.defaults.*;
import jakarta.validation.constraints.NotNull;

import java.time.*;
import java.util.*;

/**
 * All the options
 * <p>
 *
 * @author GedMarc
 * @version 1.0
 * <p>
 * <p>
 * @since Mar 4, 2015
 */

@SuppressWarnings("JavaDoc")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarOptions
		extends JavaScriptPart<FullCalendarOptions>
		implements IDataComponent
{
	
	
	/**
	 * The header
	 */
	private FullCalendarHeaderToolBarOptions headerToolbar;
	/**
	 * The footer
	 */
	private FullCalendarHeaderToolBarOptions footerToolbar;
	
	/**
	 * can click day/week names to navigate views
	 */
	private Boolean navLinks;
	/**
	 * The grid is editable or not
	 */
	private Boolean editable;
	/**
	 * Allow the "more" link when there's too many events
	 */
	private Boolean eventLimit;
	/**
	 * The events for this calendar
	 */
	private FullCalendarEventsList events;
	/**
	 * Renders the calendar with a given theme system.
	 * <p>
	 * String, default: 'standard'
	 * <p>
	 * 'standard'
	 * Renders a minimal look & feel, the look in most of the demos. Does not require any CSS files beyond the FullCalendar base files.
	 * 'bootstrap'
	 * Prepares the calendar for a Bootstrap 4 theme. There are other instructions you must follow to get Bootstrap theming working »
	 */
	private String themeSystem;
	
	/**
	 * You can specify options that apply only to specific calendar views. Provide separate options objects within the views option, keyed by the name of your view.
	 */
	private FullCalendarViews views;
	/**
	 * Reads the name field for the name of the initial view
	 */
	@JsonIgnore
	private FullCalendarView<?> initialView;
	
	/**
	 * Determines the text that will be displayed in the headerToolbar’s title.
	 */
	private FullCalendarTitleFormat titleFormat;
	/**
	 * Determines the separator text when formatting the date range in the toolbar title.
	 * <p>
	 * String, default: ' \u2013 ' (en dash)
	 */
	private String titleRangeSeparator;
	/**
	 * Text that will be displayed on buttons of the headerToolbar/footerToolbar.
	 */
	private FullCalendarHeaderButtonText buttonText;
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 */
	private FullCalendarHeaderButtonIcons buttonIcons;
	
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 */
	private FullCalendarHeaderButtonIcons bootstrapFontAwesome;
	
	private String height;
	private String contentHeight;
	private Double aspectRatio;
	private Boolean expandRows;
	private Boolean handleWindowResize;
	private Integer windowResizeDelay;
	private Boolean stickyHeaderDates;
	private Boolean stickyFooterScrollbar;
	
	
	/**
	 * The options for the component
	 */
	public FullCalendarOptions()
	{
		//Nothing Needed
	}
	
	/**
	 * The header options. never null
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions getHeaderToolbar()
	{
		if (headerToolbar == null)
		{
			headerToolbar = new FullCalendarHeaderToolBarOptions();
		}
		return headerToolbar;
	}
	
	/**
	 * Sets the header
	 *
	 * @param headerToolbar
	 * @return
	 */
	public FullCalendarOptions setHeaderToolbar(FullCalendarHeaderToolBarOptions headerToolbar)
	{
		this.headerToolbar = headerToolbar;
		return this;
	}
	
	/**
	 * The footer of the calendar
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions getFooterToolbar()
	{
		if (footerToolbar == null)
		{
			footerToolbar = new FullCalendarHeaderToolBarOptions();
		}
		return footerToolbar;
	}
	
	/**
	 * Sets the footer
	 *
	 * @param footerToolbar
	 * @return
	 */
	public FullCalendarOptions setFooterToolbar(FullCalendarHeaderToolBarOptions footerToolbar)
	{
		this.footerToolbar = footerToolbar;
		return this;
	}
	
	@NotNull
	@Override
	public StringBuilder renderData()
	{
		try
		{
			return new StringBuilder(GuiceContext.get(ObjectMapper.class)
			                                     .writeValueAsString(getEvents()));
		}
		catch (JsonProcessingException e)
		{
			return new StringBuilder();
		}
	}
	
	/**
	 * The events for this calendar
	 *
	 * @return
	 */
	public FullCalendarEventsList getEvents()
	{
		if (events == null)
		{
			events = new FullCalendarEventsList();
		}
		return events;
	}
	
	/**
	 * The events for this calendar
	 *
	 * @param events
	 * @return
	 */
	public FullCalendarOptions setEvents(FullCalendarEventsList events)
	{
		this.events = events;
		return this;
	}
	
	/**
	 * can click day/week names to navigate views
	 *
	 * @return
	 */
	public Boolean getNavLinks()
	{
		return navLinks;
	}
	
	/**
	 * can click day/week names to navigate views
	 *
	 * @param navLinks
	 * @return
	 */
	public FullCalendarOptions setNavLinks(Boolean navLinks)
	{
		this.navLinks = navLinks;
		return this;
	}
	
	/**
	 * The grid is editable or not
	 *
	 * @return
	 */
	public Boolean getEditable()
	{
		return editable;
	}
	
	/**
	 * The grid is editable or not
	 *
	 * @param editable
	 * @return
	 */
	public FullCalendarOptions setEditable(Boolean editable)
	{
		this.editable = editable;
		return this;
	}
	
	/**
	 * Allow the "more" link when there's too many events
	 *
	 * @return
	 */
	public Boolean getEventLimit()
	{
		return eventLimit;
	}
	
	/**
	 * Allow the "more" link when there's too many events
	 *
	 * @param eventLimit
	 * @return
	 */
	public FullCalendarOptions setEventLimit(Boolean eventLimit)
	{
		this.eventLimit = eventLimit;
		return this;
	}
	
	/**
	 * You can specify options that apply only to specific calendar views. Provide separate options objects within the views option, keyed by the name of your view.
	 *
	 * @return
	 */
	public FullCalendarViews getViews()
	{
		if (views == null)
		{
			views = new FullCalendarViews();
		}
		return views;
	}
	
	/**
	 * You can specify options that apply only to specific calendar views. Provide separate options objects within the views option, keyed by the name of your view.
	 *
	 * @param views
	 * @return
	 */
	public FullCalendarOptions setViews(FullCalendarViews views)
	{
		this.views = views;
		return this;
	}
	
	@JsonProperty("initialView")
	public String initialViewRender()
	{
		if (initialView != null)
		{
			return initialView.getName();
		}
		return null;
	}
	
	public FullCalendarView<?> getInitialView()
	{
		if (initialView == null)
		{
			initialView = new FullCalendarViewDayGridMonth();
		}
		return initialView;
	}
	
	/**
	 * An initial view with the name specified
	 *
	 * @param initialView
	 * @return
	 */
	public FullCalendarOptions setInitialView(FullCalendarView<?> initialView)
	{
		this.initialView = initialView;
		return this;
	}
	
	/**
	 * Determines the text that will be displayed in the headerToolbar’s title.
	 *
	 * @return
	 */
	public FullCalendarTitleFormat getTitleFormat()
	{
		if (titleFormat == null)
		{
			titleFormat = new FullCalendarTitleFormat();
		}
		return titleFormat;
	}
	
	/**
	 * Determines the text that will be displayed in the headerToolbar’s title.
	 *
	 * @param titleFormat
	 * @return
	 */
	public FullCalendarOptions setTitleFormat(FullCalendarTitleFormat titleFormat)
	{
		this.titleFormat = titleFormat;
		return this;
	}
	
	/**
	 * Determines the separator text when formatting the date range in the toolbar title.
	 * <p>
	 * String, default: ' \u2013 ' (en dash)
	 *
	 * @return
	 */
	public String getTitleRangeSeparator()
	{
		return titleRangeSeparator;
	}
	
	/**
	 * Determines the separator text when formatting the date range in the toolbar title.
	 * <p>
	 * String, default: ' \u2013 ' (en dash)
	 *
	 * @param titleRangeSeparator
	 * @return
	 */
	public FullCalendarOptions setTitleRangeSeparator(String titleRangeSeparator)
	{
		this.titleRangeSeparator = titleRangeSeparator;
		return this;
	}
	
	/**
	 * Text that will be displayed on buttons of the headerToolbar/footerToolbar.
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonText getButtonText()
	{
		if (buttonText == null)
		{
			buttonText = new FullCalendarHeaderButtonText();
		}
		return buttonText;
	}
	
	/**
	 * Text that will be displayed on buttons of the headerToolbar/footerToolbar.
	 *
	 * @param buttonText
	 * @return
	 */
	public FullCalendarOptions setButtonText(FullCalendarHeaderButtonText buttonText)
	{
		this.buttonText = buttonText;
		return this;
	}
	
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonIcons getButtonIcons()
	{
		if (buttonIcons == null)
		{
			buttonIcons = new FullCalendarHeaderButtonIcons();
		}
		return buttonIcons;
	}
	
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 *
	 * @param buttonIcons
	 * @return
	 */
	public FullCalendarOptions setButtonIcons(FullCalendarHeaderButtonIcons buttonIcons)
	{
		this.buttonIcons = buttonIcons;
		return this;
	}
	
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 *
	 * @return
	 */
	public FullCalendarHeaderButtonIcons getBootstrapFontAwesome()
	{
		if (bootstrapFontAwesome == null)
		{
			bootstrapFontAwesome = new FullCalendarHeaderButtonIcons();
		}
		return bootstrapFontAwesome;
	}
	
	/**
	 * Icons that will be displayed in buttons of the headerToolbar/footerToolbar.
	 * This setting only takes affect when themeSystem is 'standard'. If you want to change icons when themeSystem is 'bootstrap', use bootstrapFontAwesome instead.
	 *
	 * @param bootstrapFontAwesome
	 * @return
	 */
	public FullCalendarOptions setBootstrapFontAwesome(FullCalendarHeaderButtonIcons bootstrapFontAwesome)
	{
		this.bootstrapFontAwesome = bootstrapFontAwesome;
		return this;
	}
	
	/**
	 * Renders the calendar with a given theme system.
	 * <p>
	 * String, default: 'standard'
	 * <p>
	 * 'standard'
	 * Renders a minimal look & feel, the look in most of the demos. Does not require any CSS files beyond the FullCalendar base files.
	 * 'bootstrap'
	 * Prepares the calendar for a Bootstrap 4 theme. There are other instructions you must follow to get Bootstrap theming working »
	 *
	 * @return
	 */
	public String getThemeSystem()
	{
		return themeSystem;
	}
	
	/**
	 * Renders the calendar with a given theme system.
	 * <p>
	 * String, default: 'standard'
	 * <p>
	 * 'standard'
	 * Renders a minimal look & feel, the look in most of the demos. Does not require any CSS files beyond the FullCalendar base files.
	 * 'bootstrap'
	 * Prepares the calendar for a Bootstrap 4 theme. There are other instructions you must follow to get Bootstrap theming working »
	 *
	 * @param themeSystem
	 * @return
	 */
	public FullCalendarOptions setThemeSystem(String themeSystem)
	{
		this.themeSystem = themeSystem;
		return this;
	}
	
	/**
	 * Sets the height of the entire calendar, including header and footer.
	 * <p>
	 * Integer, "auto", a CSS value like "100%"
	 *
	 * @return
	 */
	public String getHeight()
	{
		return height;
	}
	
	/**
	 * Sets the height of the entire calendar, including header and footer.
	 * <p>
	 * Integer, "auto", a CSS value like "100%"
	 *
	 * @param height
	 * @return
	 */
	public FullCalendarOptions setHeight(String height)
	{
		this.height = height;
		return this;
	}
	
	/**
	 * Sets the height of the view area of the calendar.
	 * <p>
	 * Integer, "auto"
	 *
	 * @return
	 */
	public String getContentHeight()
	{
		return contentHeight;
	}
	
	/**
	 * Sets the height of the view area of the calendar.
	 * <p>
	 * Integer, "auto"
	 *
	 * @param contentHeight
	 * @return
	 */
	public FullCalendarOptions setContentHeight(String contentHeight)
	{
		this.contentHeight = contentHeight;
		return this;
	}
	
	/**
	 * Sets the width-to-height aspect ratio of the calendar.
	 * <p>
	 * Float, default: 1.35
	 *
	 * @return
	 */
	public Double getAspectRatio()
	{
		return aspectRatio;
	}
	
	/**
	 * Sets the width-to-height aspect ratio of the calendar.
	 * <p>
	 * Float, default: 1.35
	 *
	 * @param aspectRatio
	 * @return
	 */
	public FullCalendarOptions setAspectRatio(Double aspectRatio)
	{
		this.aspectRatio = aspectRatio;
		return this;
	}
	
	/**
	 * If the rows of a given view don’t take up the entire height, they will expand to fit.
	 * <p>
	 * Boolean, default: false
	 *
	 * @return
	 */
	public Boolean getExpandRows()
	{
		return expandRows;
	}
	
	/**
	 * If the rows of a given view don’t take up the entire height, they will expand to fit.
	 * <p>
	 * Boolean, default: false
	 *
	 * @param expandRows
	 * @return
	 */
	public FullCalendarOptions setExpandRows(Boolean expandRows)
	{
		this.expandRows = expandRows;
		return this;
	}
	
	/**
	 * Whether to automatically resize the calendar when the browser window resizes.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getHandleWindowResize()
	{
		return handleWindowResize;
	}
	
	/**
	 * Whether to automatically resize the calendar when the browser window resizes.
	 * <p>
	 * Boolean, default: true
	 *
	 * @param handleWindowResize
	 * @return
	 */
	public FullCalendarOptions setHandleWindowResize(Boolean handleWindowResize)
	{
		this.handleWindowResize = handleWindowResize;
		return this;
	}
	
	/**
	 * The time the calendar will wait to adjust its size after a window resize occurs, in milliseconds.
	 * <p>
	 * Integer. default: 100
	 *
	 * @return
	 */
	public Integer getWindowResizeDelay()
	{
		return windowResizeDelay;
	}
	
	/**
	 * The time the calendar will wait to adjust its size after a window resize occurs, in milliseconds.
	 * <p>
	 * Integer. default: 100
	 *
	 * @param windowResizeDelay
	 * @return
	 */
	public FullCalendarOptions setWindowResizeDelay(Integer windowResizeDelay)
	{
		this.windowResizeDelay = windowResizeDelay;
		return this;
	}
	
	/**
	 * Whether to fix the date-headers at the top of the calendar to the viewport while scrolling.
	 * <p>
	 * 'auto' (the default), true, false
	 * <p>
	 * The default value of 'auto' will look at the height setting and if it is 'auto', meaning the calendar has the potential to be very tall, sticky-header-dates will be activated.
	 *
	 * @return
	 */
	public Boolean getStickyHeaderDates()
	{
		return stickyHeaderDates;
	}
	
	/**
	 * Whether to fix the date-headers at the top of the calendar to the viewport while scrolling.
	 * <p>
	 * 'auto' (the default), true, false
	 * <p>
	 * The default value of 'auto' will look at the height setting and if it is 'auto', meaning the calendar has the potential to be very tall, sticky-header-dates will be activated.
	 *
	 * @param stickyHeaderDates
	 * @return
	 */
	public FullCalendarOptions setStickyHeaderDates(Boolean stickyHeaderDates)
	{
		this.stickyHeaderDates = stickyHeaderDates;
		return this;
	}
	
	/**
	 * Whether to fix the view’s horizontal scrollbar to the bottom of the viewport while scrolling.
	 * <p>
	 * 'auto' (the default), true, false
	 * <p>
	 * When a view has horizontal scrollbars, for example, timeline view or the daygrid or timegrid views when the (dayMinWidth) settings is activated, and calendar is within view but the scrollbars are below the fold of the screen, this setting will fix the scrollbar to the bottom of the viewport.
	 * <p>
	 * The default value of 'auto' will look at the height setting and if it is 'auto', meaning the calendar has the potential to be very tall, sticky-footer-scrollbars will be activated.
	 *
	 * @return
	 */
	public Boolean getStickyFooterScrollbar()
	{
		return stickyFooterScrollbar;
	}
	
	/**
	 * Whether to fix the view’s horizontal scrollbar to the bottom of the viewport while scrolling.
	 * <p>
	 * 'auto' (the default), true, false
	 * <p>
	 * When a view has horizontal scrollbars, for example, timeline view or the daygrid or timegrid views when the (dayMinWidth) settings is activated, and calendar is within view but the scrollbars are below the fold of the screen, this setting will fix the scrollbar to the bottom of the viewport.
	 * <p>
	 * The default value of 'auto' will look at the height setting and if it is 'auto', meaning the calendar has the potential to be very tall, sticky-footer-scrollbars will be activated.
	 *
	 * @param stickyFooterScrollbar
	 * @return
	 */
	public FullCalendarOptions setStickyFooterScrollbar(Boolean stickyFooterScrollbar)
	{
		this.stickyFooterScrollbar = stickyFooterScrollbar;
		return this;
	}
	
	
	private Boolean fixedWeekCount;
	private Boolean showNonCurrentDates;
	
	/**
	 * Determines the number of weeks displayed in a month view.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getFixedWeekCount()
	{
		return fixedWeekCount;
	}
	
	/**
	 * Determines the number of weeks displayed in a month view.
	 * <p>
	 * Boolean, default: true
	 *
	 * @param fixedWeekCount
	 * @return
	 */
	public FullCalendarOptions setFixedWeekCount(Boolean fixedWeekCount)
	{
		this.fixedWeekCount = fixedWeekCount;
		return this;
	}
	
	/**
	 * In month view, whether dates in the previous or next month should be rendered at all.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getShowNonCurrentDates()
	{
		return showNonCurrentDates;
	}
	
	/**
	 * In month view, whether dates in the previous or next month should be rendered at all.
	 * <p>
	 * Boolean, default: true
	 *
	 * @param showNonCurrentDates
	 * @return
	 */
	public FullCalendarOptions setShowNonCurrentDates(Boolean showNonCurrentDates)
	{
		this.showNonCurrentDates = showNonCurrentDates;
		return this;
	}
	
	
	private FullCalendarTitleFormatTypes listDayFormat;
	private FullCalendarTitleFormatTypes listDaySideFormat;
	
	/**
	 * A Date Formatter that affects the text on the right side of the day headings in list view.
	 *
	 * @return
	 */
	public FullCalendarTitleFormatTypes getListDayFormat()
	{
		return listDayFormat;
	}
	
	/**
	 * A Date Formatter that affects the text on the right side of the day headings in list view.
	 *
	 * @param listDayFormat
	 * @return
	 */
	public FullCalendarOptions setListDayFormat(FullCalendarTitleFormatTypes listDayFormat)
	{
		this.listDayFormat = listDayFormat;
		return this;
	}
	
	/**
	 * A Date Formatter that affects the text on the left side of the day headings in list view.
	 *
	 * @return
	 */
	public FullCalendarTitleFormatTypes getListDaySideFormat()
	{
		return listDaySideFormat;
	}
	
	/**
	 * A Date Formatter that affects the text on the left side of the day headings in list view.
	 *
	 * @param listDaySideFormat
	 * @return
	 */
	public FullCalendarOptions setListDaySideFormat(FullCalendarTitleFormatTypes listDaySideFormat)
	{
		this.listDaySideFormat = listDaySideFormat;
		return this;
	}
	
	
	private Integer eventMinHeight;
	private Integer eventShortHeight;
	private Boolean slotEventOverlap;
	private Boolean allDaySlot;
	
	/**
	 * In timeGrid view, the minimum height an event is allowed to be.
	 * <p>
	 * Number, default: 15
	 *
	 * @return
	 */
	public Integer getEventMinHeight()
	{
		return eventMinHeight;
	}
	
	/**
	 * In timeGrid view, the minimum height an event is allowed to be.
	 * <p>
	 * Number, default: 15
	 *
	 * @param eventMinHeight
	 * @return
	 */
	public FullCalendarOptions setEventMinHeight(Integer eventMinHeight)
	{
		this.eventMinHeight = eventMinHeight;
		return this;
	}
	
	/**
	 * In timeGrid view, the height threshold for when an event has a “short” style.
	 * <p>
	 * Number, default: 30
	 *
	 * @return
	 */
	public Integer getEventShortHeight()
	{
		return eventShortHeight;
	}
	
	/**
	 * In timeGrid view, the height threshold for when an event has a “short” style.
	 * <p>
	 * Number, default: 30
	 *
	 * @param eventShortHeight
	 * @return
	 */
	public FullCalendarOptions setEventShortHeight(Integer eventShortHeight)
	{
		this.eventShortHeight = eventShortHeight;
		return this;
	}
	
	/**
	 * Determines if timed events in TimeGrid view should visually overlap.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * When set to true (the default), events will overlap each other. At most half of each event will be obscured:
	 *
	 * @return
	 */
	public Boolean getSlotEventOverlap()
	{
		return slotEventOverlap;
	}
	
	/**
	 * Determines if timed events in TimeGrid view should visually overlap.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * When set to true (the default), events will overlap each other. At most half of each event will be obscured:
	 *
	 * @param slotEventOverlap
	 * @return
	 */
	public FullCalendarOptions setSlotEventOverlap(Boolean slotEventOverlap)
	{
		this.slotEventOverlap = slotEventOverlap;
		return this;
	}
	
	/**
	 * Determines if the “all-day” slot is displayed at the top of the calendar.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getAllDaySlot()
	{
		return allDaySlot;
	}
	
	/**
	 * Determines if the “all-day” slot is displayed at the top of the calendar.
	 * <p>
	 * Boolean, default: true
	 *
	 * @param allDaySlot
	 * @return
	 */
	public FullCalendarOptions setAllDaySlot(Boolean allDaySlot)
	{
		this.allDaySlot = allDaySlot;
		return this;
	}
	
	private String resourceGroupField;
	private String resourceAreaWidth;
	private List<FullCalendarResourceAreaColumn> resourceAreaColumns;
	private Boolean resourcesInitiallyExpanded;
	private Integer slotMinWidth;
	private Integer eventMinWidth;
	
	/**
	 * Visually groups resources by certain criteria.
	 *
	 * @return
	 */
	public String getResourceGroupField()
	{
		return resourceGroupField;
	}
	
	/**
	 * Visually groups resources by certain criteria.
	 *
	 * @param resourceGroupField
	 * @return
	 */
	public FullCalendarOptions setResourceGroupField(String resourceGroupField)
	{
		this.resourceGroupField = resourceGroupField;
		return this;
	}
	
	/**
	 * Determines the width of the area that contains the list of resources.
	 * <p>
	 * default: "30%"
	 *
	 * @return
	 */
	public String getResourceAreaWidth()
	{
		return resourceAreaWidth;
	}
	
	/**
	 * Determines the width of the area that contains the list of resources.
	 * <p>
	 * default: "30%"
	 *
	 * @param resourceAreaWidth
	 * @return
	 */
	public FullCalendarOptions setResourceAreaWidth(String resourceAreaWidth)
	{
		this.resourceAreaWidth = resourceAreaWidth;
		return this;
	}
	
	/**
	 * Turns the resource area from a plain list of titles into a grid of data.
	 * <p>
	 * An array of objects can be provided, each with information about a column:
	 *
	 * @return
	 */
	public List<FullCalendarResourceAreaColumn> getResourceAreaColumns()
	{
		return resourceAreaColumns;
	}
	
	/**
	 * Turns the resource area from a plain list of titles into a grid of data.
	 * <p>
	 * An array of objects can be provided, each with information about a column:
	 *
	 * @param resourceAreaColumns
	 * @return
	 */
	public FullCalendarOptions setResourceAreaColumns(List<FullCalendarResourceAreaColumn> resourceAreaColumns)
	{
		this.resourceAreaColumns = resourceAreaColumns;
		return this;
	}
	
	/**
	 * Whether child resources should be expanded when the view loads.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getResourcesInitiallyExpanded()
	{
		return resourcesInitiallyExpanded;
	}
	
	/**
	 * Whether child resources should be expanded when the view loads.
	 * <p>
	 * Boolean, default: true
	 *
	 * @param resourcesInitiallyExpanded
	 * @return
	 */
	public FullCalendarOptions setResourcesInitiallyExpanded(Boolean resourcesInitiallyExpanded)
	{
		this.resourcesInitiallyExpanded = resourcesInitiallyExpanded;
		return this;
	}
	
	/**
	 * Determines how wide each of the time-axis slots will be. Specified as a number of pixels.
	 * <p>
	 * When not specified, a reasonable value will be automatically computed.
	 *
	 * @return
	 */
	public Integer getSlotMinWidth()
	{
		return slotMinWidth;
	}
	
	/**
	 * Determines how wide each of the time-axis slots will be. Specified as a number of pixels.
	 * <p>
	 * When not specified, a reasonable value will be automatically computed.
	 *
	 * @param slotMinWidth
	 * @return
	 */
	public FullCalendarOptions setSlotMinWidth(Integer slotMinWidth)
	{
		this.slotMinWidth = slotMinWidth;
		return this;
	}
	
	/**
	 * In timeline view, the minimum width an event is allowed to be.
	 * <p>
	 * Number, default: 30
	 *
	 * @return
	 */
	public Integer getEventMinWidth()
	{
		return eventMinWidth;
	}
	
	/**
	 * In timeline view, the minimum width an event is allowed to be.
	 * <p>
	 * Number, default: 30
	 *
	 * @param eventMinWidth
	 * @return
	 */
	public FullCalendarOptions setEventMinWidth(Integer eventMinWidth)
	{
		this.eventMinWidth = eventMinWidth;
		return this;
	}
	
	private Boolean datesAboveResources;
	
	/**
	 * Determines if resourceTimeGrid or resourceDayGrid views should render their date headings above their resource headings.
	 * <p>
	 * Boolean, default: false
	 *
	 * @return
	 */
	public Boolean getDatesAboveResources()
	{
		return datesAboveResources;
	}
	
	/**
	 * Determines if resourceTimeGrid or resourceDayGrid views should render their date headings above their resource headings.
	 * <p>
	 * Boolean, default: false
	 *
	 * @param datesAboveResources
	 * @return
	 */
	public FullCalendarOptions setDatesAboveResources(Boolean datesAboveResources)
	{
		this.datesAboveResources = datesAboveResources;
		return this;
	}
	
	private FullCalendarViewDuration duration;
	private Integer dayCount;
	private FullCalendarVisibleRange visibleRange;
	
	/**
	 * Sets the exact duration of a custom view.
	 *
	 * @return
	 */
	public FullCalendarViewDuration getDuration()
	{
		return duration;
	}
	
	/**
	 * Sets the exact duration of a custom view.
	 *
	 * @param duration
	 * @return
	 */
	public FullCalendarOptions setDuration(FullCalendarViewDuration duration)
	{
		this.duration = duration;
		return this;
	}
	
	/**
	 * Sets the exact date range that is visible in a view.
	 *
	 * @return
	 */
	public FullCalendarVisibleRange getVisibleRange()
	{
		return visibleRange;
	}
	
	/**
	 * Sets the exact date range that is visible in a view.
	 *
	 * @param visibleRange
	 * @return
	 */
	public FullCalendarOptions setVisibleRange(FullCalendarVisibleRange visibleRange)
	{
		this.visibleRange = visibleRange;
		return this;
	}
	
	private Boolean weekends;
	private Set<Integer> hiddenDays;
	private Boolean dayHeaders;
	private FullCalendarTitleFormat dayHeaderFormat;
	private Integer dayMinWidth;
	private FullCalendarTimeSlot slotDuration;
	private FullCalendarTimeSlot slotLabelInterval;
	private FullCalendarTitleFormat slotLabelFormat;
	private FullCalendarTimeSlot slotMinTime;
	private FullCalendarTimeSlot slotMaxTime;
	private LocalDateTime scrollTime;
	private Boolean scrollTimeReset;
	
	/**
	 * Number of days to show
	 *
	 * @return
	 */
	public Integer getDayCount()
	{
		return dayCount;
	}
	
	/**
	 * Number of days to show
	 *
	 * @param dayCount
	 * @return
	 */
	public FullCalendarOptions setDayCount(Integer dayCount)
	{
		this.dayCount = dayCount;
		return this;
	}
	
	/**
	 * Whether to include Saturday/Sunday columns in any of the calendar views.
	 * <p>
	 * Boolean, default: true
	 *
	 * @return
	 */
	public Boolean getWeekends()
	{
		return weekends;
	}
	
	/**
	 * Whether to include Saturday/Sunday columns in any of the calendar views.
	 * *
	 * * Boolean, default: true
	 *
	 * @param weekends
	 * @return
	 */
	public FullCalendarOptions setWeekends(Boolean weekends)
	{
		this.weekends = weekends;
		return this;
	}
	
	/**
	 * Exclude certain days-of-the-week from being displayed.
	 * <p>
	 * Array, default: []
	 * <p>
	 * The value is an array of day-of-week indices to hide. Each index is zero-base (Sunday=0) and ranges from 0-6. Example:
	 * <p>
	 * hiddenDays: [ 2, 4 ] // hide Tuesdays and Thursdays
	 * <p>
	 * hiddenDays: [ 1, 3, 5 ] // hide Mondays, Wednesdays, and Fridays
	 *
	 * @return
	 */
	public Set<Integer> getHiddenDays()
	{
		return hiddenDays;
	}
	
	/**
	 * Exclude certain days-of-the-week from being displayed.
	 * <p>
	 * Array, default: []
	 * <p>
	 * The value is an array of day-of-week indices to hide. Each index is zero-base (Sunday=0) and ranges from 0-6. Example:
	 * <p>
	 * hiddenDays: [ 2, 4 ] // hide Tuesdays and Thursdays
	 * <p>
	 * hiddenDays: [ 1, 3, 5 ] // hide Mondays, Wednesdays, and Fridays
	 *
	 * @param hiddenDays
	 * @return
	 */
	public FullCalendarOptions setHiddenDays(Set<Integer> hiddenDays)
	{
		this.hiddenDays = hiddenDays;
		return this;
	}
	
	/**
	 * Whether the day headers should appear. For the Month, TimeGrid, and DayGrid views.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * The default is to show these column headers, but if you would like to hide them, especially if you have a single-day view and you feel the column header is unnecessary, you can set it to false.
	 *
	 * @return
	 */
	public Boolean getDayHeaders()
	{
		return dayHeaders;
	}
	
	/**
	 * Whether the day headers should appear. For the Month, TimeGrid, and DayGrid views.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * The default is to show these column headers, but if you would like to hide them, especially if you have a single-day view and you feel the column header is unnecessary, you can set it to false.
	 *
	 * @param dayHeaders
	 * @return
	 */
	public FullCalendarOptions setDayHeaders(Boolean dayHeaders)
	{
		this.dayHeaders = dayHeaders;
		return this;
	}
	
	/**
	 * Determines the text that will be displayed on the calendar’s column headings.
	 * <p>
	 * Date Formatter, default:
	 * <p>
	 * // like 'Mon', for month view
	 * { weekday: 'short' }
	 * <p>
	 * // like 'Mon 9/7', for week views
	 * { weekday: 'short', month: 'numeric', day: 'numeric', omitCommas: true }
	 * <p>
	 * // like 'Monday', for day views
	 * { weekday: 'long' }
	 * As noted above, each view has a specific default. Get fine-tuned control with View-Specific Options. A single string alone will set the value for all views.
	 *
	 * @return
	 */
	public FullCalendarTitleFormat getDayHeaderFormat()
	{
		return dayHeaderFormat;
	}
	
	/**
	 * Determines the text that will be displayed on the calendar’s column headings.
	 * <p>
	 * Date Formatter, default:
	 * <p>
	 * // like 'Mon', for month view
	 * { weekday: 'short' }
	 * <p>
	 * // like 'Mon 9/7', for week views
	 * { weekday: 'short', month: 'numeric', day: 'numeric', omitCommas: true }
	 * <p>
	 * // like 'Monday', for day views
	 * { weekday: 'long' }
	 * As noted above, each view has a specific default. Get fine-tuned control with View-Specific Options. A single string alone will set the value for all views.
	 *
	 * @param dayHeaderFormat
	 * @return
	 */
	public FullCalendarOptions setDayHeaderFormat(FullCalendarTitleFormat dayHeaderFormat)
	{
		this.dayHeaderFormat = dayHeaderFormat;
		return this;
	}
	
	/**
	 * If specified, when the calendar gets narrow enough where day cells can no longer meet their dayMinWidth, horizontal scrollbars will appear.
	 * <p>
	 * number of pixels, undefined
	 * <p>
	 * For vertical resource view, daygrid view, and timegrid view.
	 *
	 * @return
	 */
	public Integer getDayMinWidth()
	{
		return dayMinWidth;
	}
	
	/**
	 * If specified, when the calendar gets narrow enough where day cells can no longer meet their dayMinWidth, horizontal scrollbars will appear.
	 * <p>
	 * number of pixels, undefined
	 * <p>
	 * For vertical resource view, daygrid view, and timegrid view.
	 *
	 * @param dayMinWidth
	 * @return
	 */
	public FullCalendarOptions setDayMinWidth(Integer dayMinWidth)
	{
		this.dayMinWidth = dayMinWidth;
		return this;
	}
	
	/**
	 * The frequency for displaying time slots.
	 * <p>
	 * Duration, default: '00:30:00' (30 minutes)
	 *
	 * @return
	 */
	public FullCalendarTimeSlot getSlotDuration()
	{
		return slotDuration;
	}
	
	/**
	 * The frequency for displaying time slots.
	 * <p>
	 * Duration, default: '00:30:00' (30 minutes)
	 *
	 * @param slotDuration
	 * @return
	 */
	public FullCalendarOptions setSlotDuration(FullCalendarTimeSlot slotDuration)
	{
		this.slotDuration = slotDuration;
		return this;
	}
	
	/**
	 * The frequency that the time slots should be labelled with text.
	 * <p>
	 * Duration
	 * <p>
	 * If not specified, a reasonable value will be automatically computed based on slotDuration. When specifying this option, give a Duration-ish input, like "01:00" or {hours:1}. This will cause the header labels to appear on the hour marks, even if slotDuration was hypothetically 15 or 30 minutes long.
	 *
	 * @return
	 */
	public FullCalendarTimeSlot getSlotLabelInterval()
	{
		return slotLabelInterval;
	}
	
	/**
	 * The frequency that the time slots should be labelled with text.
	 * <p>
	 * Duration
	 * <p>
	 * If not specified, a reasonable value will be automatically computed based on slotDuration. When specifying this option, give a Duration-ish input, like "01:00" or {hours:1}. This will cause the header labels to appear on the hour marks, even if slotDuration was hypothetically 15 or 30 minutes long.
	 *
	 * @param slotLabelInterval
	 * @return
	 */
	public FullCalendarOptions setSlotLabelInterval(FullCalendarTimeSlot slotLabelInterval)
	{
		this.slotLabelInterval = slotLabelInterval;
		return this;
	}
	
	/**
	 * Determines the text that will be displayed within a time slot.
	 * <p>
	 * Date Formatter, default:
	 * <p>
	 * {
	 * hour: 'numeric',
	 * minute: '2-digit',
	 * omitZeroMinute: true,
	 * meridiem: 'short'
	 * }
	 * The default English value will produce times that look like 5pm and 5:30pm.
	 * <p>
	 * Timeline View
	 * For Timeline view, one string can be given for creating a single header row, or an array of strings can be given to create multiple tiers of header rows.
	 * <p>
	 * slotLabelFormat: [
	 * { month: 'long', year: 'numeric' }, // top level of text
	 * { weekday: 'short' } // lower level of text
	 * ]
	 *
	 * @return
	 */
	public FullCalendarTitleFormat getSlotLabelFormat()
	{
		return slotLabelFormat;
	}
	
	/**
	 * Determines the text that will be displayed within a time slot.
	 * <p>
	 * Date Formatter, default:
	 * <p>
	 * {
	 * hour: 'numeric',
	 * minute: '2-digit',
	 * omitZeroMinute: true,
	 * meridiem: 'short'
	 * }
	 * The default English value will produce times that look like 5pm and 5:30pm.
	 * <p>
	 * Timeline View
	 * For Timeline view, one string can be given for creating a single header row, or an array of strings can be given to create multiple tiers of header rows.
	 * <p>
	 * slotLabelFormat: [
	 * { month: 'long', year: 'numeric' }, // top level of text
	 * { weekday: 'short' } // lower level of text
	 * ]
	 *
	 * @param slotLabelFormat
	 * @return
	 */
	public FullCalendarOptions setSlotLabelFormat(FullCalendarTitleFormat slotLabelFormat)
	{
		this.slotLabelFormat = slotLabelFormat;
		return this;
	}
	
	/**
	 * Determines the first time slot that will be displayed for each day.
	 * <p>
	 * Duration, default: "00:00:00"
	 * <p>
	 * The default "00:00:00" means the start time will be at the very beginning of the day (midnight).
	 * <p>
	 * Determines the first time slot, even when the scrollbars have been scrolled all the way back.
	 *
	 * @return
	 */
	public FullCalendarTimeSlot getSlotMinTime()
	{
		return slotMinTime;
	}
	
	/**
	 * Determines the first time slot that will be displayed for each day.
	 * <p>
	 * Duration, default: "00:00:00"
	 * <p>
	 * The default "00:00:00" means the start time will be at the very beginning of the day (midnight).
	 * <p>
	 * Determines the first time slot, even when the scrollbars have been scrolled all the way back.
	 *
	 * @param slotMinTime
	 * @return
	 */
	public FullCalendarOptions setSlotMinTime(FullCalendarTimeSlot slotMinTime)
	{
		this.slotMinTime = slotMinTime;
		return this;
	}
	
	/**
	 * Determines the last time slot that will be displayed for each day. In line with the discussion about the Event object, it is important to stress that this should be specified as an exclusive end time.
	 * <p>
	 * Duration, default: "24:00:00"
	 * <p>
	 * The default "24:00:00" means the end time will be at the very end of the day (midnight).
	 * <p>
	 * Determines the last slot, even when the scrollbars have been scrolled all the way back.
	 *
	 * @return
	 */
	public FullCalendarTimeSlot getSlotMaxTime()
	{
		return slotMaxTime;
	}
	
	/**
	 * Determines the last time slot that will be displayed for each day. In line with the discussion about the Event object, it is important to stress that this should be specified as an exclusive end time.
	 * <p>
	 * Duration, default: "24:00:00"
	 * <p>
	 * The default "24:00:00" means the end time will be at the very end of the day (midnight).
	 * <p>
	 * Determines the last slot, even when the scrollbars have been scrolled all the way back.
	 *
	 * @param slotMaxTime
	 * @return
	 */
	public FullCalendarOptions setSlotMaxTime(FullCalendarTimeSlot slotMaxTime)
	{
		this.slotMaxTime = slotMaxTime;
		return this;
	}
	
	/**
	 * Determines how far forward the scroll pane is initially scrolled.
	 * <p>
	 * Duration, default: '06:00:00' (6am)
	 * <p>
	 * The user will be able to scroll back to see events before this time. If you want to prevent users from doing this, use the slotMinTime option instead.
	 * <p>
	 * By default, scrollTime is reapplied to the view whenever the date range changes. To disable this, set scrollTimeReset to false.
	 *
	 * @return
	 */
	public LocalDateTime getScrollTime()
	{
		return scrollTime;
	}
	
	/**
	 * Determines how far forward the scroll pane is initially scrolled.
	 * <p>
	 * Duration, default: '06:00:00' (6am)
	 * <p>
	 * The user will be able to scroll back to see events before this time. If you want to prevent users from doing this, use the slotMinTime option instead.
	 * <p>
	 * By default, scrollTime is reapplied to the view whenever the date range changes. To disable this, set scrollTimeReset to false.
	 *
	 * @param scrollTime
	 * @return
	 */
	public FullCalendarOptions setScrollTime(LocalDateTime scrollTime)
	{
		this.scrollTime = scrollTime;
		return this;
	}
	
	/**
	 * Whether the view should scroll to scrollTime every time the date range changes.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * By default, whenever the date range changes either via the API or the end-user clicking prev/next, the scroll is reset. Set scrollTimeReset to false to disable this behavior.
	 *
	 * @return
	 */
	public Boolean getScrollTimeReset()
	{
		return scrollTimeReset;
	}
	
	/**
	 * Whether the view should scroll to scrollTime every time the date range changes.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * By default, whenever the date range changes either via the API or the end-user clicking prev/next, the scroll is reset. Set scrollTimeReset to false to disable this behavior.
	 *
	 * @param scrollTimeReset
	 * @return
	 */
	public FullCalendarOptions setScrollTimeReset(Boolean scrollTimeReset)
	{
		this.scrollTimeReset = scrollTimeReset;
		return this;
	}
	
	private LocalDate initialDate;
	private FullCalendarVisibleRange validRange;
	
	/**
	 * he initial date displayed when the calendar first loads.
	 * <p>
	 * Date
	 * <p>
	 * When not specified, this value defaults to the current date.
	 * <p>
	 * This value can be anything that can parse into a Date, including an ISO8601 date string like "2014-02-01".
	 *
	 * @return
	 */
	public LocalDate getInitialDate()
	{
		return initialDate;
	}
	
	/**
	 * he initial date displayed when the calendar first loads.
	 * <p>
	 * Date
	 * <p>
	 * When not specified, this value defaults to the current date.
	 * <p>
	 * This value can be anything that can parse into a Date, including an ISO8601 date string like "2014-02-01".
	 *
	 * @param initialDate
	 * @return
	 */
	public FullCalendarOptions setInitialDate(LocalDate initialDate)
	{
		this.initialDate = initialDate;
		return this;
	}
	
	/**
	 * Limits which dates the user can navigate to and where events can go.
	 * <p>
	 * Object
	 * <p>
	 * Dates outside of the valid range will be grayed-out. The user will not be able to drag or resize events into these areas.
	 * <p>
	 * The prev/next buttons in the headerToolbar will be grayed out to prevent the user from navigating to an invalid range.
	 * <p>
	 * The validRange property can have start and end properties. You may specify one without specifying the other to create an open-ended range.
	 *
	 * @return
	 */
	public FullCalendarVisibleRange getValidRange()
	{
		return validRange;
	}
	
	/**
	 * Limits which dates the user can navigate to and where events can go.
	 * <p>
	 * Object
	 * <p>
	 * Dates outside of the valid range will be grayed-out. The user will not be able to drag or resize events into these areas.
	 * <p>
	 * The prev/next buttons in the headerToolbar will be grayed out to prevent the user from navigating to an invalid range.
	 * <p>
	 * The validRange property can have start and end properties. You may specify one without specifying the other to create an open-ended range.
	 *
	 * @param validRange
	 * @return
	 */
	public FullCalendarOptions setValidRange(FullCalendarVisibleRange validRange)
	{
		this.validRange = validRange;
		return this;
	}
	
	
	private Boolean weekNumbers;
	private String weekNumberCalculation;
	private String weekText;
	private String weekTextLong;
	private FullCalendarTitleFormat weekNumberFormat;
	
	/**
	 * Determines if week numbers should be displayed on the calendar.
	 * <p>
	 * Boolean, default: false
	 * <p>
	 * If set to true, week numbers will be displayed on the side of each row of days in Month/DayGrid views as well as at the top-left corner of the TimeGrid views. View a simple demo.
	 * <p>
	 * By default, FullCalendar will use the current locale’s week number calculation method. To display other types of week numbers, see weekNumberCalculation.
	 *
	 * @return
	 */
	public Boolean getWeekNumbers()
	{
		return weekNumbers;
	}
	
	/**
	 * Determines if week numbers should be displayed on the calendar.
	 * <p>
	 * Boolean, default: false
	 * <p>
	 * If set to true, week numbers will be displayed on the side of each row of days in Month/DayGrid views as well as at the top-left corner of the TimeGrid views. View a simple demo.
	 * <p>
	 * By default, FullCalendar will use the current locale’s week number calculation method. To display other types of week numbers, see weekNumberCalculation.
	 *
	 * @param weekNumbers
	 * @return
	 */
	public FullCalendarOptions setWeekNumbers(Boolean weekNumbers)
	{
		this.weekNumbers = weekNumbers;
		return this;
	}
	
	/**
	 * weekNumberCalculation
	 * The method for calculating week numbers that are displayed with the weekNumbers setting.
	 * <p>
	 * "local" (default), "ISO", or a function
	 * <p>
	 * Specifying "local" causes the locale-specific calculation to be used, as determined by the calendar’s locale setting. This is the default.
	 * <p>
	 * Specifiying "ISO" results in ISO8601 week numbers. Specifying "ISO" changes the default value of firstDay to 1 (Monday).
	 * <p>
	 * You may also specify a function, which must accept a single Date and return an integer week number.
	 *
	 * @return
	 */
	public String getWeekNumberCalculation()
	{
		return weekNumberCalculation;
	}
	
	/**
	 * weekNumberCalculation
	 * The method for calculating week numbers that are displayed with the weekNumbers setting.
	 * <p>
	 * "local" (default), "ISO", or a function
	 * <p>
	 * Specifying "local" causes the locale-specific calculation to be used, as determined by the calendar’s locale setting. This is the default.
	 * <p>
	 * Specifiying "ISO" results in ISO8601 week numbers. Specifying "ISO" changes the default value of firstDay to 1 (Monday).
	 * <p>
	 * You may also specify a function, which must accept a single Date and return an integer week number.
	 *
	 * @param weekNumberCalculation
	 * @return
	 */
	public FullCalendarOptions setWeekNumberCalculation(String weekNumberCalculation)
	{
		this.weekNumberCalculation = weekNumberCalculation;
		return this;
	}
	
	/**
	 * The heading text for week numbers. Also affects weeks in date formatting.
	 * <p>
	 * String, default: "W"
	 * <p>
	 * This text will go above the week number column in the DayGrid views. It will go alongside the week number text in the top-left cell for TimeGrid views.
	 * <p>
	 * The default value will change based on the current locale.
	 *
	 * @return
	 */
	public String getWeekText()
	{
		return weekText;
	}
	
	/**
	 * The heading text for week numbers. Also affects weeks in date formatting.
	 * <p>
	 * String, default: "W"
	 * <p>
	 * This text will go above the week number column in the DayGrid views. It will go alongside the week number text in the top-left cell for TimeGrid views.
	 * <p>
	 * The default value will change based on the current locale.
	 *
	 * @param weekText
	 * @return
	 */
	public FullCalendarOptions setWeekText(String weekText)
	{
		this.weekText = weekText;
		return this;
	}
	
	/**
	 * Like weekText but only used when the date-formatting week setting is set to 'long'.
	 * <p>
	 * String, default: "Week"
	 *
	 * @return
	 */
	public String getWeekTextLong()
	{
		return weekTextLong;
	}
	
	/**
	 * Like weekText but only used when the date-formatting week setting is set to 'long'.
	 * <p>
	 * String, default: "Week"
	 *
	 * @param weekTextLong
	 * @return
	 */
	public FullCalendarOptions setWeekTextLong(String weekTextLong)
	{
		this.weekTextLong = weekTextLong;
		return this;
	}
	
	/**
	 * Controls the week number text.
	 * <p>
	 * Date Formatter, default: { week: 'narrow' }
	 * <p>
	 * The formatted text depends on weekText.
	 * <p>
	 * If set to { week: 'short' }, and weekText is set to "W", a value like "W 6" will be outputted.
	 * <p>
	 * If set to { week: 'narrow' }, and weekText is set to "W", a value like "W6" will be outputted.
	 * <p>
	 * If set to { week: 'numeric' }, a plain numeric value like "6" will be outputted.
	 *
	 * @return
	 */
	public FullCalendarTitleFormat getWeekNumberFormat()
	{
		return weekNumberFormat;
	}
	
	/**
	 * Controls the week number text.
	 * <p>
	 * Date Formatter, default: { week: 'narrow' }
	 * <p>
	 * The formatted text depends on weekText.
	 * <p>
	 * If set to { week: 'short' }, and weekText is set to "W", a value like "W 6" will be outputted.
	 * <p>
	 * If set to { week: 'narrow' }, and weekText is set to "W", a value like "W6" will be outputted.
	 * <p>
	 * If set to { week: 'numeric' }, a plain numeric value like "6" will be outputted.
	 *
	 * @param weekNumberFormat
	 * @return
	 */
	public FullCalendarOptions setWeekNumberFormat(FullCalendarTitleFormat weekNumberFormat)
	{
		this.weekNumberFormat = weekNumberFormat;
		return this;
	}
	
	private Boolean nowIndicator;
	
	/**
	 * Whether or not to display a marker indicating the current time.
	 * <p>
	 * Boolean. default: false
	 * <p>
	 * The indicator will automatically reposition itself while the user is viewing the calendar.
	 * <p>
	 * When set to true in one of the TimeGrid views (view live demo):
	 *
	 * @return
	 */
	public Boolean getNowIndicator()
	{
		return nowIndicator;
	}
	
	/**
	 * Whether or not to display a marker indicating the current time.
	 * <p>
	 * Boolean. default: false
	 * <p>
	 * The indicator will automatically reposition itself while the user is viewing the calendar.
	 * <p>
	 * When set to true in one of the TimeGrid views (view live demo):
	 *
	 * @param nowIndicator
	 * @return
	 */
	public FullCalendarOptions setNowIndicator(Boolean nowIndicator)
	{
		this.nowIndicator = nowIndicator;
		return this;
	}
	
	private List<FullCalendarBusinessHours> businessHours;
	
	/**
	 * Emphasizes certain time slots on the calendar. By default, Monday-Friday, 9am-5pm.
	 * <p>
	 * boolean / object / array. default: false
	 * <p>
	 * If true, the default business hours will be emphasized (view live demo). If false (the default), there will be no emphasis.
	 * <p>
	 * An object may be given to render business hours with fine-grain control over the days/hours. The object may have any of the following properties:
	 *
	 * @return
	 */
	public List<FullCalendarBusinessHours> getBusinessHours()
	{
		return businessHours;
	}
	
	public FullCalendarOptions setBusinessHours(List<FullCalendarBusinessHours> businessHours)
	{
		this.businessHours = businessHours;
		return this;
	}
	
	
	private Boolean eventStartEditable;
	private Boolean eventResizableFromStart;
	private Boolean eventDurationEditable;
	private Boolean eventResourceEditable;
	private Boolean droppable;
	
	private Integer eventDragMinDistance;
	private Integer dragRevertDuration;
	
	private Boolean dragScroll;
	private FullCalendarTimeSlot snapDuration;
	private Boolean allDayMaintainDuration;
	private Boolean eventOverlap;
	
	private Integer longPressDelay;
	private Integer eventLongPressDelay;
	private Integer selectLongPressDelay;
	
	/**
	 * For touch devices, the amount of time the user must hold down before an event becomes draggable or a date becomes selectable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 * <p>
	 * This setting controls event dragging and date selecting. For further granularity, please see the following settings:
	 *
	 * @return
	 */
	public Integer getLongPressDelay()
	{
		return longPressDelay;
	}
	
	/**
	 * For touch devices, the amount of time the user must hold down before an event becomes draggable or a date becomes selectable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 * <p>
	 * This setting controls event dragging and date selecting. For further granularity, please see the following settings:
	 *
	 * @param longPressDelay
	 * @return
	 */
	public FullCalendarOptions setLongPressDelay(Integer longPressDelay)
	{
		this.longPressDelay = longPressDelay;
		return this;
	}
	
	/**
	 * For touch devices, the amount of time the user must hold down before an event becomes draggable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds. If not specified, it falls back to longPressDelay.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 *
	 * @return
	 */
	public Integer getEventLongPressDelay()
	{
		return eventLongPressDelay;
	}
	
	/**
	 * For touch devices, the amount of time the user must hold down before an event becomes draggable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds. If not specified, it falls back to longPressDelay.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 *
	 * @param eventLongPressDelay
	 * @return
	 */
	public FullCalendarOptions setEventLongPressDelay(Integer eventLongPressDelay)
	{
		this.eventLongPressDelay = eventLongPressDelay;
		return this;
	}
	
	/**
	 * For touch devices, the amount of time the user must hold down before a date becomes selectable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds. If not specified, it falls back to longPressDelay.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 *
	 * @return
	 */
	public Integer getSelectLongPressDelay()
	{
		return selectLongPressDelay;
	}
	
	/**
	 * For touch devices, the amount of time the user must hold down before a date becomes selectable.
	 * <p>
	 * Integer, default: 1000 (1 second)
	 * <p>
	 * This value is specified in milliseconds. If not specified, it falls back to longPressDelay.
	 * <p>
	 * Only applicable when the calendar is being used on a touch device. Otherwise, there is no delay.
	 *
	 * @param selectLongPressDelay
	 * @return
	 */
	public FullCalendarOptions setSelectLongPressDelay(Integer selectLongPressDelay)
	{
		this.selectLongPressDelay = selectLongPressDelay;
		return this;
	}
	
	/**
	 * Allow events’ start times to be editable through dragging.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article »
	 * <p>
	 * This option can be overridden on a per-source basis with the startEditable Event Source Object option or on a per-event basis with the startEditable Event Object option.
	 *
	 * @return
	 */
	public Boolean getEventStartEditable()
	{
		return eventStartEditable;
	}
	
	/**
	 * Allow events’ start times to be editable through dragging.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article »
	 * <p>
	 * This option can be overridden on a per-source basis with the startEditable Event Source Object option or on a per-event basis with the startEditable Event Object option.
	 *
	 * @param eventStartEditable
	 * @return
	 */
	public FullCalendarOptions setEventStartEditable(Boolean eventStartEditable)
	{
		this.eventStartEditable = eventStartEditable;
		return this;
	}
	
	/**
	 * Whether the user can resize an event from its starting edge.
	 * <p>
	 * true or false (default)
	 * <p>
	 * By default, the user cannot.
	 *
	 * @return
	 */
	public Boolean getEventResizableFromStart()
	{
		return eventResizableFromStart;
	}
	
	/**
	 * Whether the user can resize an event from its starting edge.
	 * <p>
	 * true or false (default)
	 * <p>
	 * By default, the user cannot.
	 *
	 * @param eventResizableFromStart
	 * @return
	 */
	public FullCalendarOptions setEventResizableFromStart(Boolean eventResizableFromStart)
	{
		this.eventResizableFromStart = eventResizableFromStart;
		return this;
	}
	
	/**
	 * Allow events’ durations to be editable through resizing.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article
	 * <p>
	 * This option can be overridden on a per-source basis with the durationEditable Event Source Object option or on a per-event basis with the durationEditable Event Object option.
	 *
	 * @return
	 */
	public Boolean getEventDurationEditable()
	{
		return eventDurationEditable;
	}
	
	/**
	 * Allow events’ durations to be editable through resizing.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article
	 * <p>
	 * This option can be overridden on a per-source basis with the durationEditable Event Source Object option or on a per-event basis with the durationEditable Event Object option.
	 *
	 * @param eventDurationEditable
	 * @return
	 */
	public FullCalendarOptions setEventDurationEditable(Boolean eventDurationEditable)
	{
		this.eventDurationEditable = eventDurationEditable;
		return this;
	}
	
	/**
	 * Determines whether the user can drag events between resources.
	 * <p>
	 * Boolean
	 * <p>
	 * The default value is inherited from the master editable flag, which is false by default.
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article
	 * <p>
	 * A variation of this property called resourceEditable can be set on an Event Object for more granular control:
	 *
	 * @return
	 */
	public Boolean getEventResourceEditable()
	{
		return eventResourceEditable;
	}
	
	/**
	 * Determines whether the user can drag events between resources.
	 * <p>
	 * Boolean
	 * <p>
	 * The default value is inherited from the master editable flag, which is false by default.
	 * <p>
	 * You must enable the interaction plugin before using this setting. See code samples on the editable article
	 * <p>
	 * A variation of this property called resourceEditable can be set on an Event Object for more granular control:
	 *
	 * @param eventResourceEditable
	 * @return
	 */
	public FullCalendarOptions setEventResourceEditable(Boolean eventResourceEditable)
	{
		this.eventResourceEditable = eventResourceEditable;
		return this;
	}
	
	/**
	 * Determines if external draggable elements or events from other calendars can be dropped onto the calendar.
	 * <p>
	 * Boolean, default: false
	 * <p>
	 * It’s important to enable this setting if you want to use the drop callback or the eventReceive callback.
	 *
	 * @return
	 */
	public Boolean getDroppable()
	{
		return droppable;
	}
	
	/**
	 * Determines if external draggable elements or events from other calendars can be dropped onto the calendar.
	 * <p>
	 * Boolean, default: false
	 * <p>
	 * It’s important to enable this setting if you want to use the drop callback or the eventReceive callback.
	 *
	 * @param droppable
	 * @return
	 */
	public FullCalendarOptions setDroppable(Boolean droppable)
	{
		this.droppable = droppable;
		return this;
	}
	
	/**
	 * How many pixels the user’s mouse/touch must move before an event drag activates.
	 * <p>
	 * Integer, default: 5
	 *
	 * @return
	 */
	public Integer getEventDragMinDistance()
	{
		return eventDragMinDistance;
	}
	
	/**
	 * How many pixels the user’s mouse/touch must move before an event drag activates.
	 * <p>
	 * Integer, default: 5
	 *
	 * @param eventDragMinDistance
	 * @return
	 */
	public FullCalendarOptions setEventDragMinDistance(Integer eventDragMinDistance)
	{
		this.eventDragMinDistance = eventDragMinDistance;
		return this;
	}
	
	/**
	 * Time it takes for an event to revert to its original position after an unsuccessful drag.
	 * <p>
	 * Integer, default: 500
	 * <p>
	 * Time is in milliseconds (1 second = 1000 milliseconds).
	 *
	 * @return
	 */
	public Integer getDragRevertDuration()
	{
		return dragRevertDuration;
	}
	
	/**
	 * Time it takes for an event to revert to its original position after an unsuccessful drag.
	 * <p>
	 * Integer, default: 500
	 * <p>
	 * Time is in milliseconds (1 second = 1000 milliseconds).
	 *
	 * @param dragRevertDuration
	 * @return
	 */
	public FullCalendarOptions setDragRevertDuration(Integer dragRevertDuration)
	{
		this.dragRevertDuration = dragRevertDuration;
		return this;
	}
	
	/**
	 * Whether to automatically scoll the scroll-containers during event drag-and-drop and date selecting.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * If enabled, the scroll container will automatically scroll once the mouse gets close to the edge.
	 *
	 * @return
	 */
	public Boolean getDragScroll()
	{
		return dragScroll;
	}
	
	/**
	 * Whether to automatically scoll the scroll-containers during event drag-and-drop and date selecting.
	 * <p>
	 * Boolean, default: true
	 * <p>
	 * If enabled, the scroll container will automatically scroll once the mouse gets close to the edge.
	 *
	 * @param dragScroll
	 * @return
	 */
	public FullCalendarOptions setDragScroll(Boolean dragScroll)
	{
		this.dragScroll = dragScroll;
		return this;
	}
	
	/**
	 * The time interval at which a dragged event will snap to the time axis. Also affects the granularity at which selections can be made.
	 * <p>
	 * Duration
	 * <p>
	 * The default value will be whatever slotDuration is, which defaults to half an hour.
	 *
	 * @return
	 */
	public FullCalendarTimeSlot getSnapDuration()
	{
		return snapDuration;
	}
	
	/**
	 * The time interval at which a dragged event will snap to the time axis. Also affects the granularity at which selections can be made.
	 * <p>
	 * Duration
	 * <p>
	 * The default value will be whatever slotDuration is, which defaults to half an hour.
	 *
	 * @param snapDuration
	 * @return
	 */
	public FullCalendarOptions setSnapDuration(FullCalendarTimeSlot snapDuration)
	{
		this.snapDuration = snapDuration;
		return this;
	}
	
	/**
	 * Determines how an event’s duration should be mutated when it is dragged from a timed section to an all-day section and vice versa.
	 * <p>
	 * true or false (the default)
	 * <p>
	 * When true, the duration will remain roughly the same before and after it is dragged to/from an all-day section. “Roughly” because if an event has a duration with hourly precision, it will be rounded down to the nearest whole-day.
	 * <p>
	 * When false, the default, the event’s duration will be reset to defaultAllDayEventDuration (probably one day) if it is being dropped in an all-day section or defaultTimedEventDuration (probably one hour) if it is being dropped in a timed section.
	 *
	 * @return
	 */
	public Boolean getAllDayMaintainDuration()
	{
		return allDayMaintainDuration;
	}
	
	/**
	 * Determines how an event’s duration should be mutated when it is dragged from a timed section to an all-day section and vice versa.
	 * <p>
	 * true or false (the default)
	 * <p>
	 * When true, the duration will remain roughly the same before and after it is dragged to/from an all-day section. “Roughly” because if an event has a duration with hourly precision, it will be rounded down to the nearest whole-day.
	 * <p>
	 * When false, the default, the event’s duration will be reset to defaultAllDayEventDuration (probably one day) if it is being dropped in an all-day section or defaultTimedEventDuration (probably one hour) if it is being dropped in a timed section.
	 *
	 * @param allDayMaintainDuration
	 * @return
	 */
	public FullCalendarOptions setAllDayMaintainDuration(Boolean allDayMaintainDuration)
	{
		this.allDayMaintainDuration = allDayMaintainDuration;
		return this;
	}
	
	/**
	 * Determines if events being dragged and resized are allowed to overlap each other.
	 * <p>
	 * boolean / function. default: true
	 * <p>
	 * If false, no events are allowed to overlap. If true, all events are allowed to overlap (the default).
	 *
	 * @return
	 */
	public Boolean getEventOverlap()
	{
		return eventOverlap;
	}
	
	/**
	 * Determines if events being dragged and resized are allowed to overlap each other.
	 * <p>
	 * boolean / function. default: true
	 * <p>
	 * If false, no events are allowed to overlap. If true, all events are allowed to overlap (the default).
	 *
	 * @param eventOverlap
	 * @return
	 */
	public FullCalendarOptions setEventOverlap(Boolean eventOverlap)
	{
		this.eventOverlap = eventOverlap;
		return this;
	}
	
	private String schedulerLicenseKey;
	
	public String getSchedulerLicenseKey()
	{
		return schedulerLicenseKey;
	}
	
	public FullCalendarOptions setSchedulerLicenseKey(String schedulerLicenseKey)
	{
		this.schedulerLicenseKey = schedulerLicenseKey;
		return this;
	}
	
	private Integer eventMaxStack;
	
	public Integer getEventMaxStack()
	{
		return eventMaxStack;
	}
	
	public FullCalendarOptions setEventMaxStack(Integer eventMaxStack)
	{
		this.eventMaxStack = eventMaxStack;
		return this;
	}
	
	
	private String resourceOrder;
	
	public String getResourceOrder()
	{
		return resourceOrder;
	}
	
	public FullCalendarOptions setResourceOrder(String resourceOrder)
	{
		this.resourceOrder = resourceOrder;
		return this;
	}
	
	private String resourceAreaHeaderContent;
	
	public String getResourceAreaHeaderContent()
	{
		return resourceAreaHeaderContent;
	}
	
	public FullCalendarOptions setResourceAreaHeaderContent(String resourceAreaHeaderContent)
	{
		this.resourceAreaHeaderContent = resourceAreaHeaderContent;
		return this;
	}
	
	private Boolean selectable;
	
	public Boolean getSelectable()
	{
		return selectable;
	}
	
	public FullCalendarOptions setSelectable(Boolean selectable)
	{
		this.selectable = selectable;
		return this;
	}
	
	@JsonRawValue
	private String select;
	@JsonRawValue
	private String eventClick;
	@JsonRawValue
	private String dateClick;
	@JsonRawValue
	private String drop;
	@JsonRawValue
	private String eventReceive;
	@JsonRawValue
	private String eventResize;
	
	public String getSelect()
	{
		return select;
	}
	
	public FullCalendarOptions setSelect(String select)
	{
		this.select = select;
		return this;
	}
	
	public String getEventClick()
	{
		return eventClick;
	}
	
	public FullCalendarOptions setEventClick(String eventClick)
	{
		this.eventClick = eventClick;
		return this;
	}
	
	public String getDateClick()
	{
		return dateClick;
	}
	
	public FullCalendarOptions setDateClick(String dateClick)
	{
		this.dateClick = dateClick;
		return this;
	}
	
	public String getDrop()
	{
		return drop;
	}
	
	public FullCalendarOptions setDrop(String drop)
	{
		this.drop = drop;
		return this;
	}
	
	public String getEventReceive()
	{
		return eventReceive;
	}
	
	public FullCalendarOptions setEventReceive(String eventReceive)
	{
		this.eventReceive = eventReceive;
		return this;
	}
	
	public String getEventResize()
	{
		return eventResize;
	}
	
	public FullCalendarOptions setEventResize(String eventResize)
	{
		this.eventResize = eventResize;
		return this;
	}
}
