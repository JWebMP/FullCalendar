/*
 * Copyright (C) 2017 Marc Magon
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

    /**
     * A list of calendar events
     */
    private List<FullCalendarEvent> events;

    /**
     * Returns the list of calendar events
     *
     * @return
     */
    @JsonValue
    public List<FullCalendarEvent> getEvents()
    {
        if (events == null)
        {
            events = new ArrayList<>();
        }

        return events;
    }

    /**
     * Sets the list of calendar events
     *
     * @param events
     */
    public void setEvents(List<FullCalendarEvent> events)
    {
        this.events = events;
    }

}
