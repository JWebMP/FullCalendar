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
package za.co.mmagon.jwebswing.plugins.fullcalendar;

import java.util.Map;
import za.co.mmagon.jwebswing.base.servlets.interfaces.IDataComponent;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.fullcalendar.options.FullCalendarEventsList;
import za.co.mmagon.jwebswing.plugins.fullcalendar.options.FullCalendarHeaderOptions;

/**
 * All the options
 * <p>
 * @author GedMarc
 * @since Mar 4, 2015
 * @version 1.0
 * <p>
 * <p>
 */
public class FullCalendarOptions extends JavaScriptPart implements IDataComponent<FullCalendarEventsList>
{

    private static final long serialVersionUID = 1L;
    /**
     * The header
     */
    private FullCalendarHeaderOptions header;
    /**
     * The footer
     */
    private FullCalendarHeaderOptions footer;

    /**
     * can click day/week names to navigate views
     */
    private Boolean navLinks;
    /**
     * The grid is editable or not
     */
    private Boolean editable;
    /**
     * Allow the "more" link when there's too many events
     */
    private Boolean eventLimit;
    /**
     * The events for this calendar
     */
    private FullCalendarEventsList events;
    /**
     * To use jquery ui themes
     */
    private Boolean theme;

    /**
     * The options for the component
     */
    public FullCalendarOptions()
    {

    }

    /**
     * The header options. never null
     *
     * @return
     */
    public FullCalendarHeaderOptions getHeader()
    {
        if (header == null)
        {
            header = new FullCalendarHeaderOptions();
        }
        return header;
    }

    /**
     * Sets the header
     *
     * @param header
     * @return
     */
    public FullCalendarOptions setHeader(FullCalendarHeaderOptions header)
    {
        this.header = header;
        return this;
    }

    /**
     * The footer of the calendar
     *
     * @return
     */
    public FullCalendarHeaderOptions getFooter()
    {
        if (footer == null)
        {
            footer = new FullCalendarHeaderOptions();
        }
        return footer;
    }

    /**
     * Sets the footer
     *
     * @param footer
     * @return
     */
    public FullCalendarOptions setFooter(FullCalendarHeaderOptions footer)
    {
        this.footer = footer;
        return this;
    }

    @Override
    public FullCalendarEventsList getData(Map<String, String[]> params)
    {
        return getEvents();
    }

    /**
     * can click day/week names to navigate views
     *
     * @return
     */
    public Boolean getNavLinks()
    {
        return navLinks;
    }

    /**
     * can click day/week names to navigate views
     *
     * @param navLinks
     * @return
     */
    public FullCalendarOptions setNavLinks(Boolean navLinks)
    {
        this.navLinks = navLinks;
        return this;
    }

    /**
     * The grid is editable or not
     *
     * @return
     */
    public Boolean getEditable()
    {
        return editable;
    }

    /**
     * The grid is editable or not
     *
     * @param editable
     * @return
     */
    public FullCalendarOptions setEditable(Boolean editable)
    {
        this.editable = editable;
        return this;
    }

    /**
     * Allow the "more" link when there's too many events
     *
     * @return
     */
    public Boolean getEventLimit()
    {
        return eventLimit;
    }

    /**
     * Allow the "more" link when there's too many events
     *
     * @param eventLimit
     * @return
     */
    public FullCalendarOptions setEventLimit(Boolean eventLimit)
    {
        this.eventLimit = eventLimit;
        return this;
    }

    /**
     * The events for this calendar
     *
     * @return
     */
    public FullCalendarEventsList getEvents()
    {
        if (events == null)
        {
            events = new FullCalendarEventsList();
        }
        return events;
    }

    /**
     * The events for this calendar
     *
     * @param events
     * @return
     */
    public FullCalendarOptions setEvents(FullCalendarEventsList events)
    {
        this.events = events;
        return this;
    }

    /**
     * To use the themes
     *
     * @return
     */
    public Boolean getTheme()
    {
        return theme;
    }

    /**
     * To use jquery ui themes
     *
     * @param theme
     * @return
     */
    public FullCalendarOptions setTheme(Boolean theme)
    {
        this.theme = theme;
        return this;
    }

}
