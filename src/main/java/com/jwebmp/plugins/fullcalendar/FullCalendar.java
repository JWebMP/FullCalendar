/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.fullcalendar;

import com.guicedee.guicedinjection.*;
import com.guicedee.guicedservlets.websockets.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.client.annotations.constructors.*;
import com.jwebmp.core.base.angular.client.annotations.functions.*;
import com.jwebmp.core.base.angular.client.annotations.references.*;
import com.jwebmp.core.base.angular.client.annotations.structures.*;
import com.jwebmp.core.base.angular.client.services.*;
import com.jwebmp.core.base.angular.client.services.interfaces.*;
import com.jwebmp.core.base.angular.implementations.*;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.plugins.*;
import com.jwebmp.plugins.fullcalendar.events.*;
import com.jwebmp.plugins.fullcalendar.options.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.util.List;
import java.util.*;

/**
 * An implementation of
 * <p>
 *
 * @author GedMarc
 * @version 1.0
 * @since 17 Jan 2017
 */
@ComponentInformation(name = "Full Calendar",
                      description = "Display a full-size drag-n-drop event calendar",
                      url = "https://fullcalendar.io/")
@NgImportReference(value = "CalendarOptions, DateSelectArg, EventClickArg, EventApi, EventDropArg,EventInput,CalendarApi,FullCalendarComponent", reference = "@fullcalendar/angular")
@NgImportReference(value = "DateClickArg, DropArg, EventReceiveArg, EventResizeDoneArg", reference = "@fullcalendar/interaction")

@NgImportReference(value = "ViewChild", reference = "@angular/core")

@NgDataTypeReference(FullCalendarEventsList.class)
@NgDataTypeReference(FullCalendarEvent.class)
@NgDataTypeReference(FullCalendarResourceItemsList.class)
@NgDataTypeReference(FullCalendarResourceItem.class)

@NgImportReference(value = "bufferTime, Subscription", reference = "rxjs")

@NgField("currentEvents: EventApi[] = [];")
@NgField("@ViewChild('calendar')\n" +
         "    calendarComponent?: FullCalendarComponent;")
@NgField("private calendarApi? : CalendarApi;")


@NgAfterViewInit("this.calendarApi = this.calendarComponent?.getApi();")
@NgAfterViewInit("let currentDate = this.calendarApi?.view.currentStart;")
@NgAfterViewInit("this.fetchData();")

@NgMethod("handleWeekendsToggle() {\n" +
          "    const { calendarOptions } = this;\n" +
          "    calendarOptions.weekends = !calendarOptions.weekends;\n" +
          "  }")

@NgMethod("handleDateClick(selectInfo: DateClickArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.dateClickEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.dateClickEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "\t}")

@NgMethod("handleDateSelect(selectInfo: DateSelectArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.selectEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.selectEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "\t}")


@NgMethod("handleEventClick(selectInfo: EventClickArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.eventClickEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.eventClickEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "  }")

@NgMethod("handleDropEvent(selectInfo: DropArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.dropEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.dropEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "  }")

@NgMethod("handleEventDrop(selectInfo: EventDropArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.eventDropEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.eventDropEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "  }")

@NgMethod("handleEventReceive(selectInfo: EventReceiveArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.receiveEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.receiveEvent},'onClick',undefined,undefined);\n" +
          "  }")

@NgMethod("handleEventResize(selectInfo: EventResizeDoneArg) {\n" +
          "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
          "\t\tcalendarApi.unselect(); // clear date selection\n" +
          "\t\tif(this.eventResizeEvent)\n" +
          "\t\tthis.socketClientService.send('ajax',{infoObj : selectInfo,eventClass : this.eventResizeEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
          "  }")


@NgMethod("handleEvents(events: EventApi[]) {\n" +
          "    this.currentEvents = events;\n" +
          "  }")

@NgComponentReference(SocketClientService.class)

