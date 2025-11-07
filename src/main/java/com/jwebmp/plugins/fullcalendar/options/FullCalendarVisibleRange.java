package com.jwebmp.plugins.fullcalendar.options;


import com.fasterxml.jackson.annotation.*;
import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import com.jwebmp.core.htmlbuilder.javascript.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@NgDataType
@Getter
@Setter
public class FullCalendarVisibleRange extends JavaScriptPart<FullCalendarVisibleRange> implements INgDataType<FullCalendarVisibleRange>
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;

    public FullCalendarVisibleRange()
    {
    }

    public FullCalendarVisibleRange(LocalDate start, LocalDate end)
    {
        this.start = start;
        this.end = end;
    }
}
