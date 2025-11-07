package com.jwebmp.plugins.fullcalendar.options.titles;

import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import com.jwebmp.core.htmlbuilder.javascript.*;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
@Getter
@Setter
public class FullCalendarTitleFormat extends JavaScriptPart<FullCalendarTitleFormat> implements INgDataType<FullCalendarTitleFormat>
{
    private FullCalendarTitleFormatTypes year;
    private FullCalendarTitleFormatTypes month;
    private FullCalendarTitleFormatTypes day;
    private FullCalendarTitleFormatTypes weekday;
    private FullCalendarTitleFormatTypes hour;
    private FullCalendarTitleFormatTypes minute;
    private FullCalendarTitleFormatTypes second;
    private FullCalendarTitleFormatTypes hour12;
    private FullCalendarTitleFormatTypes timeZoneName;
}
