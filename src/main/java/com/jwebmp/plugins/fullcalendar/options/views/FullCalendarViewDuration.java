package com.jwebmp.plugins.fullcalendar.options.views;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import com.jwebmp.core.htmlbuilder.javascript.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
public class FullCalendarViewDuration<J extends FullCalendarViewDuration<J>> extends JavaScriptPart<J> implements INgDataType<J>
{
    private String years;
    private String months;
    private String weeks;
    private Integer days;

    public String getYears()
    {
        return years;
    }

    @SuppressWarnings("unchecked")
    public J setYears(String years)
    {
        this.years = years;
        return (J) this;
    }

    public String getMonths()
    {
        return months;
    }

    @SuppressWarnings("unchecked")
    public J setMonths(String months)
    {
        this.months = months;
        return (J) this;
    }

    public String getWeeks()
    {
        return weeks;
    }

    @SuppressWarnings("unchecked")
    public J setWeeks(String weeks)
    {
        this.weeks = weeks;
        return (J) this;
    }

    public Integer getDays()
    {
        return days;
    }

    @SuppressWarnings("unchecked")
    public J setDays(Integer days)
    {
        this.days = days;
        return (J) this;
    }
}