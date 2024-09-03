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

import com.jwebmp.core.base.angular.client.annotations.typescript.TsDependency;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.PluginStatus;
import com.jwebmp.core.services.IPage;
import com.jwebmp.core.services.IPageConfigurator;
import jakarta.validation.constraints.NotNull;

/**
 * @author GedMarc
 * @since 23 Feb 2017
 */
@PluginInformation(pluginName = "Full Calendar",
                   pluginUniqueName = "full-calendar",
                   pluginDescription = "Full Calendar is a free, open-source project that enables you to design full calendar-like features and functions that is fully interactive, ajax controlled and mobile friendly. ",
                   pluginVersion = "5.3.0",
                   pluginDependancyUniqueIDs = "jquery,moment",
                   pluginCategories = "jquery, calendar, schedular, event planner, events, ui, web",
                   pluginSubtitle = "Display a full-size drag-n-drop event calendar, leveraging jQuery. ",
                   pluginGitUrl = "https://github.com/GedMarc/JWebMP-FullCalendarPlugin",
                   pluginSourceUrl = "https://github.com/fullcalendar",
                   pluginWikiUrl = "https://github.com/GedMarc/JWebMP-FullCalendarPlugin/wiki",
                   pluginOriginalHomepage = "https://fullcalendar.io/",
                   pluginSourceDonateUrl = "https://fullcalendar.io/donate/",
                   pluginDownloadUrl = "https://mvnrepository.com/artifact/com.jwebmp.plugins.jquery/jwebmp-full-calendar",
                   pluginIconUrl = "",
                   pluginIconImageUrl = "",
                   pluginLastUpdatedDate = "2020/12/16",
                   pluginGroupId = "com.jwebmp.plugins.jquery",
                   pluginArtifactId = "jwebmp-full-calendar",
                   pluginModuleName = "com.jwebmp.plugins.fullcalendar",
                   pluginStatus = PluginStatus.Released
)
@TsDependency(value = "@fullcalendar/angular", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/adaptive", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/daygrid", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/timegrid", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/list", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/interaction", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/scrollgrid", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/bootstrap5", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/luxon2", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/moment", version = "^6.1.13")
@TsDependency(value = "@fullcalendar/moment-timezone", version = "^6.1.13")


//@NgBootImportReference(value = "FullCalendarModule ", reference = "@fullcalendar/angular")
/*@NgBootImportReference(name = "!dayGridPlugin", reference = "@fullcalendar/daygrid")
@NgBootImportReference(name = "!scrollGridPlugin", reference = "@fullcalendar/scrollgrid")
@NgBootImportReference(name = "!timeGridPlugin", reference = "@fullcalendar/timegrid")
@NgBootImportReference(name = "!listPlugin", reference = "@fullcalendar/list")
@NgBootImportReference(name = "!interactionPlugin", reference = "@fullcalendar/interaction")*/

@TsDependency(value = "@fullcalendar/bootstrap5", version = "^6.1.13")
//@TsDependency(value = "bootstrap-icons", version = "*")
//@NgBootGlobalField("FullCalendarModule.registerPlugins([\n" + "  bootstrap5Plugin\n" + "])")
//@NgBootModuleImport("FullCalendarModule")

/*@NgBootGlobalField("FullCalendarModule.registerPlugins([\n" +
        "  dayGridPlugin,\n" +
        "  timeGridPlugin,\n" +
        "  listPlugin,\n" +
        "  interactionPlugin,\n" +
        "])")*/

public class FullCalendarPageConfigurator
        implements IPageConfigurator<FullCalendarPageConfigurator>
{

    public FullCalendarPageConfigurator()
    {
        //Nothing Needed
    }

    @NotNull
    @Override
    public IPage<?> configure(IPage<?> page)
    {
        return page;
    }

    @Override
    public boolean enabled()
    {
        return true;
    }
}
