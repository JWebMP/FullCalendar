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
package com.jwebmp.plugins.fullcalendar.angularjs;

import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.base.angular.directives.*;
import jakarta.validation.constraints.*;

import static com.jwebmp.core.FileTemplates.*;

/**
 * Maps to the angular function of right click
 *
 * @author GedMarc
 * @since 25 Jun 2016
 */
public class FullCalendarAngularJsDirective
		extends AngularDirectiveBase<FullCalendarAngularJsDirective>
{


	/**
	 * Constructs a new right click directive based on the angular object passed in
	 * <p>
	 */
	public FullCalendarAngularJsDirective()
	{
		super("angularJsFullCalendarDirective");
	}

	/**
	 * Renders the right click directive from the JavaScript file
	 *
	 * @return
	 */
	@Override
	@NotNull
	public String renderFunction()
	{
		return getFileTemplate(getClass(),"FullCalendarDirective", "fullcalendar.min.js")
				       .toString();
	}

	/**
	 * If this page configurator is enabled
	 *
	 * @return if the configuration must run
	 */
	@Override
	public boolean enabled()
	{
		return AngularPageConfigurator.isEnabled() && AngularPageConfigurator.isRequired();
	}
}
