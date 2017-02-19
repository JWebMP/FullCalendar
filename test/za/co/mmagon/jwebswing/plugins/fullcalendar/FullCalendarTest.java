/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.mmagon.jwebswing.plugins.fullcalendar;

import java.util.Date;
import org.junit.Test;
import za.co.mmagon.jwebswing.plugins.fullcalendar.options.FullCalendarEvent;
import za.co.mmagon.jwebswing.plugins.fullcalendar.options.enumerations.FullCalendarHeaderParts;

/**
 *
 * @author GedMarc
 */
public class FullCalendarTest
{

    public FullCalendarTest()
    {
    }

    @Test
    public void testSomeMethod()
    {
        FullCalendar fc = new FullCalendar();

        System.out.println(fc.renderJavascript());
    }

    @Test
    public void testTag()
    {
        FullCalendar fc = new FullCalendar();

        System.out.println(fc.toString(true));
    }

    @Test
    public void testComplex()
    {
        FullCalendar calendar = new FullCalendar();
        calendar.getOptions().setNavLinks(true);
        calendar.getOptions().setEditable(true);
        calendar.getOptions().setTheme(true);

        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 1").setStart(new Date()));

        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 2").setStart(new Date()));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 3").setStart(new Date()));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 4").setStart(new Date()));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 5").setStart(new Date()));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 6").setStart(new Date()));

        calendar.getOptions().getHeader().setLeft(FullCalendarHeaderParts.prev, FullCalendarHeaderParts.next, FullCalendarHeaderParts.space, FullCalendarHeaderParts.today);
        calendar.getOptions().getHeader().setCenter(FullCalendarHeaderParts.title);
        calendar.getOptions().getHeader().setRight(FullCalendarHeaderParts.month, FullCalendarHeaderParts.agendaWeek, FullCalendarHeaderParts.agendaDay, FullCalendarHeaderParts.listWeek);

        System.out.println(calendar.renderJavascript());
    }

}