@NgOnDestroy("this.subscription?.unsubscribe();")
@NgOnDestroy("this.socketClientService.deregisterListener(this.listenerName);")
@NgOnDestroy("this.subscriptionAdd?.unsubscribe();")
@NgOnDestroy("this.subscriptionEdit?.unsubscribe();")
@NgOnDestroy("this.subscriptionDelete?.unsubscribe();")

public abstract class FullCalendar<J extends FullCalendar<J>>
		extends Div<FullCalendarChildren, FullCalendarAttributes, FullCalendarFeatures, FullCalendarEvents, J>
		implements INgComponent<J>
{
	/**
	 * The full calendar options list
	 */
	private FullCalendarOptions options;
	private String eventSource;
	private String externalEventContainerId;
	
	
	private FullCalendarDateClickEvent dateClickEvent;
	private FullCalendarEventClickEvent eventClickEvent;
	private FullCalendarEventReceiveEvent receiveEvent;
	private FullCalendarEventResizeEvent eventResizeEvent;
	private FullCalendarEventDropEvent eventDropEvent;
	private FullCalendarDropEvent dropEvent;
	private FullCalendarSelectEvent selectEvent;
	
	protected FullCalendar()
	{
	}
	
	/**
	 * Constructs a new instance
	 */
	public FullCalendar(String id)
	{
		setID(id);
		setTag("full-calendar");
		addAttribute("#calendar", "");
	}
	
	@Override
	public List<String> componentConstructorBody()
	{
		List<String> bodies = INgComponent.super.componentConstructorBody();
		bodies.add("this.subscription = this.socketClientService.registerListener(this.listenerName)" +
		           //     ".pipe(bufferTime(100))" +
		           ".subscribe((message : EventInput[]) => {\n" +
		           " this.calendarApi?.removeAllEvents();\n" +
		           "            for (const messageElement of message) {\n" +
		           "                this.calendarApi?.addEvent(messageElement);\n" +
		           "            }" +
		           //   "alert('received events'); \n" +
		           "});\n");
		
		bodies.add("this.subscriptionAdd = this.socketClientService.registerListener(this.listenerName + 'Add')" +
		           //   ".pipe(bufferTime(100))" +
		           ".subscribe((message : EventApi) => {\n" +
		           "this.calendarApi?.addEvent({...message});" +
		           //"this.events = message; \n" +
		           "});\n");
		
		bodies.add("this.subscriptionEdit = this.socketClientService.registerListener(this.listenerName + 'Edit')" +
		           //    ".pipe(bufferTime(100))" +
		           ".subscribe((message : EventApi) => {\n" +
		           "this.calendarApi?.getEventById('' +message.id)?.remove();\n" +
		           "            this.calendarApi?.addEvent({...message});" +
		           //"this.events = message; \n" +
		           "});\n");
		
		bodies.add("this.subscriptionDelete = this.socketClientService.registerListener(this.listenerName + 'Delete')" +
		           //    ".pipe(bufferTime(100))" +
		           ".subscribe((message : EventApi) => {\n" +
		           "this.calendarApi?.getEventById('' +message.id)?.remove();" +
		           //"this.events = message; \n" +
		           "});\n");
		return bodies;
	}
	
	
	public List<String> componentMethods()
	{
		List<String> methods = INgComponent.super.componentMethods();
		methods.add("fetchData(){\n" +
		            "   this.socketClientService.send(this.listenerName,{className :  '" +
		            getClass().getCanonicalName() + "',\n" +
		            "            listenerName: this.listenerName},this.listenerName);\n" +
		            "}\n");
		return methods;
	}
	
	public List<String> componentFields()
	{
		List<String> fields = INgComponent.super.componentFields();
		fields.add(" private listenerName = '" + getID() + "';");
		fields.add(" private subscription? : Subscription;\n");
		fields.add(" private subscriptionAdd? : Subscription;\n");
		fields.add(" private subscriptionEdit? : Subscription;\n");
		fields.add(" private subscriptionDelete? : Subscription;\n");
		return fields;
	}
	
	
	@Override
	public List<String> fields()
	{
		List<String> out = INgComponent.super.fields();
		out.add("calendarOptions: CalendarOptions = " + getOptions().toJson());
		
		if (dateClickEvent != null)
		{
			out.add("dateClickEvent : string = '" + dateClickEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("dateClickEvent? : string;");
		}
		if (eventClickEvent != null)
		{
			out.add("eventClickEvent : string = '" + eventClickEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("eventClickEvent? : string;");
		}
		if (receiveEvent != null)
		{
			out.add("receiveEvent : string = '" + receiveEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("receiveEvent? : string;");
		}
		if (eventResizeEvent != null)
		{
			out.add("eventResizeEvent : string = '" + eventResizeEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("eventResizeEvent? : string;");
		}
		if (eventDropEvent != null)
		{
			out.add("eventDropEvent : string = '" + eventDropEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("eventDropEvent? : string;");
		}
		if (dropEvent != null)
		{
			out.add("dropEvent : string = '" + dropEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("dropEvent? : string;");
		}
		if (selectEvent != null)
		{
			out.add("selectEvent : string = '" + selectEvent.getClassCanonicalName() + "';");
		}
		else
		{
			out.add("selectEvent? : string;");
		}
		return out;
	}
	
	@Override
	public void init()
	{
		addAttribute("[options]", "calendarOptions");
		setInvertColonRender(true);
		if (dateClickEvent != null)
		{
			addAttribute("date-click", dateClickEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setSelectable(true);
			getOptions().setDateClick("this.handleDateClick.bind(this)");
		}
		if (eventClickEvent != null)
		{
			addAttribute("event-click", eventClickEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setEventClick("this.handleEventClick.bind(this)");
		}
		if (receiveEvent != null)
		{
			addAttribute("event-receive", receiveEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setEventReceive("this.handleEventReceive.bind(this)");
		}
		if (eventResizeEvent != null)
		{
			addAttribute("event-resize", eventResizeEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setEventResize("this.handleEventResize.bind(this)");
		}
		if (eventDropEvent != null)
		{
			addAttribute("event-drop", eventDropEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setDrop("this.handleEventDrop.bind(this)");
		}
		if (selectEvent != null)
		{
			addAttribute("select", selectEvent.getClassCanonicalName());
			getOptions().setEditable(true);
			getOptions().setSelectable(true);
			getOptions().setSelect("this.handleDateSelect.bind(this)");
		}
		
		if (eventSource != null)
		{
			addAttribute("event-source", eventSource);
		}
		if (externalEventContainerId != null)
		{
			addAttribute("externalEvents", externalEventContainerId);
		}
		
		registerWebSocketListeners();
		
		super.init();
	}
	
	@Override
	public List<String> interfaces()
	{
		return List.of("AfterViewInit");
	}
	
	/**
	 * Returns the options if any is required
	 *
	 * @return
	 */
	@Override
	public FullCalendarOptions getOptions()
	{
		if (options == null)
		{
			options = new FullCalendarOptions();
		}
		return options;
	}
	
	public FullCalendarDateClickEvent getDateClickEvent()
	{
		return dateClickEvent;
	}
	
	public J setDateClickEvent(FullCalendarDateClickEvent dateClickEvent)
	{
		this.dateClickEvent = dateClickEvent;
		return (J) this;
	}
	
	public J setOptions(FullCalendarOptions options)
	{
		this.options = options;
		return (J) this;
	}
	
	public String getEventSource()
	{
		return eventSource;
	}
	
	public J setEventSource(String eventSource)
	{
		this.eventSource = eventSource;
		return (J) this;
	}
	
	public String getExternalEventContainerId()
	{
		return externalEventContainerId;
	}
	
	public J setExternalEventContainerId(String externalEventContainerId)
	{
		this.externalEventContainerId = externalEventContainerId;
		return (J) this;
	}
	
	public FullCalendarEventClickEvent getEventClickEvent()
	{
		return eventClickEvent;
	}
	
	public J setEventClickEvent(FullCalendarEventClickEvent eventClickEvent)
	{
		this.eventClickEvent = eventClickEvent;
		return (J) this;
	}
	
	public FullCalendarEventReceiveEvent getReceiveEvent()
	{
		return receiveEvent;
	}
	
	public J setReceiveEvent(FullCalendarEventReceiveEvent receiveEvent)
	{
		this.receiveEvent = receiveEvent;
		return (J) this;
	}
	
	public FullCalendarEventResizeEvent getEventResizeEvent()
	{
		return eventResizeEvent;
	}
	
	public J setEventResizeEvent(FullCalendarEventResizeEvent eventResizeEvent)
	{
		this.eventResizeEvent = eventResizeEvent;
		return (J) this;
	}
	
	public FullCalendarEventDropEvent getEventDropEvent()
	{
		return eventDropEvent;
	}
	
	public J setEventDropEvent(FullCalendarEventDropEvent eventDropEvent)
	{
		this.eventDropEvent = eventDropEvent;
		return (J) this;
	}
	
	public FullCalendarSelectEvent getSelectEvent()
	{
		return selectEvent;
	}
	
	public J setSelectEvent(FullCalendarSelectEvent selectEvent)
	{
		this.selectEvent = selectEvent;
		return (J) this;
	}
	
	public FullCalendarDropEvent getDropEvent()
	{
		return dropEvent;
	}
	
	public J setDropEvent(FullCalendarDropEvent dropEvent)
	{
		this.dropEvent = dropEvent;
		return (J) this;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (getClass().isAssignableFrom(o.getClass()))
		{
			return getID()
					.equals(((FullCalendar<?>) o).getID());
		}
		return false;
	}
	
	public int hashCode()
	{
		return getID().hashCode();
	}
	
	protected String getListenerName()
	{
		return getID();
	}
	
	protected String getListenerNameResources()
	{
		return getID() + "Resources";
	}
	
	
	protected String getListenerNameAdd()
	{
		return getID() + "Add";
	}
	
	protected String getListenerNameEdit()
	{
		return getID() + "Edit";
	}
	
	protected String getListenerNameDelete()
	{
		return getID() + "Delete";
	}
	
	public void pushAdd(FullCalendarEvent event)
	{
	
	}
	
	public void pushEdit(FullCalendarEvent event)
	{
	
	}
	
	public void pushDelete(FullCalendarEvent event)
	{
	
	}
	
	public abstract FullCalendarEventsList getInitialEvents();
	

	
	protected void registerWebSocketListeners()
	{
		if (!GuicedWebSocket.isWebSocketReceiverRegistered(getListenerName()))
		{
			GuicedWebSocket.addWebSocketMessageReceiver(new InitialEventsReceiver(getListenerName(), getClass()));
		}
	}
	
	protected static class InitialEventsReceiver extends WebSocketAbstractCallReceiver
	{
		private String listenerName;
		private Class<? extends FullCalendar> actionClass;
		
		public InitialEventsReceiver()
		{
		}
		
		public InitialEventsReceiver(String listenerName, Class<? extends FullCalendar> actionClass)
		{
			this.listenerName = listenerName;
			this.actionClass = actionClass;
		}
		
		@Override
		public String getMessageDirector()
		{
			return listenerName;
		}
		
		@Override
		public AjaxResponse<?> action(AjaxCall<?> call, AjaxResponse<?> response)
		{
			try
			{
				actionClass = (Class<? extends FullCalendar>) Class.forName(call.getClassName());
				listenerName = call.getUnknownFields()
				                   .get("listenerName")
				                   .toString();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			FullCalendarEventsList initialEvents = GuiceContext.get(actionClass)
			                                                   .getInitialEvents();
			response.addDataResponse(listenerName, initialEvents);
			return response;
		}
	}
	

}
