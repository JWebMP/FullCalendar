package com.jwebmp.plugins.fullcalendar.options.views;

import com.jwebmp.plugins.fullcalendar.*;
import com.jwebmp.plugins.fullcalendar.options.*;
import com.jwebmp.plugins.fullcalendar.options.views.defaults.*;
import org.junit.jupiter.api.*;

import java.time.*;

public class FullCalendarViewsTest
{
    @Test
    public void testVisibleRange()
    {
        FullCalendarViews views = new FullCalendarViews();
        views.addView(new FullCalendarViewTimeGrid().setName("timeGridDay")
                                                    .setVisibleRange(new FullCalendarVisibleRange()
                                                            .setStart(LocalDate.now())
                                                            .setEnd(LocalDate.now())
                                                    )
        );
        System.out.println(views.toJson());
    }

    @Test
    public void viewsMap()
    {
        FullCalendarViews views = new FullCalendarViews();
        views.addView(new FullCalendarViewTimeGrid().setName("timeGridDay"));
        System.out.println(views.toJson());


    }

    @Test
    public void initialViewSetting()
    {
        FullCalendar fce = new FullCalendarComponentExample();
        fce.getOptions()
           .setInitialView(new FullCalendarViewResourceTimeLine());
        System.out.println(fce.getOptions()
                              .toJson());
    }
}