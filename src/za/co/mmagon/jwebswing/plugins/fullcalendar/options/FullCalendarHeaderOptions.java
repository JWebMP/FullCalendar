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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.fullcalendar.options.enumerations.FullCalendarHeaderParts;

/**
 * header
 * <p>
 * Defines the buttons and title at the top of the calendar.
 *
 * @author GedMarc
 * @since 04 Feb 2017
 *
 */
public class FullCalendarHeaderOptions extends JavaScriptPart
{

    private static final long serialVersionUID = 1L;
    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     */
    @JsonIgnoreProperties(allowSetters = true)
    private List<FullCalendarHeaderParts> left;
    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     */
    @JsonIgnoreProperties(allowSetters = true)
    private List<FullCalendarHeaderParts> right;
    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     */
    @JsonIgnoreProperties(allowSetters = true)
    private List<FullCalendarHeaderParts> center;

    /**
     * A new instance of the header options
     */
    public FullCalendarHeaderOptions()
    {
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param left
     * @param right
     * @param center
     */
    public FullCalendarHeaderOptions(List<FullCalendarHeaderParts> left, List<FullCalendarHeaderParts> right, List<FullCalendarHeaderParts> center)
    {
        this.left = left;
        this.right = right;
        this.center = center;
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @return
     */
    public List<FullCalendarHeaderParts> getLeft()
    {
        return left;
    }

    @JsonProperty("left")
    protected String getLeftJson()
    {
        StringBuilder sb = new StringBuilder();
        for (FullCalendarHeaderParts leftPart : getLeft())
        {

            sb.append(leftPart.toString());
            if (leftPart != FullCalendarHeaderParts.space)
            {
                sb.append(",");
            }
        }
        if (sb.indexOf(",") > 0)
        {
            sb = sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param left
     *
     * @return
     */
    public FullCalendarHeaderOptions setLeft(List<FullCalendarHeaderParts> left)
    {
        this.left = left;
        return this;
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param left
     *
     * @return
     */
    public FullCalendarHeaderOptions setLeft(FullCalendarHeaderParts... left)
    {
        List<FullCalendarHeaderParts> all = Arrays.asList(left);
        return setLeft(all);
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @return
     */
    public List<FullCalendarHeaderParts> getRight()
    {
        return right;
    }

    @JsonProperty("right")
    protected String getRightJson()
    {
        StringBuilder sb = new StringBuilder();
        for (FullCalendarHeaderParts leftPart : getRight())
        {

            sb.append(leftPart.toString());
            if (leftPart != FullCalendarHeaderParts.space)
            {
                sb.append(",");
            }
        }
        if (sb.indexOf(",") > 0)
        {
            sb = sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param right
     *
     * @return
     */
    public FullCalendarHeaderOptions setRight(List<FullCalendarHeaderParts> right)
    {
        this.right = right;
        return this;
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param left
     *
     * @return
     */
    public FullCalendarHeaderOptions setRight(FullCalendarHeaderParts... left)
    {
        List<FullCalendarHeaderParts> all = Arrays.asList(left);
        return setRight(all);
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @return
     */
    public List<FullCalendarHeaderParts> getCenter()
    {
        return center;
    }

    @JsonProperty("center")
    protected String getCenterJson()
    {
        StringBuilder sb = new StringBuilder();
        for (FullCalendarHeaderParts leftPart : getCenter())
        {

            sb.append(leftPart.toString());
            if (leftPart != FullCalendarHeaderParts.space)
            {
                sb.append(",");
            }
        }
        if (sb.indexOf(",") > 0)
        {
            sb = sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param center
     *
     * @return
     */
    public FullCalendarHeaderOptions setCenter(List<FullCalendarHeaderParts> center)
    {
        this.center = center;
        return this;
    }

    /**
     * An object can be supplied with properties left, center, and right. These properties contain strings with comma/space separated values. Values separated by a comma will be displayed adjacently.
     * Values separated by a space will be displayed with a small gap in between.
     *
     * @param left
     *
     * @return
     */
    public FullCalendarHeaderOptions setCenter(FullCalendarHeaderParts... left)
    {
        List<FullCalendarHeaderParts> all = Arrays.asList(left);
        return setCenter(all);
    }

}
