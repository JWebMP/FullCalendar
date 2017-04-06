/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.mmagon.jwebswing.plugins.fullcalendar;

import java.util.Date;
import org.junit.Assert;
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
        fc.setID("fc");
        System.out.println(fc.renderJavascript());
        Assert.assertEquals("$('#fc').fullCalendar();\n", fc.renderJavascript().toString());
    }

    @Test
    public void testTag()
    {
        FullCalendar fc = new FullCalendar();
        fc.setID("fc");
        System.out.println(fc.toString(true));
        Assert.assertEquals("<div id=\"fc\"></div>", fc.toString(true));
    }

    @Test
    public void testComplex()
    {
        FullCalendar calendar = new FullCalendar();

        Date evDate = new Date(2017, 4, 4, 0, 0, 0);

        calendar.getOptions().setNavLinks(true);
        calendar.getOptions().setEditable(true);
        calendar.getOptions().setTheme(true);

        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 1").setStart(evDate));

        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 2").setStart(evDate));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 3").setStart(evDate));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 4").setStart(evDate));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 5").setStart(evDate));
        calendar.getOptions().getEvents().getEvents().add(new FullCalendarEvent().setTitle("Event 6").setStart(evDate));

        calendar.getOptions().getHeader().setLeft(FullCalendarHeaderParts.prev, FullCalendarHeaderParts.next, FullCalendarHeaderParts.space, FullCalendarHeaderParts.today);
        calendar.getOptions().getHeader().setCenter(FullCalendarHeaderParts.title);
        calendar.getOptions().getHeader().setRight(FullCalendarHeaderParts.month, FullCalendarHeaderParts.agendaWeek, FullCalendarHeaderParts.agendaDay, FullCalendarHeaderParts.listWeek);

        calendar.setID("fc");

        System.out.println(calendar.renderJavascript());

        Assert.assertEquals("$('#fc').fullCalendar({\n"
                + "  \"header\" : {\n"
                + "    \"left\" : \"prev,next, today\",\n"
                + "    \"right\" : \"month,agendaWeek,agendaDay,listWeek\",\n"
                + "    \"center\" : \"title\"\n"
                + "  },\n"
                + "  \"navLinks\" : true,\n"
                + "  \"editable\" : true,\n"
                + "  \"events\" : [ {\n"
                + "    \"title\" : \"Event 1\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  }, {\n"
                + "    \"title\" : \"Event 2\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  }, {\n"
                + "    \"title\" : \"Event 3\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  }, {\n"
                + "    \"title\" : \"Event 4\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  }, {\n"
                + "    \"title\" : \"Event 5\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  }, {\n"
                + "    \"title\" : \"Event 6\",\n"
                + "    \"start\" : 61451992800000\n"
                + "  } ],\n"
                + "  \"theme\" : true\n"
                + "});\n"
                + "", calendar.renderJavascript().toString());
    }

}
