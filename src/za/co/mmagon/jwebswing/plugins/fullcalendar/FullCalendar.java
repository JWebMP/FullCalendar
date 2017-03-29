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

import za.co.mmagon.jwebswing.base.html.Div;
import za.co.mmagon.jwebswing.plugins.ComponentInformation;

/**
 * An implementation of
 * <p>
 * @author Marc Magon
 * @since 17 Jan 2017
 * @version 1.0
 */
@ComponentInformation(name = "Full Calendar", description = "Display a full-size drag-n-drop event calendar", url = "https://fullcalendar.io/")
public class FullCalendar extends Div<FullCalendarChildren, FullCalendarAttributes, FullCalendarFeatures, FullCalendarEvents, FullCalendar>
{

    private static final long serialVersionUID = 1L;
    /**
     * The associated feature
     */
    private FullCalendarFeature feature;

    /**
     * Constructs a new instance
     */
    public FullCalendar()
    {
        addFeature(getFeature());
        FullCalendarPageConfigurator.setRequired(this, true);
    }

    /**
     * Returns the feature if any is required
     *
     * @return
     */
    public final FullCalendarFeature getFeature()
    {
        if (feature == null)
        {
            feature = new FullCalendarFeature(this);
        }
        return feature;
    }

    /**
     * Returns the options if any is required
     *
     * @return
     */
    @Override
    public FullCalendarOptions getOptions()
    {
        return getFeature().getOptions();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.getID().hashCode());
        return hash;
    }

}
