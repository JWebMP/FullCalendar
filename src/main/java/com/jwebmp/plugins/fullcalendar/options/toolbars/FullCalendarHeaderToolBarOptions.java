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
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.fullcalendar.options.enumerations.FullCalendarHeaderParts;

import java.util.Arrays;
import java.util.List;

import static com.guicedee.guicedinjection.json.StaticStrings.STRING_COMMNA;

/**
 * header
 * <p>
 * Defines the buttons and title at the top of the calendar.
 *
 * @author GedMarc
 * @since 04 Feb 2017
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)

public class FullCalendarHeaderToolBarOptions
		extends JavaScriptPart<FullCalendarHeaderToolBarOptions>
{
	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 */
	@JsonIgnoreProperties(allowSetters = true)
	private List<IFullCalendarHeaderToolbarButton<?>> start;
	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 */
	@JsonIgnoreProperties(allowSetters = true)
	private List<IFullCalendarHeaderToolbarButton<?>> end;
	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 */
	@JsonIgnoreProperties(allowSetters = true)
	private List<IFullCalendarHeaderToolbarButton<?>> center;

	/**
	 * A new instance of the header options
	 */
	public FullCalendarHeaderToolBarOptions()
	{
		//No config
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param start
	 * @param end
	 * @param center
	 */
	public FullCalendarHeaderToolBarOptions(List<IFullCalendarHeaderToolbarButton<?>> start, List<IFullCalendarHeaderToolbarButton<?>> end, List<IFullCalendarHeaderToolbarButton<?>> center)
	{
		this.start = start;
		this.end = end;
		this.center = center;
	}

	@JsonProperty("start")
	protected String getLeftJson()
	{
		StringBuilder sb = new StringBuilder();
		if (getStart() != null)
		{
			for (IFullCalendarHeaderToolbarButton<?> leftPart : getStart())
			{

				sb.append(leftPart.toString());
				if (leftPart != FullCalendarHeaderParts.space)
				{
					sb.append(STRING_COMMNA);
				}
			}
		}
		if (sb.indexOf(STRING_COMMNA) > 0)
		{
			sb.deleteCharAt(sb.lastIndexOf(STRING_COMMNA));
		}
		return sb.toString();
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @return
	 */
	public List<IFullCalendarHeaderToolbarButton<?>> getStart()
	{
		return start;
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param start
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setStart(IFullCalendarHeaderToolbarButton<?>... start)
	{
		List<IFullCalendarHeaderToolbarButton<?>> all = Arrays.asList(start);
		return setLeft(all);
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param left
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setLeft(List<IFullCalendarHeaderToolbarButton<?>> left)
	{
		this.start = left;
		return this;
	}

	@JsonProperty("end")
	protected String getRightJson()
	{
		StringBuilder sb = new StringBuilder();
		if (getEnd() != null)
		{
			for (IFullCalendarHeaderToolbarButton<?> leftPart : getEnd())
			{

				sb.append(leftPart.toString());
				if (leftPart != FullCalendarHeaderParts.space)
				{
					sb.append(STRING_COMMNA);
				}
			}
		}
		if (sb.indexOf(STRING_COMMNA) > 0)
		{
			sb.deleteCharAt(sb.lastIndexOf(STRING_COMMNA));
		}
		return sb.toString();
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @return
	 */
	public List<IFullCalendarHeaderToolbarButton<?>> getEnd()
	{
		return end;
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param left
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setEnd(IFullCalendarHeaderToolbarButton<?>... left)
	{
		List<IFullCalendarHeaderToolbarButton<?>> all = Arrays.asList(left);
		return setRight(all);
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param right
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setRight(List<IFullCalendarHeaderToolbarButton<?>> right)
	{
		this.end = right;
		return this;
	}

	@JsonProperty("center")
	protected String getCenterJson()
	{
		StringBuilder sb = new StringBuilder();
		for (IFullCalendarHeaderToolbarButton<?> leftPart : getCenter())
		{

			sb.append(leftPart.toString());
			if (leftPart != FullCalendarHeaderParts.space)
			{
				sb.append(STRING_COMMNA);
			}
		}
		if (sb.indexOf(STRING_COMMNA) > 0)
		{
			sb.deleteCharAt(sb.lastIndexOf(STRING_COMMNA));
		}
		return sb.toString();
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @return
	 */
	public List<IFullCalendarHeaderToolbarButton<?>> getCenter()
	{
		return center;
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param left
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setCenter(IFullCalendarHeaderToolbarButton<?>... left)
	{
		List<IFullCalendarHeaderToolbarButton<?>> all = Arrays.asList(left);
		return setCenter(all);
	}

	/**
	 * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be
	 * displayed adjacently.
	 * Values separated by a space will be displayed with a small gap in between.
	 *
	 * @param center
	 *
	 * @return
	 */
	public FullCalendarHeaderToolBarOptions setCenter(List<IFullCalendarHeaderToolbarButton<?>> center)
	{
		this.center = center;
		return this;
	}

}
