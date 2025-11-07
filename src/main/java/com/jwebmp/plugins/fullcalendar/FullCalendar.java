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

import com.guicedee.client.IGuiceContext;
import com.guicedee.guicedservlets.websockets.options.IGuicedWebSocket;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.client.DynamicData;
import com.jwebmp.core.base.angular.client.annotations.constructors.NgConstructorBody;
import com.jwebmp.core.base.angular.client.annotations.constructors.NgConstructorParameter;
import com.jwebmp.core.base.angular.client.annotations.functions.NgAfterViewInit;
import com.jwebmp.core.base.angular.client.annotations.functions.NgOnDestroy;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgDataTypeReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportModule;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.annotations.structures.NgField;
import com.jwebmp.core.base.angular.client.annotations.structures.NgInterface;
import com.jwebmp.core.base.angular.client.annotations.structures.NgMethod;
import com.jwebmp.core.base.angular.client.services.EventBusService;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.implementations.WebSocketAbstractCallReceiver;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.plugins.ComponentInformation;
import com.jwebmp.plugins.fullcalendar.events.*;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarEvent;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarEventsList;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarOptions;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarResourceItem;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarResourceItemsList;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


@NgImportReference(value = "CalendarOptions, DateSelectArg, EventClickArg, EventApi, EventDropArg,EventInput,CalendarApi",
        reference = "@fullcalendar/core")

@NgImportReference(value = "FullCalendarComponent", reference = "@fullcalendar/angular")
@NgImportReference(value = "DateClickArg, DropArg, EventReceiveArg, EventResizeDoneArg",
        reference = "@fullcalendar/interaction")
@NgImportReference(value = "FullCalendarModule ", reference = "@fullcalendar/angular")
@NgImportReference(value = "dayGridPlugin", reference = "@fullcalendar/daygrid", wrapValueInBraces = false)
@NgImportReference(value = "scrollGridPlugin", reference = "@fullcalendar/scrollgrid", wrapValueInBraces = false)
@NgImportReference(value = "timeGridPlugin", reference = "@fullcalendar/timegrid", wrapValueInBraces = false)
@NgImportReference(value = "listPlugin", reference = "@fullcalendar/list", wrapValueInBraces = false)
@NgImportReference(value = "interactionPlugin", reference = "@fullcalendar/interaction", wrapValueInBraces = false)

@NgImportReference(value = "bootstrap5Plugin", reference = "@fullcalendar/bootstrap5", wrapValueInBraces = false)

@NgImportReference(value = "ViewChild", reference = "@angular/core")
@NgImportReference(value = "CommonModule", reference = "@angular/common")

@NgDataTypeReference(FullCalendarEventsList.class)
//@NgDataTypeReference(FullCalendarEvent.class)
@NgDataTypeReference(FullCalendarResourceItemsList.class)
@NgDataTypeReference(FullCalendarResourceItem.class)

@NgInterface("OnDestroy")

@NgImportReference(value = "bufferTime, Subscription", reference = "rxjs")

@NgField("currentEvents: EventApi[] = [];")
@NgField("@ViewChild('calendar')\n" +
        "    calendarComponent?: FullCalendarComponent;")
@NgField("private calendarApi? : CalendarApi;")

@NgField("""
        \tprivate handlerGeneralId : string;
            private handlerAddId : string;
            private handlerEditId : string;
            private handlerDeleteId : string;
            private handlerOptionsId : string;
        """)

@NgConstructorBody("""
        \tthis.handlerGeneralId = this.generateHandlerId();
                  this.handlerAddId = this.generateHandlerId();
                  this.handlerEditId = this.generateHandlerId();
                  this.handlerDeleteId = this.generateHandlerId();
                  this.handlerOptionsId = this.generateHandlerId();
        """)

//@NgAfterViewInit("this.calendarApi = this.calendarComponent?.getApi();")
//@NgAfterViewInit("let currentDate = this.calendarApi?.view.currentStart;")
//@NgAfterViewInit("this.fetchData();")

@NgAfterViewInit("""
        \t\tthis.initializeEventListeners();
                this.initializeCalendarApi();
                this.fetchData();
        """)

@NgImportReference(value = "v4 as uuidv4", reference = "uuid")
@NgMethod("""
        private generateHandlerId(): string {
            return `${this.listenerName}-${uuidv4()}`;
        }
        """)

