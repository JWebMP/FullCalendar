package com.jwebmp.plugins.fullcalendar.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;

import java.util.Map;

import static com.guicedee.guicedinjection.interfaces.ObjectBinderKeys.DefaultObjectMapper;

public abstract class FullCalendarEventReceiveEvent extends ClickAdapter<FullCalendarEventReceiveEvent>
{
    public FullCalendarEventReceiveEvent()
    {
    }

    public abstract void onEventReceive(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);

    @Override
    public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
    {
        Map<String, Object> info = (Map<String, Object>) call.getUnknownFields()
                                                             .get("infoObj");
        ObjectMapper mapper = IGuiceContext.get(DefaultObjectMapper);
        FullCalendarEventInfo el = mapper.convertValue(info, FullCalendarEventInfo.class);
        el.updateDates();
        onEventReceive(call, response, el);
    }

}
