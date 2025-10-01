package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarResourceItem;
import io.smallrye.mutiny.Uni;

import java.util.LinkedHashMap;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.DefaultObjectMapper;

public abstract class FullCalendarEventDropEvent extends ClickAdapter<FullCalendarEventDropEvent>
{
    public FullCalendarEventDropEvent()
    {
    }

    public abstract void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response,
                                     FullCalendarResourceItem oldResource, FullCalendarResourceItem newResource,
                                     FullCalendarEventInfo oldEvent, FullCalendarEventInfo newEvent);

    @Override
    public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response)
    {
        LinkedHashMap map = (LinkedHashMap) call.getUnknownFields()
                                                .get("infoObj");
        LinkedHashMap<String, String> oldEventMap = (LinkedHashMap<String, String>) map.get("oldEvent");
        LinkedHashMap<String, String> eventMap = (LinkedHashMap<String, String>) map.get("event");
        LinkedHashMap<String, String> oldResourceMap = (LinkedHashMap<String, String>) map.get("oldResource");
        LinkedHashMap<String, String> newResourceMap = (LinkedHashMap<String, String>) map.get("newResource");

        ObjectMapper mapper = IGuiceContext.get(DefaultObjectMapper);
        FullCalendarEventInfo el = mapper.convertValue(oldEventMap, FullCalendarEventInfo.class);
        FullCalendarEventInfo el2 = mapper.convertValue(eventMap, FullCalendarEventInfo.class);

        FullCalendarResourceItem ri1 = mapper.convertValue(oldResourceMap, FullCalendarResourceItem.class);
        FullCalendarResourceItem ri2 = mapper.convertValue(newResourceMap, FullCalendarResourceItem.class);
        el.updateDates();
        el2.updateDates();


        onEventDrop(call, response, ri1, ri2, el, el2);
        return Uni.createFrom()
                  .voidItem();
    }

}