@NgMethod("""
        \t
            /**
             * Initialize FullCalendar API
             */
            private initializeCalendarApi(): void {
                this.calendarApi = this.calendarComponent?.getApi();
                if (this.calendarApi) {
                    console.log('[FullCalendar] Calendar API initialized');
                } else {
                    console.error('[FullCalendar] Calendar API could not be initialized.');
                }
            }
        """)
@NgMethod("""
        \t/**
               * Initialize EventBus listeners
               */
              private initializeEventListeners(): void {
                  // General listener
                  const generalObserver = {
                      next: (data: any) => this.handleSessionEvents(data),
                      error: (err: any) =>
                          console.error(`[FullCalendar] Error in general listener:`, err),
                      complete: () =>
                          console.log('[FullCalendar] General listener completed'),
                  };
                  this.subscriptionGeneral = this.eventBusService
                      .listen(this.listenerName, this.handlerGeneralId)
                      .subscribe(generalObserver);
        
                  // Add events listener
                  const addObserver = {
                      next: (data: any) => this.handleAddEvent(data),
                      error: (err: any) =>
                          console.error(`[FullCalendar] Error in add listener:`, err),
                      complete: () =>
                          console.log('[FullCalendar] Add listener completed'),
                  };
                  this.subscriptionAdd = this.eventBusService
                      .listen(`${this.listenerName}Add`, this.handlerAddId)
                      .subscribe(addObserver);
        
                  // Edit events listener
                  const editObserver = {
                      next: (data: any) => this.handleEditEvent(data),
                      error: (err: any) =>
                          console.error(`[FullCalendar] Error in edit listener:`, err),
                      complete: () =>
                          console.log('[FullCalendar] Edit listener completed'),
                  };
                  this.subscriptionEdit = this.eventBusService
                      .listen(`${this.listenerName}Edit`, this.handlerEditId)
                      .subscribe(editObserver);
        
                  // Delete events listener
                  const deleteObserver = {
                      next: (data: any) => this.handleDeleteEvent(data),
                      error: (err: any) =>
                          console.error(`[FullCalendar] Error in delete listener:`, err),
                      complete: () =>
                          console.log('[FullCalendar] Delete listener completed'),
                  };
                  this.subscriptionDelete = this.eventBusService
                      .listen(`${this.listenerName}Delete`, this.handlerDeleteId)
                      .subscribe(deleteObserver);
        
                  // Options listener
                  const optionsObserver = {
                      next: (options: CalendarOptions) =>
                          this.updateCalendarOptions(options),
                      error: (err: any) =>
                          console.error(`[FullCalendar] Error in options listener:`, err),
                      complete: () =>
                          console.log('[FullCalendar] Options listener completed'),
                  };
                  this.subscriptionOptions = this.eventBusService
                      .listen(`${this.listenerName}Options`, this.handlerOptionsId)
                      .subscribe(optionsObserver);
              }
        
        """)


@NgMethod("\thandleWeekendsToggle() {\n" +
        "    const { calendarOptions } = this;\n" +
        "    calendarOptions.weekends = !calendarOptions.weekends;\n" +
        "  }")

@NgMethod("""
            /**
             * Update calendar options dynamically.
             */
            private updateCalendarOptions(options: CalendarOptions): void {
                if(typeof options === 'string')
                {
                    options = JSON.parse(options);
                }
                this.calendarOptions = {
                    ...this.calendarOptions,
                    ...options
                };
                console.log('[FullCalendar] Updating calendar options:', this.calendarOptions);
            }
        """)

