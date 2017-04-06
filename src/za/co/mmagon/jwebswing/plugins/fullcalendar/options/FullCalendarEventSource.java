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

/**
 * Event Source Object
 * <p>
 * An "event source" is anything that provides FullCalendar with data about events. It can be a simple array, an event-generating function that you define, a URL to a json feed, or a Google Calendar
 * feed.
 * <p>
 * Event Objects can have "options" associated with them. However, before you can start specifying options, you must write an Event Object in its extended form. It must be a traditional JavaScript
 * object with properties. Here are the extended forms for each type of Event Source:
 *
 * @author GedMarc
 * @since 05 Feb 2017
 *
 */
public class FullCalendarEventSource extends FullCalendarEvent
{

    private static final long serialVersionUID = 1L;
    /**
     * The URL for an event source
     */
    private String url;

    /**
     * The URL for the event source
     *
     * @return
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * The URL for the events source
     *
     * @param url
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

}
