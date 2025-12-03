package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import io.smallrye.mutiny.Uni;

import java.util.LinkedHashMap;

import static com.guicedee.client.implementations.ObjectBinderKeys.DefaultObjectMapper;

public abstract class FullCalendarEventClickEvent extends ClickAdapter<FullCalendarEventClickEvent>
{
    public FullCalendarEventClickEvent()
    {
    }

    public abstract void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);

    @Override
    public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response)
    {
        LinkedHashMap map = (LinkedHashMap) call.getUnknownFields()
                                                .get("infoObj");
        LinkedHashMap<String, String> info = (LinkedHashMap<String, String>) map.get("event");

        ObjectMapper mapper = IGuiceContext.get(DefaultObjectMapper);
        FullCalendarEventInfo el = mapper.convertValue(info, FullCalendarEventInfo.class);
        el.updateDates();
        onEventClick(call, response, el);
        return Uni.createFrom()
                  .voidItem();
    }

}
