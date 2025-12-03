package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.*;

import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.*;
import io.smallrye.mutiny.Uni;

import java.util.*;

import static com.guicedee.client.implementations.ObjectBinderKeys.*;

public abstract class FullCalendarDropEvent extends ClickAdapter<FullCalendarDropEvent>
{
    public FullCalendarDropEvent()
    {
    }

    public abstract void onDrop(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);

    @Override
    public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response)
    {
        LinkedHashMap map = (LinkedHashMap) call.getUnknownFields()
                                                .get("infoObj");
        LinkedHashMap<String, String> info = (LinkedHashMap<String, String>) map.get("event");

        ObjectMapper mapper = IGuiceContext.get(DefaultObjectMapper);
        FullCalendarEventInfo el = mapper.convertValue(info, FullCalendarEventInfo.class);
        onDrop(call, response, el);
        return Uni.createFrom()
                  .voidItem();
    }

}