@NgMethod("handleDateClick(selectInfo: DateClickArg) {\n" +
        //   "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.dateClickEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.dateClickEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "\t}")

@NgMethod("handleDateSelect(selectInfo: DateSelectArg) {\n" +
        //  "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.selectEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.selectEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "\t}")


@NgMethod("handleEventClick(selectInfo: EventClickArg) {\n" +
        //     "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.eventClickEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.eventClickEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "  }")

@NgMethod("handleDropEvent(selectInfo: DropArg) {\n" +
        //    "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.dropEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.dropEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "  }")

@NgMethod("handleEventDrop(selectInfo: EventDropArg) {\n" +
        //     "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.eventDropEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.eventDropEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "  }")

@NgMethod("handleEventReceive(selectInfo: EventReceiveArg) {\n" +
        //         "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.receiveEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.receiveEvent},'onClick',undefined,undefined);\n" +
        "  }")

@NgMethod("handleEventResize(selectInfo: EventResizeDoneArg) {\n" +
        //     "\t\tconst calendarApi = selectInfo.view.calendar;\n" +
        "\t\tthis.calendarApi?.unselect(); // clear date selection\n" +
        "\t\tif(this.eventResizeEvent)\n" +
        "\t\tthis.eventBusService.send('ajax',{infoObj : selectInfo,eventClass : this.eventResizeEvent},'onClick',selectInfo.jsEvent,undefined);\n" +
        "  }")


@NgMethod("handleEvents(events: EventApi[]) {\n" +
        "    this.currentEvents = events;\n" +
        "  }")

@NgMethod("""
        \t/**
               * Handle general session events.
               */
              private handleSessionEvents(data: any): void {
                  console.log('[FullCalendar] Received general session events data:', data);
                  this.calendarApi?.removeAllEventSources();
                  if(typeof data === 'string')
                  {
                    this.calendarApi?.addEventSource(JSON.parse(data));
                  }else {
                    this.calendarApi?.addEventSource(data);
                  }
              }
        """)
@NgMethod("""
        \t/**
               * Handle "add" events.
               */
              private handleAddEvent(data: any): void {
                  console.log('[FullCalendar] Received add event data:', data);
                  let ev : EventInput = JSON.parse(data);
                  this.calendarApi?.addEvent(ev);
              }
        """)
@NgMethod("""
        \t/**
               * Handle "edit" events.
               */
              private handleEditEvent(data: any): void {
                  console.log('[FullCalendar] Received edit event data:', data);
                  let ev : EventInput = JSON.parse(data);
                  this.calendarApi?.getEventById(ev.id!)?.remove();
                  this.calendarApi?.addEvent(ev);
              }
        """)
@NgMethod("""
        \t/**
               * Handle "delete" events.
               */
              private handleDeleteEvent(data: any): void {
                  console.log('[FullCalendar] Received delete event data:', data);
                  let ev : EventInput = JSON.parse(data);
                  this.calendarApi?.getEventById(ev.id!)?.remove();
              }
        """)

@NgComponentReference(EventBusService.class)
//@NgImportReference(value = "inject", reference = "@angular/core")
//@NgField(value = "private readonly eventBusService = inject(EventBusService); // Injected EventBus service.")

@NgComponentReference(DynamicData.class)

@NgOnDestroy("""
                console.log('Cleaning up all event listeners and subscriptions.');
        
                // Unsubscribe to avoid memory leaks
                if (this.subscriptionGeneral) this.subscriptionGeneral.unsubscribe();
                if (this.subscriptionAdd) this.subscriptionAdd.unsubscribe();
                if (this.subscriptionEdit) this.subscriptionEdit.unsubscribe();
                if (this.subscriptionDelete) this.subscriptionDelete.unsubscribe();
                if (this.subscriptionOptions) this.subscriptionOptions.unsubscribe();
        
                // Unregister each listener
                this.eventBusService.unregisterListener(this.listenerName,this.handlerGeneralId);
                this.eventBusService.unregisterListener(`${this.listenerName}Add`,this.handlerAddId);
                this.eventBusService.unregisterListener(`${this.listenerName}Edit`,this.handlerEditId);
                this.eventBusService.unregisterListener(`${this.listenerName}Delete`,this.handlerDeleteId);
                this.eventBusService.unregisterListener(`${this.listenerName}Options`,this.handlerOptionsId);
        """)
@NgImportModule("FullCalendarModule")
@NgImportModule("CommonModule")
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

    // Template flags (opt-in). All default to false so no ng-template markup is emitted unless enabled explicitly.
    private boolean enableEventContentTemplate;
    private boolean enableDayHeaderTemplate;
    private boolean enableDayCellTemplate;
    private boolean enableWeekNumberTemplate;
    private boolean enableMoreLinkTemplate;
    private boolean enableNoEventsTemplate;
    private boolean enableSlotLabelTemplate;
    private boolean enableListDayHeaderTemplate;

    public boolean isEnableEventContentTemplate() {return enableEventContentTemplate;}

    public boolean isEnableDayHeaderTemplate() {return enableDayHeaderTemplate;}

    public boolean isEnableDayCellTemplate() {return enableDayCellTemplate;}

    public boolean isEnableWeekNumberTemplate() {return enableWeekNumberTemplate;}

    public boolean isEnableMoreLinkTemplate() {return enableMoreLinkTemplate;}

    public boolean isEnableNoEventsTemplate() {return enableNoEventsTemplate;}

    public boolean isEnableSlotLabelTemplate() {return enableSlotLabelTemplate;}

    public boolean isEnableListDayHeaderTemplate() {return enableListDayHeaderTemplate;}

    @SuppressWarnings("unchecked")
    public J setEnableEventContentTemplate(boolean enable)
    {
        this.enableEventContentTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableDayHeaderTemplate(boolean enable)
    {
        this.enableDayHeaderTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableDayCellTemplate(boolean enable)
    {
        this.enableDayCellTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableWeekNumberTemplate(boolean enable)
    {
        this.enableWeekNumberTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableMoreLinkTemplate(boolean enable)
    {
        this.enableMoreLinkTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableNoEventsTemplate(boolean enable)
    {
        this.enableNoEventsTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableSlotLabelTemplate(boolean enable)
    {
        this.enableSlotLabelTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableListDayHeaderTemplate(boolean enable)
    {
        this.enableListDayHeaderTemplate = enable;
        return (J) this;
    }

    /**
     * Convenience to enable all base templates (restores previous default behavior).
     */
    @SuppressWarnings("unchecked")
    public J enableAllBaseTemplates()
    {
        this.enableEventContentTemplate = true;
        this.enableDayHeaderTemplate = true;
        this.enableDayCellTemplate = true;
        this.enableWeekNumberTemplate = true;
        this.enableMoreLinkTemplate = true;
        this.enableNoEventsTemplate = true;
        this.enableSlotLabelTemplate = true;
        this.enableListDayHeaderTemplate = true;
        return (J) this;
    }

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
    public List<String> constructorBody()
    {
        List<String> bodies = INgComponent.super.constructorBody();
       /* bodies.add("this.subscription = this.eventBusService.listen(this.listenerName).subscribe((message: any) => {\n" +

                "            if(message)" +
                "            try {\n" +
                "                 this.calendarApi?.removeAllEvents();\n" +
                "//                alert('events message - ' + JSON.stringify(message));\n" +
                "                let workable = false;\n" +
                "                if (Array.isArray(message)) {\n" +
                "                    workable = true;\n" +
                "                } else {\n" +
                "                    if (message.out && message.out[0]) {\n" +
                "                        message = message.out[0];\n" +
                "                        workable = true;\n" +
                "                    }\n" +
                "                }\n" +
                "                if(workable) {\n" +
                " //                   alert('working on events message - ' + JSON.stringify(message));\n" +
                "                    this.calendarApi?.addEventSource(message);\n" +
                "                }\n" +
                "            } catch (e) {\n" +
                "                console.log(\"error in events\", e);\n" +
                "            }\n" +
                "        });\n");

        bodies.add("this.subscriptionAdd = this.eventBusService.listen(this.listenerName + 'Add')\n" +
                "            .subscribe((message: DynamicData) => {\n" +
                "                if(message && message.out && message.out[0])\n" +
                "                {\n" +
                "                    let ev : EventInput = message.out[0];\n" +
                "                    this.calendarApi?.addEvent(ev);\n" +
                "                }\n" +
                "            });\n");

        bodies.add("this.subscriptionEdit = this.eventBusService.listen(this.listenerName + 'Edit')\n" +
                "            .subscribe((message: DynamicData) => {\n" +
                "                if(message && message.out && message.out[0])\n" +
                "                {\n" +
                "                    let ev : EventInput = message.out[0];\n" +
                "                    this.calendarApi?.getEventById(ev.id!)?.remove();\n" +
                "                    this.calendarApi?.addEvent(ev);\n" +
                "                }\n" +
                "                \n" +
                "            });\n");

        bodies.add("this.subscriptionDelete = this.eventBusService.listen(this.listenerName + 'Delete')\n" +
                "            .subscribe((message: DynamicData) => {\n" +
                "                if(message && message.out && message.out[0])\n" +
                "                {\n" +
                "                    let ev : EventInput = message.out[0];\n" +
                "                    this.calendarApi?.getEventById(ev.id!)?.remove();\n" +
                "                }\n" +
                "            });\n");

        bodies.add("this.subscriptionOptions = this.eventBusService.listen(this.listenerName + 'Options')\n" +
                "            .subscribe((message: any) => {\n" +
                "                //alert('incoming message ' + JSON.stringify(message));\n" +
                "                if (message && message.out && message.out[0]) {\n" +
                "                  //  alert('options message - ' + JSON.stringify(message.out[0]));\n" +
                "                  //  this.calendarOptions = {...this.calendarOptionsOriginal,...message.out[0]}\n" +
                "                 //    alert('options message - ' + JSON.stringify(this.calendarOptions));\n" +
                "                }\n" +
                "            });\n");*/
        return bodies;
    }


    public List<String> methods()
    {
        List<String> methods = INgComponent.super.methods();
        methods.add("fetchData(){\n" +
                "this.eventBusService.send(this.listenerName + 'Options', {\n" +
                "            className: this.clazzName,\n" +
                "            listenerName: this.listenerName + 'Options'\n" +
                "        }, this.listenerName + 'Options');" +
                "" +
                "" +
                "" +
                "   this.eventBusService.send(this.listenerName,{\n" +
                "           className :  this.clazzName,\n" +
                "            listenerName: this.listenerName},this.listenerName);\n" +
                "}\n");

/*        methods.add(" ngAfterContentChecked(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterContentInit(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterViewChecked(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterViewInit(): void {\n" +
                "       this.calendarApi = this.calendarComponent?.getApi();" +
                "       this.fetchData();" +
                "    }\n" +
                "\n" +
                "    ngOnDestroy(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngOnInit(): void {\n" +
                "    }");*/
        return methods;
    }


    @Override
    public List<String> fields()
    {
        List<String> out = INgComponent.super.fields();
        out.add(" private listenerName = '" + getID() + "';");
        out.add(" private clazzName = '" + getClass().getCanonicalName() + "';");
        out.add(" private subscriptionGeneral? : Subscription;\n");
        out.add(" private subscriptionAdd? : Subscription;\n");
        out.add(" private subscriptionEdit? : Subscription;\n");
        out.add(" private subscriptionDelete? : Subscription;\n");
        out.add(" private subscriptionOptions? : Subscription;\n");

        out.add("calendarOptionsOriginal: CalendarOptions = " + getOptions().toJson() + ";");

        out.add("calendarOptions: CalendarOptions = {...this.calendarOptionsOriginal};");

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
    protected void init()
    {
        addAttribute("[options]", "calendarOptions");
        setInvertColonRender(true);
        if (dateClickEvent != null && getOptions().getDateClick() == null)
        {
            //\t\taddAttribute("date-click", dateClickEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            if (getOptions().getSelectable() == null)
            {
                getOptions().setSelectable(true);
            }
            getOptions().setDateClick("this.handleDateClick.bind(this)");
        }
        if (eventClickEvent != null && getOptions().getEventClick() == null)
        {
            //\t\taddAttribute("event-click", eventClickEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            getOptions().setEventClick("this.handleEventClick.bind(this)");
        }
        if (receiveEvent != null && getOptions().getEventReceive() == null)
        {
            //\t\taddAttribute("event-receive", receiveEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            getOptions().setEventReceive("this.handleEventReceive.bind(this)");
        }
        if (eventResizeEvent != null && getOptions().getEventResize() == null)
        {
            //\taddAttribute("event-resize", eventResizeEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            getOptions().setEventResize("this.handleEventResize.bind(this)");
        }
        if (eventDropEvent != null && getOptions().getEventDrop() == null)
        {
            //\taddAttribute("event-drop-event", eventDropEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            getOptions().setEventDrop("this.handleEventDrop.bind(this)");
        }
        if (dropEvent != null && getOptions().getDrop() == null)
        {
            //\taddAttribute("event-drop", eventDropEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            getOptions().setDrop("this.handleDropEvent.bind(this)");
        }
        if (selectEvent != null && getOptions().getSelect() == null)
        {
            //\taddAttribute("select", selectEvent.getClassCanonicalName());
            if (getOptions().getEditable() == null)
            {
                getOptions().setEditable(true);
            }
            if (getOptions().getSelectable() == null)
            {
                getOptions().setSelectable(true);
            }
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

        // Inject Angular template slots (content-injection areas)
        try
        {
            if (enableEventContentTemplate)
            {
                NgTemplateElement eventContent = new NgTemplateElement("eventContent").withLetArg();
                eventContent.add("<span class=\"fc-tpl fc-event\">{{ arg?.timeText }} {{ arg?.event?.title }}</span>");
                super.add(eventContent);
            }

            if (enableDayHeaderTemplate)
            {
                NgTemplateElement dayHeaderContent = new NgTemplateElement("dayHeaderContent").withLetArg();
                dayHeaderContent.add("<span class=\"fc-tpl fc-day-header\">{{ arg?.text }}</span>");
                super.add(dayHeaderContent);
            }

            if (enableDayCellTemplate)
            {
                NgTemplateElement dayCellContent = new NgTemplateElement("dayCellContent").withLetArg();
                dayCellContent.add("<span class=\"fc-tpl fc-day-cell\">{{ arg?.date | date:'yyyy-MM-dd' }}</span>");
                super.add(dayCellContent);
            }

            if (enableWeekNumberTemplate)
            {
                NgTemplateElement weekNumberContent = new NgTemplateElement("weekNumberContent").withLetArg();
                weekNumberContent.add("<span class=\"fc-tpl fc-week-num\">W{{ arg?.num }}</span>");
                super.add(weekNumberContent);
            }

            if (enableMoreLinkTemplate)
            {
                NgTemplateElement moreLinkContent = new NgTemplateElement("moreLinkContent").withLetArg();
                moreLinkContent.add("<span class=\"fc-tpl fc-more-link\">+{{ arg?.num }} more</span>");
                super.add(moreLinkContent);
            }

            if (enableNoEventsTemplate)
            {
                NgTemplateElement noEventsContent = new NgTemplateElement("noEventsContent").withLetArg();
                noEventsContent.add("<em class=\"fc-tpl fc-no-events\">No events to display</em>");
                super.add(noEventsContent);
            }

            if (enableSlotLabelTemplate)
            {
                NgTemplateElement slotLabelContent = new NgTemplateElement("slotLabelContent").withLetArg();
                slotLabelContent.add("<span class=\"fc-tpl fc-slot-label\">{{ arg?.text }}</span>");
                super.add(slotLabelContent);
            }

            if (enableListDayHeaderTemplate)
            {
                NgTemplateElement listDayHeaderContent = new NgTemplateElement("listDayHeaderContent").withLetArg();
                listDayHeaderContent.add("<span class=\"fc-tpl fc-list-day-header\">{{ arg?.text }}</span>");
                super.add(listDayHeaderContent);
            }
        }
        catch (Exception ignored)
        {
            // Defensive: generation-only path; ignore to avoid impacting server runtime
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

    protected String getListenerNameOnLoadOptions()
    {
        return getID() + "Options";
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


    /**
     * Override to provide the initial events
     *
     * @return
     */
    public @NotNull FullCalendarEventsList getInitialEvents()
    {
        return new FullCalendarEventsList();
    }

    public FullCalendarOptions getOnLoadOptions()
    {
        return getOptions();
    }


    protected void registerWebSocketListeners()
    {
        if (!IGuicedWebSocket.isWebSocketReceiverRegistered(getListenerName()))
        {
            IGuicedWebSocket.addWebSocketMessageReceiver(new InitialEventsReceiver(getListenerName(), getClass()));
        }
        if (!IGuicedWebSocket.isWebSocketReceiverRegistered(getListenerNameOnLoadOptions()))
        {
            IGuicedWebSocket.addWebSocketMessageReceiver(new OnLoadOptionsReceiver(getListenerNameOnLoadOptions(), getClass()));
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
            FullCalendarEventsList initialEvents = IGuiceContext.get(actionClass)
                                                                .getInitialEvents();
            if (initialEvents == null)
            {
                return null;
            }
            response.addDataResponse(listenerName, initialEvents);
            return response;
        }
    }

    protected static class OnLoadOptionsReceiver extends WebSocketAbstractCallReceiver
    {
        private String listenerName;
        private Class<? extends FullCalendar> actionClass;

        public OnLoadOptionsReceiver()
        {
        }

        public OnLoadOptionsReceiver(String listenerName, Class<? extends FullCalendar> actionClass)
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
            FullCalendarOptions initialEvents = IGuiceContext.get(actionClass)
                                                             .getOnLoadOptions();
            if (initialEvents == null)
            {
                return null;
            }
            response.addDataResponse(listenerName, initialEvents);
            return response;
        }
    }


}
