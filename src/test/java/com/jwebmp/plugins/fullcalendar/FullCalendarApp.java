package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.angular.services.*;
import com.jwebmp.core.base.angular.services.annotations.*;

@NgApp(name = "fullcalendar", bootComponent = FullCalendarExample.class)
public class FullCalendarApp extends NGApplication<FullCalendarApp>
{
	public FullCalendarApp()
	{
		getOptions().setTitle("Full Calendar App");
	}
	
}
