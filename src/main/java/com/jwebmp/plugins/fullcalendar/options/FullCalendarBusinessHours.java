package com.jwebmp.plugins.fullcalendar.options;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.guicedee.services.jsonrepresentation.IJsonRepresentation;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@NgDataType
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Getter
@Setter
public class FullCalendarBusinessHours implements IJsonRepresentation<FullCalendarBusinessHours>, INgDataType<FullCalendarBusinessHours>
{
    private int[] daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
