package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.services.EventBusService;
import com.jwebmp.plugins.fullcalendar.events.FullCalendarSelectEventTest;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarEvent;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarEventsList;

import java.time.LocalDateTime;

@NgComponent("full-cal-jwebmp")
@NgComponentReference(EventBusService.class)
public class FullCalendarComponentExample extends FullCalendar<FullCalendarComponentExample>
{
    public FullCalendarComponentExample()
    {
        super("fcjweb");
        setSelectEvent(new FullCalendarSelectEventTest());
    }


    @Override
    public FullCalendarEventsList getInitialEvents()
    {
        FullCalendarEventsList fullCalendarEventsList = new FullCalendarEventsList();
        fullCalendarEventsList.getEvents()
                .add(new FullCalendarEvent().setId("1")
                        .setTitle("Event 1")
                        .setStart(LocalDateTime.now()));
        return fullCalendarEventsList;
    }


}
