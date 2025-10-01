package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import io.smallrye.mutiny.Uni;

import java.util.LinkedHashMap;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.DefaultObjectMapper;


public abstract class FullCalendarSelectEvent extends ClickAdapter<FullCalendarSelectEvent>
{
    public FullCalendarSelectEvent()
    {
    }

    public abstract void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarSelectEventInfo selectEvent);

    @Override
    public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response)
    {
        LinkedHashMap map = (LinkedHashMap) call.getUnknownFields()
                                                .get("infoObj");
        ObjectMapper mapper = IGuiceContext.get(DefaultObjectMapper);
        FullCalendarSelectEventInfo el = mapper.convertValue(map, FullCalendarSelectEventInfo.class);
        el.updateDates();
        onSelect(call, response, el);
        return Uni.createFrom()
                  .voidItem();
    }

}
