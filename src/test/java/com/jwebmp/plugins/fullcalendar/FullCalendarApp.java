package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.angular.client.annotations.angular.*;
import com.jwebmp.core.base.angular.client.services.interfaces.*;
import com.jwebmp.core.base.angular.services.*;

@NgApp(value = "fullcalendar", bootComponent = FullCalendarExample.class)
public class FullCalendarApp extends NGApplication<FullCalendarApp> implements INgApp<FullCalendarApp>
{
    public FullCalendarApp()
    {
        getOptions().setTitle("Full Calendar App");
    }

}
