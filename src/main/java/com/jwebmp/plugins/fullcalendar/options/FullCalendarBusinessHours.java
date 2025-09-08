package com.jwebmp.plugins.fullcalendar.options;

import com.guicedee.services.jsonrepresentation.IJsonRepresentation;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;

import java.time.LocalTime;
import java.util.List;

@NgDataType
public class FullCalendarBusinessHours implements IJsonRepresentation<FullCalendarBusinessHours>, INgDataType<FullCalendarBusinessHours>
{

    private int[] daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public int[] getDaysOfWeek()
    {
        return daysOfWeek;
    }

    public FullCalendarBusinessHours setDaysOfWeek(int[] daysOfWeek)
    {
        this.daysOfWeek = daysOfWeek;
        return this;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public FullCalendarBusinessHours setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
        return this;
    }

    public LocalTime getEndTime()
    {
        return endTime;
    }

    public FullCalendarBusinessHours setEndTime(LocalTime endTime)
    {
        this.endTime = endTime;
        return this;
    }
}
