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
package com.jwebmp.plugins.fullcalendar.options.resources;

import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.base.angular.services.annotations.*;
import com.jwebmp.core.base.angular.services.interfaces.*;
import com.jwebmp.core.htmlbuilder.javascript.*;

import java.util.*;

/**
 * @author GedMarc
 * @since 05 Feb 2017
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
public class FullCalendarResourceItemsList
		extends JavaScriptPart<FullCalendarResourceItemsList> implements INgDataType<FullCalendarResourceItemsList>
{
	
	/**
	 * A list of calendar events
	 */
	private List<FullCalendarResourceItem> resources;

	/**
	 * Returns the list of calendar events
	 *
	 * @return
	 */
	@JsonValue
	public List<FullCalendarResourceItem> getResources()
	{
		if (resources == null)
		{
			resources = new ArrayList<>();
		}

		return resources;
	}

	/**
	 * Sets the list of calendar events
	 *
	 * @param resources
	 */
	public void setResources(List<FullCalendarResourceItem> resources)
	{
		this.resources = resources;
	}

}
