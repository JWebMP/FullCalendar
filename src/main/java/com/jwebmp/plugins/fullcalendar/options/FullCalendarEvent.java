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
import com.jwebmp.core.base.angular.client.annotations.angular.*;
import com.jwebmp.core.base.angular.client.services.interfaces.*;
import com.jwebmp.core.htmlbuilder.css.colours.ColourCSSImpl;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.fullcalendar.options.views.*;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GedMarc
 * @since 05 Feb 2017
 */

@SuppressWarnings("JavaDoc")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
public class FullCalendarEvent
		extends JavaScriptPart<FullCalendarEvent>
		implements IFullCalendarEvent, INgDataType<FullCalendarEvent>
{
	/**
	 * String/Integer. Optional
	 * <p>
	 * Uniquely identifies the given event. Different instances of repeating events should all have the same id.
	 */
	private String id;

	/**
	 * String. Required.
	 * <p>
	 * The text on an event's element
	 */
	private String title;

	/**
	 * true or false. Optional.
	 * <p>
	 * Whether an event occurs at a specific time-of-day. This property affects whether an event's time is shown. Also, in the agenda views, determines if it is displayed in the
	 * "all-day" section.
	 * If this value is not explicitly specified, allDayDefault will be used if it is defined.
	 * <p>
	 * If all else fails, FullCalendar will try to guess. If either the start or end value has a "T" as part of the ISO8601 date string, allDay will become false. Otherwise, it
	 * will be true.
	 * <p>
	 * Don't include quotes around your true/false. This value is a boolean, not a string!
	 */
	private Boolean allDay;

	/**
	 * The date/time an event begins. Required.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 */
	private LocalDateTime start;

	/**
	 * The exclusive date/time an event ends. Optional.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 * <p>
	 * It is the moment immediately after the event has ended. For example, if the last full day of an event is Thursday, the exclusive end of the event will be 00:00:00 on Friday!
	 */
	private LocalDateTime end;

	/**
	 * String/Array. Optional.
	 * <p>
	 * A CSS class (or array of classes) that will be attached to this event's element.
	 */
	private List<String> className;

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master editable option for this single event.
	 */
	private Boolean editable;
	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventStartEditable option for this single event.
	 */
	private Boolean startEditable;

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventDurationEditable option for this single event.
	 */
	private Boolean durationEditable;

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventResourceEditable option for this single event.
	 */
	private Boolean resourceEditable;

	/**
	 * Allows alternate rendering of the event, like background events.
	 * <p>
	 * Can be empty, "background", or "inverse-background"
	 */
	private FullCalendarEventRenderingOptions rendering;

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventOverlap option for this single event.
	 * <p>
	 * If false, prevents this event from being dragged/resized over other events. Also prevents other events from being dragged/resized over this event.
	 */
	private Boolean overlap;

	/**
	 * an event ID, "businessHours", object. Optional.
	 * <p>
	 * Overrides the master eventConstraint option for this single event.
	 */
	private Serializable constraint;

	/**
	 * Event Source Object. Automatically populated.
	 * <p>
	 * A reference to the event source that this event came from.
	 */
	private Serializable source;

	/**
	 * Sets an event's background and border color just like the calendar-wide eventColor option.
	 */
	private ColourCSSImpl color;

	/**
	 * Sets an event's background color just like the calendar-wide eventBackgroundColor option.
	 */
	private ColourCSSImpl backgroundColor;

	/**
	 * Sets an event's border color just like the the calendar-wide eventBorderColor option.
	 */
	private ColourCSSImpl borderColor;

	/**
	 * Sets an event's text color just like the calendar-wide eventTextColor option.
	 */
	private ColourCSSImpl textColor;

	private Boolean defaultAllDay;
	private FullCalendarViewDuration defaultAllDayEventDuration;
	private LocalTime defaultTimedEventDuration;
	private Boolean forceEventDuration;
	private List<String> resourceId;
	
	private String groupId;
	private String display;
	
	
	/**
	 * Determines the default value for each Event Object’s allDay property when it is unspecified.
	 *
	 * Boolean, default: undefined
	 *
	 * By default, defaultAllDay is not set. As a result, any Event Objects that do not specify an allDay property will be subject the guessing behavior mentioned in the Event Object article.
	 * @return
	 */
	public Boolean getDefaultAllDay()
	{
		return defaultAllDay;
	}
	
	/**
	 * Determines the default value for each Event Object’s allDay property when it is unspecified.
	 *
	 * Boolean, default: undefined
	 *
	 * By default, defaultAllDay is not set. As a result, any Event Objects that do not specify an allDay property will be subject the guessing behavior mentioned in the Event Object article.
	 * @param defaultAllDay
	 * @return
	 */
	public FullCalendarEvent setDefaultAllDay(Boolean defaultAllDay)
	{
		this.defaultAllDay = defaultAllDay;
		return this;
	}
	
	/**
	 * A fallback duration for all-day Event Objects without a specified end value.
	 *
	 * Duration, default: { days: 1 }
	 *
	 * If an event does not have an end specified, it will appear to be this duration when rendered.
	 *
	 * The actual end of the event will remain unset unless forceEventDuration has been set to true.
	 * @return
	 */
	public FullCalendarViewDuration getDefaultAllDayEventDuration()
	{
		if (defaultAllDayEventDuration == null)
		{
			defaultAllDayEventDuration = new FullCalendarViewDuration();
		}
		return defaultAllDayEventDuration;
	}
	
	/**
	 * A fallback duration for all-day Event Objects without a specified end value.
	 *
	 * Duration, default: { days: 1 }
	 *
	 * If an event does not have an end specified, it will appear to be this duration when rendered.
	 *
	 * The actual end of the event will remain unset unless forceEventDuration has been set to true.
	 * @param defaultAllDayEventDuration
	 * @return
	 */
	public FullCalendarEvent setDefaultAllDayEventDuration(FullCalendarViewDuration defaultAllDayEventDuration)
	{
		this.defaultAllDayEventDuration = defaultAllDayEventDuration;
		return this;
	}
	
	/**
	 * A fallback duration for timed Event Objects without a specified end value.
	 *
	 * Duration, default: '01:00' (1 hour)
	 *
	 * If an event does not have an end specified, it will appear to be this duration when rendered.
	 *
	 * The actual end of the event will remain unset unless forceEventDuration has been set to true.
	 *
	 * This setting only affects events with allDay equal to false. For all-day events, use defaultAllDayEventDuration.
	 * @return
	 */
	public LocalTime getDefaultTimedEventDuration()
	{
		return defaultTimedEventDuration;
	}
	
	/**
	 * A fallback duration for timed Event Objects without a specified end value.
	 *
	 * Duration, default: '01:00' (1 hour)
	 *
	 * If an event does not have an end specified, it will appear to be this duration when rendered.
	 *
	 * The actual end of the event will remain unset unless forceEventDuration has been set to true.
	 *
	 * This setting only affects events with allDay equal to false. For all-day events, use defaultAllDayEventDuration.
	 * @param defaultTimedEventDuration
	 * @return
	 */
	public FullCalendarEvent setDefaultTimedEventDuration(LocalTime defaultTimedEventDuration)
	{
		this.defaultTimedEventDuration = defaultTimedEventDuration;
		return this;
	}
	
	/**
	 * A flag to force assignment of an event’s end if it is unspecified.
	 *
	 * Boolean, default: false
	 *
	 * If an event’s end is not specified, it will be calculated and assigned to the Event Object using defaultTimedEventDuration or defaultAllDayEventDuration.
	 * @return
	 */
	public Boolean getForceEventDuration()
	{
		return forceEventDuration;
	}
	
	/**
	 * A flag to force assignment of an event’s end if it is unspecified.
	 *
	 * Boolean, default: false
	 *
	 * If an event’s end is not specified, it will be calculated and assigned to the Event Object using defaultTimedEventDuration or defaultAllDayEventDuration.
	 * @param forceEventDuration
	 * @return
	 */
	public FullCalendarEvent setForceEventDuration(Boolean forceEventDuration)
	{
		this.forceEventDuration = forceEventDuration;
		return this;
	}
	
	/**
	 * String/Integer. Optional
	 * <p>
	 * Uniquely identifies the given event. Different instances of repeating events should all have the same id.
	 *
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * String/Integer. Optional
	 * <p>
	 * Uniquely identifies the given event. Different instances of repeating events should all have the same id.
	 *
	 * @param id
	 *
	 * @return
	 */
	public FullCalendarEvent setId(String id)
	{
		this.id = id;
		return this;
	}

	/**
	 * String. Required.
	 * <p>
	 * The text on an event's element
	 *
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * String. Required.
	 * <p>
	 * The text on an event's element
	 *
	 * @param title
	 *
	 * @return
	 */
	public FullCalendarEvent setTitle(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Whether an event occurs at a specific time-of-day. This property affects whether an event's time is shown. Also, in the agenda views, determines if it is displayed in the
	 * "all-day" section.
	 * If this value is not explicitly specified, allDayDefault will be used if it is defined.
	 * <p>
	 * If all else fails, FullCalendar will try to guess. If either the start or end value has a "T" as part of the ISO8601 date string, allDay will become false. Otherwise, it
	 * will be true.
	 * <p>
	 * Don't include quotes around your true/false. This value is a boolean, not a string!
	 *
	 * @return
	 */
	public Boolean getAllDay()
	{
		return allDay;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Whether an event occurs at a specific time-of-day. This property affects whether an event's time is shown. Also, in the agenda views, determines if it is displayed in the
	 * "all-day" section.
	 * If this value is not explicitly specified, allDayDefault will be used if it is defined.
	 * <p>
	 * If all else fails, FullCalendar will try to guess. If either the start or end value has a "T" as part of the ISO8601 date string, allDay will become false. Otherwise, it
	 * will be true.
	 * <p>
	 * Don't include quotes around your true/false. This value is a boolean, not a string!
	 *
	 * @param allDay
	 *
	 * @return
	 */
	public FullCalendarEvent setAllDay(Boolean allDay)
	{
		this.allDay = allDay;
		return this;
	}

	/**
	 * The date/time an event begins. Required.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 *
	 * @return
	 */
	public LocalDateTime getStart()
	{
		return start;
	}

	/**
	 * The date/time an event begins. Required.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 *
	 * @param start
	 *
	 * @return
	 */
	public FullCalendarEvent setStart(LocalDateTime start)
	{
		this.start = start;
		return this;
	}

	/**
	 * The exclusive date/time an event ends. Optional.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 * <p>
	 * It is the moment immediately after the event has ended. For example, if the last full day of an event is Thursday, the exclusive end of the event will be 00:00:00 on Friday!
	 *
	 * @return
	 */
	public LocalDateTime getEnd()
	{
		return end;
	}

	/**
	 * The exclusive date/time an event ends. Optional.
	 * <p>
	 * A Moment-ish input, like an ISO8601 string. Throughout the API this will become a real Moment object.
	 * <p>
	 * It is the moment immediately after the event has ended. For example, if the last full day of an event is Thursday, the exclusive end of the event will be 00:00:00 on Friday!
	 *
	 * @param end
	 *
	 * @return
	 */
	public FullCalendarEvent setEnd(LocalDateTime end)
	{
		this.end = end;
		return this;
	}

	/**
	 * String/Array. Optional.
	 * <p>
	 * A CSS class (or array of classes) that will be attached to this event's element.
	 *
	 * @return
	 */
	public List<String> getClassName()
	{
		if (className == null)
		{
			setClassName(new ArrayList<>());
		}
		return className;
	}

	/**
	 * String/Array. Optional.
	 * <p>
	 * A CSS class (or array of classes) that will be attached to this event's element.
	 *
	 * @param className
	 *
	 * @return
	 */
	public FullCalendarEvent setClassName(List<String> className)
	{
		this.className = className;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master editable option for this single event.
	 *
	 * @return
	 */
	public Boolean getEditable()
	{
		return editable;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master editable option for this single event.
	 *
	 * @param editable
	 *
	 * @return
	 */
	public FullCalendarEvent setEditable(Boolean editable)
	{
		this.editable = editable;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventStartEditable option for this single event.
	 *
	 * @return
	 */
	public Boolean getStartEditable()
	{
		return startEditable;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventStartEditable option for this single event.
	 *
	 * @param startEditable
	 *
	 * @return
	 */
	public FullCalendarEvent setStartEditable(Boolean startEditable)
	{
		this.startEditable = startEditable;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventDurationEditable option for this single event.
	 *
	 * @return
	 */
	public Boolean getDurationEditable()
	{
		return durationEditable;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventDurationEditable option for this single event.
	 *
	 * @param durationEditable
	 *
	 * @return
	 */
	public FullCalendarEvent setDurationEditable(Boolean durationEditable)
	{
		this.durationEditable = durationEditable;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventResourceEditable option for this single event.
	 *
	 * @return
	 */
	public Boolean getResourceEditable()
	{
		return resourceEditable;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventResourceEditable option for this single event.
	 *
	 * @param resourceEditable
	 *
	 * @return
	 */
	public FullCalendarEvent setResourceEditable(Boolean resourceEditable)
	{
		this.resourceEditable = resourceEditable;
		return this;
	}

	/**
	 * Allows alternate rendering of the event, like background events.
	 * <p>
	 * Can be empty, "background", or "inverse-background"
	 *
	 * @return
	 */
	public FullCalendarEventRenderingOptions getRendering()
	{
		return rendering;
	}

	/**
	 * Allows alternate rendering of the event, like background events.
	 * <p>
	 * Can be empty, "background", or "inverse-background"
	 *
	 * @param rendering
	 *
	 * @return
	 */
	public FullCalendarEvent setRendering(FullCalendarEventRenderingOptions rendering)
	{
		this.rendering = rendering;
		return this;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventOverlap option for this single event.
	 * <p>
	 * If false, prevents this event from being dragged/resized over other events. Also prevents other events from being dragged/resized over this event.
	 *
	 * @return
	 */
	public Boolean getOverlap()
	{
		return overlap;
	}

	/**
	 * true or false. Optional.
	 * <p>
	 * Overrides the master eventOverlap option for this single event.
	 * <p>
	 * If false, prevents this event from being dragged/resized over other events. Also prevents other events from being dragged/resized over this event.
	 *
	 * @param overlap
	 *
	 * @return
	 */
	public FullCalendarEvent setOverlap(Boolean overlap)
	{
		this.overlap = overlap;
		return this;
	}

	/**
	 * an event ID, "businessHours", object. Optional.
	 * <p>
	 * Overrides the master eventConstraint option for this single event.
	 *
	 * @return
	 */
	public Serializable getConstraint()
	{
		return constraint;
	}

	/**
	 * an event ID, "businessHours", object. Optional.
	 * <p>
	 * Overrides the master eventConstraint option for this single event.
	 *
	 * @param constraint
	 *
	 * @return
	 */
	public FullCalendarEvent setConstraint(Serializable constraint)
	{
		this.constraint = constraint;
		return this;
	}

	/**
	 * Event Source Object. Automatically populated.
	 * <p>
	 * A reference to the event source that this event came from.
	 *
	 * @return
	 */
	public Serializable getSource()
	{
		return source;
	}

	/**
	 * Event Source Object. Automatically populated.
	 * <p>
	 * A reference to the event source that this event came from.
	 *
	 * @param source
	 *
	 * @return
	 */
	public FullCalendarEvent setSource(Serializable source)
	{
		this.source = source;
		return this;
	}

	/**
	 * Sets an event's background and border color just like the calendar-wide eventColor option.
	 *
	 * @return
	 */
	public ColourCSSImpl getColor()
	{
		return color;
	}

	/**
	 * Sets an event's background and border color just like the calendar-wide eventColor option.
	 *
	 * @param color
	 *
	 * @return
	 */
	public FullCalendarEvent setColor(ColourCSSImpl color)
	{
		this.color = color;
		return this;
	}

	/**
	 * Sets an event's background color just like the calendar-wide eventBackgroundColor option.
	 *
	 * @return
	 */
	public ColourCSSImpl getBackgroundColor()
	{
		return backgroundColor;
	}

	/**
	 * Sets an event's background color just like the calendar-wide eventBackgroundColor option.
	 *
	 * @param backgroundColor
	 *
	 * @return
	 */
	public FullCalendarEvent setBackgroundColor(ColourCSSImpl backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		return this;
	}

	/**
	 * Sets an event's border color just like the the calendar-wide eventBorderColor option.
	 *
	 * @return
	 */
	public ColourCSSImpl getBorderColor()
	{
		return borderColor;
	}

	/**
	 * Sets an event's border color just like the the calendar-wide eventBorderColor option.
	 *
	 * @param borderColor
	 *
	 * @return
	 */
	public FullCalendarEvent setBorderColor(ColourCSSImpl borderColor)
	{
		this.borderColor = borderColor;
		return this;
	}

	/**
	 * Sets an event's text color just like the calendar-wide eventTextColor option.
	 *
	 * @return
	 */
	public ColourCSSImpl getTextColor()
	{
		return textColor;
	}

	/**
	 * Sets an event's text color just like the calendar-wide eventTextColor option.
	 *
	 * @param textColor
	 *
	 * @return
	 */
	public FullCalendarEvent setTextColor(ColourCSSImpl textColor)
	{
		this.textColor = textColor;
		return this;
	}
	
	/**
	 * Associating Events with Resources
	 * An Event becomes associated with a resource when its resourceId property matches one of the Resource Object’s id fields:
	 * @return
	 */
	public List<String> getResourceId()
	{
		if (resourceId == null)
		{
			resourceId = new ArrayList<>();
		}
		return resourceId;
	}
	
	/**
	 * Associating Events with Resources
	 * An Event becomes associated with a resource when its resourceId property matches one of the Resource Object’s id fields:
	 * @param resourceId
	 * @return
	 */
	public FullCalendarEvent setResourceId(List<String> resourceId)
	{
		this.resourceId = resourceId;
		return this;
	}
	
	/**
	 * String. Events that share a groupId will be dragged and resized together automatically.
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}
	
	/**
	 * String. Events that share a groupId will be dragged and resized together automatically.
	 * @param groupId
	 * @return
	 */
	public FullCalendarEvent setGroupId(String groupId)
	{
		this.groupId = groupId;
		return this;
	}
	
	/**
	 *
	 * The rendering type of this event. Can be 'auto', 'block', 'list-item', 'background', 'inverse-background', or 'none'. See eventDisplay.
	 * @return
	 */
	public String getDisplay()
	{
		return display;
	}
	
	/**
	 *
	 * The rendering type of this event. Can be 'auto', 'block', 'list-item', 'background', 'inverse-background', or 'none'. See eventDisplay.
	 * @param display
	 * @return
	 */
	public FullCalendarEvent setDisplay(String display)
	{
		this.display = display;
		return this;
	}
}
