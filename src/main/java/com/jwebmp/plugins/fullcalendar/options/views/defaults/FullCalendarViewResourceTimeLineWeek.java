package com.jwebmp.plugins.fullcalendar.options.views.defaults;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import com.jwebmp.plugins.fullcalendar.options.views.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
public class FullCalendarViewResourceTimeLineWeek extends FullCalendarView<FullCalendarViewResourceTimeLineWeek> implements INgDataType<FullCalendarViewResourceTimeLineWeek>
{
    public FullCalendarViewResourceTimeLineWeek()
    {
        setType(FullCalendarDefaultViews.resourceTimelineWeek);
    }

}
