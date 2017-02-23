/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package za.co.mmagon.jwebswing.plugins.fullcalendar;

import za.co.mmagon.jwebswing.Component;
import za.co.mmagon.jwebswing.Page;
import za.co.mmagon.jwebswing.PageConfigurator;
import za.co.mmagon.jwebswing.plugins.PluginInformation;

/**
 *
 * @author GedMarc
 * @since 23 Feb 2017
 *
 */
@PluginInformation(pluginName = "Full Calendar", pluginUniqueName = "full-calendar", pluginDescription = "", pluginVersion = "3.2.0",
        pluginDependancyUniqueIDs = "moment", pluginCategories = "jquery, calendar, schedular, event planner, events, ui, web", pluginSubtitle = "Display a full-size drag-n-drop event calendar, leveraging jQuery. ",
        pluginGitUrl = "https://github.com/GedMarc/JWebSwing-FullCalendarPlugin", pluginSourceUrl = "",
        pluginWikiUrl = "https://github.com/GedMarc/JWebSwing-FullCalendarPlugin/wiki",
        pluginOriginalHomepage = "https://fullcalendar.io/")
public class FullCalendarPageConfigurator extends PageConfigurator
{

    private static final long serialVersionUID = 1L;
    public static final String FullCalendarEnabled = "full-calendar-enabled";

    public FullCalendarPageConfigurator()
    {

    }

    @Override
    public Page configure(Page page)
    {
        if (!page.isConfigured())
        {
            if (page.getBody().readChildrenPropertyFirstResult(FullCalendarEnabled, true))
            {

            }
        }
        return page;
    }

    public static void setFullCalendarRequired(Component component, boolean required)
    {
        component.getProperties().put(FullCalendarEnabled, required);
    }
}
