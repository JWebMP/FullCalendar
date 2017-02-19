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
package za.co.mmagon.jwebswing.plugins.fullcalendar.options;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;

/**
 *
 * @author GedMarc
 * @since 05 Feb 2017
 *
 */
public class FullCalendarEventsList extends JavaScriptPart
{

    private static final long serialVersionUID = 1L;

    private List<FullCalendarEvent> events;

    @JsonValue
    public List<FullCalendarEvent> getEvents()
    {
        if (events == null)
        {
            events = new ArrayList<>();
        }

        return events;
    }

    public void setEvents(List<FullCalendarEvent> events)
    {
        this.events = events;
    }

}
