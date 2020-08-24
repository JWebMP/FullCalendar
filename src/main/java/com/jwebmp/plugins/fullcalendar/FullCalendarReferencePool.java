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
package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.references.CSSReference;
import com.jwebmp.core.base.references.JavascriptReference;
import com.jwebmp.core.base.servlets.interfaces.ReferencePool;

/**
 * Default reference pool structure
 *
 * @author GedMarc
 * @since 20 Apr 2016
 */
public enum FullCalendarReferencePool
		implements ReferencePool
{

/*	FullCalendarGoogleCalendarReference(new JavascriptReference("FullCalendar1", 1.0, "bower_components/fullcalendar/dist/gcal.min.js",
	                                                            "https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/gcal.min.js"), null),*/
	FullCalendarReferencePrint(null, new CSSReference("FullCalendar2", 1.0, "bower_components/fullcalendar/dist/fullcalendar.print.min.css",
	                                                  "https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.print.min.css")),
	FullCalendarReference(new JavascriptReference("FullCalendar3" + "Core", 1.0, "bower_components/fullcalendar/dist/fullcalendar.min.js",
	                                              "https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.js"),
	                      new CSSReference("FullCalendar4" + "Core", 1.0, "bower_components/fullcalendar/dist/fullcalendar.min.css",
	                                       "https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.min.css")),
	;
	/**
	 * The actual javascript
	 */
	private JavascriptReference javaScriptReference;
	/**
	 * The actual css reference
	 */
	private CSSReference cssReference;

	/**
	 * Constructs a reference pool
	 */
	FullCalendarReferencePool()
	{
	}

	/**
	 * Constructs a new reference pool
	 *
	 * @param javaScriptReference
	 * @param cssReference
	 */
	FullCalendarReferencePool(JavascriptReference javaScriptReference, CSSReference cssReference)
	{
		this.javaScriptReference = javaScriptReference;
		this.cssReference = cssReference;
	}

	/**
	 * Gets the cSS reference
	 *
	 * @return
	 */
	@Override
	public CSSReference getCssReference()
	{
		return cssReference;
	}

	/**
	 * Sets the CSS Reference
	 *
	 * @param cssReference
	 */
	@Override
	public void setCssReference(CSSReference cssReference)
	{
		this.cssReference = cssReference;
	}

	/**
	 * Returns the javascript reference
	 *
	 * @return
	 */
	@Override
	public JavascriptReference getJavaScriptReference()
	{
		return javaScriptReference;
	}

	/**
	 * Sets the javascript reference
	 *
	 * @param javaScriptReference
	 */
	@Override
	public void setJavaScriptReference(JavascriptReference javaScriptReference)
	{
		this.javaScriptReference = javaScriptReference;
	}
}
