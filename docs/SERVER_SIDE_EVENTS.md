# Server-Side Events & Angular Templates – Complete Guide

**Date**: December 4, 2025  
**Package**: `com.jwebmp.plugins.fullcalendar.events`  
**Framework**: JWebMP with GuicedEE Client + Angular 12-20  
**FullCalendar Version**: v6.1.19

---

## Overview

JWebMP FullCalendar provides two powerful mechanisms for customization:

### 1. Server-Side Event Handlers
Respond to user interactions on the calendar (clicks, selections, drags, drops) through the `com.jwebmp.plugins.fullcalendar.events` package.

- **Respond to calendar interactions** (clicks, selections, drags, drops)
- **Access detailed event data** with automatic deserialization
- **Return responses directly** to the client
- **Manage large datasets** through specialized listeners
- **Handle resource assignments** (when using resource scheduler)

### 2. Angular ng-template Support
Customize calendar rendering using Angular templates, allowing dynamic content injection with Angular syntax and components.

- **Custom event content** rendering with Angular interpolation
- **Custom day headers, cells, week numbers, more links**
- **Custom slot labels and time headers** (in time-grid views)
- **Full access to Angular features** (pipes, directives, component content)
- **Direct template manipulation** from Java backend

All event handlers extend JWebMP's `ClickAdapter` and use **Reactive programming** (Smallrye Mutiny) for async handling.

**Templates are only rendered when explicitly created and added** - no sensible defaults are auto-generated. You control exactly which templates override FullCalendar's rendering.

**Server-side handlers and templates are independent**: register server handlers via `setEventClickEvent(...)`, `setEventDropEvent(...)`, `setSelectEvent(...)`, `setDateClickEvent(...)`, `setEventResizeEvent(...)`, etc. Enabling or adding templates is optional and does not affect server handler registration.

---

## Angular Template System

### Available Templates

JWebMP FullCalendar supports 8 customizable Angular `ng-template` slots, enumerated in `NgTemplateSlot`:

| Enum Constant | Template Name | Purpose |
|---------------|---------------|---------|
| `EVENT_CONTENT` | `eventContent` | Event rendering |
| `DAY_HEADER_CONTENT` | `dayHeaderContent` | Day header customization |
| `DAY_CELL_CONTENT` | `dayCellContent` | Date cell customization |
| `WEEK_NUMBER_CONTENT` | `weekNumberContent` | Week number format |
| `MORE_LINK_CONTENT` | `moreLinkContent` | Overflow indicator |
| `NO_EVENTS_CONTENT` | `noEventsContent` | Empty state messaging |
| `SLOT_LABEL_CONTENT` | `slotLabelContent` | Time slot labels |
| `LIST_DAY_HEADER_CONTENT` | `listDayHeaderContent` | List view headers |

**Important**: Templates are **NOT auto-generated**. You must explicitly create and add `NgTemplateElement` instances to customize rendering. The enable flags only indicate template support is available.

**Type Safety**: Use the `NgTemplateSlot` enum when creating templates (preferred) for compile-time safety and IDE auto-completion.

### Template Context (arg Parameter)

Each template receives an `arg` object containing context-specific data:

**eventContent template**:
```typescript
arg: {
  timeText: string;        // "9:00 AM" or similar
  event: {
    id: string;
    title: string;
    start: Date;
    end: Date;
    extendedProps: any;    // Custom properties
    // ... all FullCalendar event properties
  }
}
```

**dayHeaderContent template**:
```typescript
arg: {
  text: string;            // "Mon", "Tue", etc.
  date: Date;              // Date object for the day
}
```

**dayCellContent template**:
```typescript
arg: {
  date: Date;              // Date object
}
```

**weekNumberContent template**:
```typescript
arg: {
  num: number;             // Week number
  date: Date;              // Date in the week
}
```

**moreLinkContent template**:
```typescript
arg: {
  num: number;             // Count of hidden events
  date: Date;              // Date with overflow
}
```

**slotLabelContent template**:
```typescript
arg: {
  text: string;            // Time text (e.g., "9:00 AM")
  time: Date;              // Time as date object
}
```

### Enabling Templates

The enable flags indicate that your component supports custom templates. They do **NOT** auto-generate default templates. You must explicitly create and add `NgTemplateElement` instances:

**Enable template support** (allows custom templates to render):
```java
FullCalendar calendar = new FullCalendar<>("calendar");

// Enable specific template slots
calendar.setEnableEventContentTemplate(true);
calendar.setEnableDayCellTemplate(true);
calendar.setEnableMoreLinkTemplate(true);

// Or enable all template slots at once
calendar.enableAllBaseTemplates();
```

**Add custom templates** (required - no defaults auto-generated):
```java
// Using NgTemplateSlot enum (PREFERRED - type safe)
NgTemplateElement eventTemplate = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
    .withLetArg()
    .add("<div class=\"custom-event\">" +
         "  <strong>{{ arg?.event?.title }}</strong>" +
         "  <small>{{ arg?.timeText }}</small>" +
         "</div>");

calendar.add(eventTemplate);  // Must explicitly add!
```

**Or using string directly** (when enum is not available):
```java
NgTemplateElement eventTemplate = new NgTemplateElement("eventContent")
    .withLetArg()
    .add("<div class=\"custom-event\">" +
         "  <strong>{{ arg?.event?.title }}</strong>" +
         "  <small>{{ arg?.timeText }}</small>" +
         "</div>");

calendar.add(eventTemplate);  // Must explicitly add!
```

**Without explicit template additions**, FullCalendar uses its default rendering (no ng-template is rendered).

### Custom Template Content

After enabling templates, customize their content by accessing the `NgTemplateElement`. Use the `NgTemplateSlot` enum for type safety:

```java
FullCalendar calendar = new FullCalendar<>("calendar");
calendar.setEnableEventContentTemplate(true);

// Create custom template using NgTemplateSlot enum
NgTemplateElement eventContent = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
    .withLetArg()
    .add("<div class=\"custom-event\">" +
         "  <strong>{{ arg?.event?.title }}</strong>" +
         "  <br/>" +
         "  <small>{{ arg?.timeText }}</small>" +
         "  <span *ngIf=\"arg?.event?.extendedProps?.location\" " +
         "        class=\"badge\">📍 {{ arg?.event?.extendedProps?.location }}</span>" +
         "</div>");

calendar.add(eventContent);
```

**Available Enum Constants**:
- `NgTemplateSlot.EVENT_CONTENT` - Event rendering
- `NgTemplateSlot.DAY_HEADER_CONTENT` - Day headers
- `NgTemplateSlot.DAY_CELL_CONTENT` - Date cells
- `NgTemplateSlot.WEEK_NUMBER_CONTENT` - Week numbers
- `NgTemplateSlot.MORE_LINK_CONTENT` - Overflow indicator
- `NgTemplateSlot.NO_EVENTS_CONTENT` - Empty state
- `NgTemplateSlot.SLOT_LABEL_CONTENT` - Time labels
- `NgTemplateSlot.LIST_DAY_HEADER_CONTENT` - List headers

### Working with Template Data

All templates have access to Angular features. Use `NgTemplateSlot` enum for clarity:

**Using Angular pipes**:
```java
// Using NgTemplateSlot enum for type safety
NgTemplateElement eventTemplate = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
    .withLetArg()
    .add("<span>" +
         "  {{ arg?.event?.title | uppercase }}" +
         "  <br/>" +
         "  {{ arg?.event?.start | date:'short' }}" +
         "</span>");

calendar.add(eventTemplate);
```

**Using Angular directives**:
```java
NgTemplateElement dayCellTemplate = new NgTemplateElement(NgTemplateSlot.DAY_CELL_CONTENT)
    .withLetArg()
    .add("<div [class.weekend]=\"isWeekend(arg?.date)\">" +
         "  {{ arg?.date | date:'d' }}" +
         "</div>");

calendar.add(dayCellTemplate);
```

**Using Angular components** (must be declared in module):
```java
NgTemplateElement eventTemplate = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
    .withLetArg()
    .add("<app-event-renderer [event]=\"arg?.event\"></app-event-renderer>");

calendar.add(eventTemplate);
```

### Default Template Fallback

When templates are **not explicitly added**:
- FullCalendar uses its **standard HTML rendering**
- No ng-template is rendered
- FullCalendar controls the HTML output

When templates **are explicitly added** (via NgTemplateElement):
- Your template **replaces** FullCalendar's default rendering
- You have full control over the HTML output
- Angular processes your template syntax

**Example comparison**:
```java
// WITHOUT explicit template - FullCalendar default rendering
FullCalendar calendar = new FullCalendar<>("calendar");
calendar.setEnableEventContentTemplate(true);  // Just enables flag, no actual template added
// Result: FullCalendar renders events with its standard HTML

// WITH explicit template - Custom rendering
FullCalendar calendar = new FullCalendar<>("calendar");
calendar.setEnableEventContentTemplate(true);
calendar.add(new NgTemplateElement("eventContent")
    .withLetArg()
    .add("<div>{{ arg?.event?.title }}</div>"));
// Result: Your template is rendered instead of FullCalendar default
```

---

## Event Types & Use Cases

### 1. Event Click Events

#### **FullCalendarEventClickEvent**

Triggered when a user clicks on an event in the calendar.

**Use Cases**:
- Open event detail modal
- Load event data for editing
- Show event information sidebar
- Track user interactions

**Code Example**:
```java
public class MyEventClickHandler extends FullCalendarEventClickEvent {
    @Override
    public void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, 
                            FullCalendarEventInfo event) {
        // Access event information
        String eventId = event.getId();
        LocalDateTime start = event.getStart();
        LocalDateTime end = event.getEnd();
        Boolean allDay = event.getAllDay();
        
        // Access resource if using resource scheduler
        FullCalendarEventResourceInfo resource = event.getResource();
        
        // Send response back to client
        response.setResponseBody(Map.of(
            "eventId", eventId,
            "title", "Event Details",
            "message", "Event clicked successfully"
        ));
    }
}
```

**FullCalendarEventInfo Properties**:
| Property | Type | Description |
|----------|------|-------------|
| `id` | String | Unique event identifier |
| `start` | LocalDateTime | Event start time (parsed from startStr) |
| `end` | LocalDateTime | Event end time (parsed from endStr) |
| `startStr` | String | Raw start time string from client |
| `endStr` | String | Raw end time string from client |
| `allDay` | Boolean | Whether event is all-day |
| `resource` | FullCalendarEventResourceInfo | Associated resource (if resource scheduler enabled) |

---

### 2. Date Click Events

#### **FullCalendarDateClickEvent**

Triggered when a user clicks on a date/time in the calendar (not on an event).

**Use Cases**:
- Create new event at clicked date/time
- Populate date picker in form
- Show date information
- Schedule new appointments

**Code Example**:
```java
public class MyDateClickHandler extends FullCalendarDateClickEvent {
    @Override
    public void onDateClick(AjaxCall<?> call, AjaxResponse<?> response, 
                           FullCalendarDateClickEventInfo dateInfo) {
        // Access date information
        LocalDateTime clickedDate = dateInfo.getDate();
        Boolean allDay = dateInfo.getAllDay();
        
        // Access resource if applicable
        FullCalendarEventResourceInfo resource = dateInfo.getResource();
        
        // Prepare new event creation
        response.setResponseBody(Map.of(
            "action", "create",
            "date", clickedDate.toString(),
            "allDay", allDay
        ));
    }
}
```

**FullCalendarDateClickEventInfo Properties**:
| Property | Type | Description |
|----------|------|-------------|
| `date` | LocalDateTime | Clicked date/time (parsed from dateStr) |
| `dateStr` | String | Raw date string from client |
| `allDay` | Boolean | Whether clicked area is all-day section |
| `resource` | FullCalendarEventResourceInfo | Associated resource (if applicable) |

---

### 3. Event Drag & Drop Events

#### **FullCalendarEventDropEvent**

Triggered when an event is **dragged and dropped** to a new date/time.

**Use Cases**:
- Update event times in database
- Validate move is allowed (check conflicts)
- Update UI with confirmation
- Log user actions
- Update dependent tasks

**Code Example**:
```java
public class MyEventDropHandler extends FullCalendarEventDropEvent {
    @Override
    public void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response,
                           FullCalendarResourceItem oldResource, 
                           FullCalendarResourceItem newResource,
                           FullCalendarEventInfo oldEvent, 
                           FullCalendarEventInfo newEvent) {
        
        // Compare old vs new state
        LocalDateTime oldStart = oldEvent.getStart();
        LocalDateTime newStart = newEvent.getStart();
        LocalDateTime oldEnd = oldEvent.getEnd();
        LocalDateTime newEnd = newEvent.getEnd();
        
        String eventId = newEvent.getId();
        
        // Check resource change (if using resource scheduler)
        String oldResourceId = oldResource != null ? oldResource.getId() : null;
        String newResourceId = newResource != null ? newResource.getId() : null;
        
        try {
            // Update database
            updateEventInDatabase(eventId, newStart, newEnd, newResourceId);
            
            response.setResponseBody(Map.of(
                "success", true,
                "message", "Event updated successfully"
            ));
        } catch (Exception e) {
            // Revert the change
            response.setResponseBody(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
}
```

**Parameters**:
- `oldResource`: Previous resource assignment (null if no resource or unchanged)
- `newResource`: New resource assignment (null if no resource or unchanged)
- `oldEvent`: Event state before drag
- `newEvent`: Event state after drag

---

#### **FullCalendarEventReceiveEvent**

Triggered when an event is **dragged and dropped onto the calendar from external source**.

**Use Cases**:
- Accept external items (from sidebar, other apps)
- Create new events from templates
- Validate external drops
- Track external event creation

**Code Example**:
```java
public class MyEventReceiveHandler extends FullCalendarEventReceiveEvent {
    @Override
    public void onEventReceive(AjaxCall<?> call, AjaxResponse<?> response, 
                              FullCalendarEventInfo event) {
        // The dropped event data
        String eventId = event.getId();
        LocalDateTime start = event.getStart();
        LocalDateTime end = event.getEnd();
        
        // Create new event in database
        createNewEvent(eventId, start, end);
        
        response.setResponseBody(Map.of(
            "success", true,
            "message", "External event accepted and created"
        ));
    }
}
```

---

#### **FullCalendarDropEvent**

Triggered when an **external item is dropped on the calendar**.

**Use Cases**:
- Handle generic drops from any source
- Create events from various external sources
- Log drop operations

**Code Example**:
```java
public class MyDropHandler extends FullCalendarDropEvent {
    @Override
    public void onDrop(AjaxCall<?> call, AjaxResponse<?> response, 
                      FullCalendarEventInfo event) {
        // Handle generic drop
        LocalDateTime dropDate = event.getStart();
        
        response.setResponseBody(Map.of(
            "action", "dropped",
            "date", dropDate.toString()
        ));
    }
}
```

---

### 4. Event Resize Events

#### **FullCalendarEventResizeEvent**

Triggered when an event is **resized** (duration changed).

**Use Cases**:
- Update event duration in database
- Validate new duration
- Prevent overlaps
- Notify participants of duration changes

**Code Example**:
```java
public class MyEventResizeHandler extends FullCalendarEventResizeEvent {
    @Override
    public void onEventResize(AjaxCall<?> call, AjaxResponse<?> response,
                             FullCalendarEventInfo oldEvent, 
                             FullCalendarEventInfo newEvent) {
        
        // Compare duration
        LocalDateTime oldStart = oldEvent.getStart();
        LocalDateTime oldEnd = oldEvent.getEnd();
        LocalDateTime newStart = newEvent.getStart();
        LocalDateTime newEnd = newEvent.getEnd();
        
        String eventId = newEvent.getId();
        
        // Calculate duration change
        long oldDuration = Duration.between(oldStart, oldEnd).toMinutes();
        long newDuration = Duration.between(newStart, newEnd).toMinutes();
        
        try {
            // Validate constraints
            if (newDuration < 15) {
                throw new IllegalArgumentException("Minimum event duration is 15 minutes");
            }
            
            // Update database
            updateEventDuration(eventId, newStart, newEnd);
            
            response.setResponseBody(Map.of(
                "success", true,
                "oldDuration", oldDuration,
                "newDuration", newDuration
            ));
        } catch (Exception e) {
            response.setResponseBody(Map.of(
                "success", false,
                "error", e.getMessage()
            ));
        }
    }
}
```

**Parameters**:
- `oldEvent`: Event data before resize
- `newEvent`: Event data after resize (called "selectEvent" in method signature)

---

### 5. Selection Events

#### **FullCalendarSelectEvent**

Triggered when a user **selects a date/time range** on the calendar.

**Use Cases**:
- Create new event with selected time range
- Show time slot availability
- Schedule meetings
- Populate form with selected times

**Code Example**:
```java
public class MySelectHandler extends FullCalendarSelectEvent {
    @Override
    public void onSelect(AjaxCall<?> call, AjaxResponse<?> response, 
                        FullCalendarSelectEventInfo selectionInfo) {
        
        // Access selected range
        LocalDateTime start = selectionInfo.getStart();
        LocalDateTime end = selectionInfo.getEnd();
        Boolean allDay = selectionInfo.getAllDay();
        
        // Access resource if applicable
        FullCalendarEventResourceInfo resource = selectionInfo.getResource();
        
        // Calculate duration
        Duration duration = Duration.between(start, end);
        
        response.setResponseBody(Map.of(
            "action", "select",
            "start", start.toString(),
            "end", end.toString(),
            "duration", duration.toMinutes(),
            "allDay", allDay
        ));
    }
}
```

**FullCalendarSelectEventInfo Properties**:
| Property | Type | Description |
|----------|------|-------------|
| `start` | LocalDateTime | Selection start (parsed from startStr) |
| `end` | LocalDateTime | Selection end (parsed from endStr) |
| `startStr` | String | Raw start string from client |
| `endStr` | String | Raw end string from client |
| `allDay` | Boolean | Whether selecting all-day slot |
| `resource` | FullCalendarEventResourceInfo | Associated resource (if applicable) |

---

## Event Handler Architecture

### Base Class: ClickAdapter

All FullCalendar events extend `ClickAdapter<T>` which provides:

```java
public abstract class ClickAdapter<T extends ClickAdapter<T>> {
    // Reactive method that must be implemented
    public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response) {
        // Handle event and return reactive stream
        return Uni.createFrom().voidItem();
    }
}
```

### Event Processing Flow

```
Client (JavaScript)
        ↓ (AJAX Call)
   Listener (ClickAdapter)
        ↓ (onClick() called)
   Event Handler (Your implementation)
        ↓ (Process event data)
   Response Object
        ↓ (Sent back to client)
   Browser (JavaScript callback)
```

### Data Deserialization

The framework automatically:
1. **Receives AJAX call** with event data in `call.getUnknownFields()`
2. **Extracts nested objects** from LinkedHashMap structures
3. **Uses Jackson ObjectMapper** to deserialize to typed objects
4. **Calls `updateDates()`** to parse date strings to LocalDateTime
5. **Invokes your handler method** with typed parameters

---

## Integration with FullCalendar Component

### Registering Event Handlers

Handlers are registered as listeners on the `FullCalendar` component:

```java
FullCalendar calendar = new FullCalendar<>(options);

// Register handlers
calendar.setEventClickEvent(new MyEventClickHandler());
calendar.setEventDropEvent(new MyEventDropHandler());
calendar.setSelectEvent(new MySelectHandler());
calendar.setDateClickEvent(new MyDateClickHandler());
calendar.setEventResizeEvent(new MyEventResizeHandler());
```

Handler registration is **independent of Angular templates**. You can attach server handlers without enabling templates, and adding templates does not change handler wiring.

### Handler Lifecycle

1. **Component Creation**: Register handlers at component instantiation
2. **Render**: Component renders with event listeners configured
3. **User Interaction**: User interacts with calendar
4. **AJAX Call**: Browser sends event data to server
5. **Handler Execution**: Your handler method is called
6. **Response**: Handler sends response back to client
7. **UI Update**: Client-side JavaScript processes response

---

## Advanced Features

### Accessing Request Context

The `AjaxCall` parameter provides access to request information:

```java
public void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, 
                        FullCalendarEventInfo event) {
    
    // Access raw event data
    Map<String, Object> unknownFields = call.getUnknownFields();
    
    // Access request headers, parameters, etc.
    // Depends on JWebMP framework implementation
}
```

### Async Processing with Mutiny

Events support reactive/async processing:

```java
@Override
public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response) {
    // Can perform async operations
    LinkedHashMap map = (LinkedHashMap) call.getUnknownFields().get("infoObj");
    FullCalendarEventInfo event = mapToEvent(map);
    
    // Return reactive stream
    return Uni.createFrom()
              .completionStage(asyncDatabaseOperation(event))
              .replaceWithVoid();
}
```

### Combining Behaviors per Event Type

FullCalendar exposes **one server-side handler per event type** (e.g., one drop handler). Combine behaviors inside that handler or delegate internally:

```java
calendar.setEventDropEvent(new CompositeEventDropHandler(
    new LoggingEventDropHandler(),      // Logs to database
    new ValidationEventDropHandler(),   // Validates move
    new NotificationEventDropHandler()  // Sends notifications
));
```

### Handling Large Datasets

For calendars with many events:

1. **Use event listeners** instead of loading all events upfront
2. **Implement lazy loading** - load events as calendar views change
3. **Batch operations** - combine multiple changes before processing
4. **Cache strategically** - cache frequently accessed data
5. **Implement pagination** - if dealing with huge datasets

---

## Templates + Server-Side Events Integration

The most powerful approach combines **Angular templates for rendering** with **server-side event handlers for logic**:

```java
@Component
public class AdvancedCalendarComponent extends FullCalendar<AdvancedCalendarComponent> {
    
    private EventService eventService;
    
    public AdvancedCalendarComponent(EventService eventService) {
        super("calendar");
        this.eventService = eventService;
        
        // Configure calendar
        FullCalendarOptions options = new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setEditable(true);
        setOptions(options);
        
        // Enable rendering templates
        setEnableEventContentTemplate(true);
        setEnableDayCellTemplate(true);
        setEnableMoreLinkTemplate(true);
        
        // Customize rendering with Angular templates
        setupCustomTemplates();
        
        // Register event handlers for interactions
        registerEventHandlers();
    }
    
    private void setupCustomTemplates() {
        // Custom event rendering with conditional styling and badges
        NgTemplateElement eventTemplate = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
            .withLetArg()
            .add("<div class=\"fc-event-custom\" " +
                 "     [class.event-highlight]=\"arg?.event?.extendedProps?.priority === 'high'\" " +
                 "     [class.event-completed]=\"arg?.event?.extendedProps?.completed\">" +
                 "  <span class=\"event-time\">{{ arg?.timeText }}</span>" +
                 "  <strong class=\"event-title\">{{ arg?.event?.title }}</strong>" +
                 "  <span *ngIf=\"arg?.event?.extendedProps?.location\" " +
                 "        class=\"event-location badge\">📍 {{ arg?.event?.extendedProps?.location }}</span>" +
                 "  <span *ngIf=\"arg?.event?.extendedProps?.attendees\" " +
                 "        class=\"event-count badge\">👥 {{ arg?.event?.extendedProps?.attendees | json }}</span>" +
                 "</div>");
        add(eventTemplate);  // Must explicitly add!
        
        // Custom day cell with day of week and styling
        NgTemplateElement dayCellTemplate = new NgTemplateElement(NgTemplateSlot.DAY_CELL_CONTENT)
            .withLetArg()
            .add("<div class=\"fc-day-cell-custom\" " +
                 "     [class.weekend]=\"isWeekend(arg?.date)\" " +
                 "     [class.today]=\"isToday(arg?.date)\">" +
                 "  <span class=\"day-number\">{{ arg?.date | date:'d' }}</span>" +
                 "  <span class=\"day-name\">{{ arg?.date | date:'EEE' }}</span>" +
                 "</div>");
        add(dayCellTemplate);  // Must explicitly add!
        
        // Custom "more" link with count
        NgTemplateElement moreLinkTemplate = new NgTemplateElement(NgTemplateSlot.MORE_LINK_CONTENT)
            .withLetArg()
            .add("<span class=\"fc-more-link-custom\">+{{ arg?.num }} more events</span>");
        add(moreLinkTemplate);  // Must explicitly add!
    }
    
    private void registerEventHandlers() {
        // When event is clicked, load full details
        setEventClickEvent(new EventClickHandler(eventService));
        
        // When event is dragged, validate and update
        setEventDropEvent(new EventDropHandler(eventService));
        
        // When date is clicked, create new event
        setDateClickEvent(new DateClickHandler(eventService));
    }
    
    // Helper methods for templates
    private boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
    
    private boolean isToday(Date date) {
        return new Date().equals(date);
    }
}
```

### Complete Working Example in Angular Component

When the above Java component renders, the Angular component receives templates like this:

```typescript
@Component({
  selector: 'app-calendar-container',
  template: `
    <full-calendar [options]="calendarOptions">
      <!-- Auto-generated event content template -->
      <ng-template #eventContent let-arg>
        <div class="fc-event-custom" 
             [class.event-highlight]="arg?.event?.extendedProps?.priority === 'high'" 
             [class.event-completed]="arg?.event?.extendedProps?.completed">
          <span class="event-time">{{ arg?.timeText }}</span>
          <strong class="event-title">{{ arg?.event?.title }}</strong>
          <span *ngIf="arg?.event?.extendedProps?.location" 
                class="event-location badge">📍 {{ arg?.event?.extendedProps?.location }}</span>
          <span *ngIf="arg?.event?.extendedProps?.attendees" 
                class="event-count badge">👥 {{ arg?.event?.extendedProps?.attendees }}</span>
        </div>
      </ng-template>
      
      <!-- Auto-generated day cell template -->
      <ng-template #dayCellContent let-arg>
        <div class="fc-day-cell-custom" 
             [class.weekend]="isWeekend(arg?.date)" 
             [class.today]="isToday(arg?.date)">
          <span class="day-number">{{ arg?.date | date:'d' }}</span>
          <span class="day-name">{{ arg?.date | date:'EEE' }}</span>
        </div>
      </ng-template>
      
      <!-- Auto-generated more link template -->
      <ng-template #moreLinkContent let-arg>
        <span class="fc-more-link-custom">+{{ arg?.num }} more events</span>
      </ng-template>
    </full-calendar>
  `,
  styles: [`
    :host ::ng-deep {
      .fc-event-custom {
        padding: 4px 6px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        border-radius: 4px;
        color: white;
        font-size: 12px;
      }
      
      .event-highlight {
        box-shadow: 0 0 10px rgba(255, 0, 0, 0.5);
        border: 2px solid red;
      }
      
      .event-completed {
        opacity: 0.6;
        text-decoration: line-through;
      }
      
      .fc-day-cell-custom {
        position: relative;
        height: 100%;
      }
      
      .weekend {
        background-color: #f5f5f5;
      }
      
      .today {
        background-color: #e3f2fd;
        border-left: 3px solid #2196F3;
      }
      
      .badge {
        display: inline-block;
        margin-left: 4px;
        padding: 2px 6px;
        background-color: rgba(255,255,255,0.3);
        border-radius: 3px;
        font-size: 10px;
      }
    }
  `]
})
export class CalendarContainerComponent implements OnInit {
  calendarOptions?: CalendarOptions;
  
  constructor(
    private http: HttpClient,
    private eventService: EventService
  ) {}
  
  ngOnInit() {
    this.http.get<any>('/api/calendar/options')
      .subscribe(options => {
        this.calendarOptions = {
          ...options,
          eventClick: (arg) => this.onEventClick(arg),
          dateClick: (arg) => this.onDateClick(arg),
          eventDrop: (arg) => this.onEventDrop(arg),
          eventResize: (arg) => this.onEventResize(arg)
        };
      });
  }
  
  onEventClick(arg: any) {
    // Server-side handler is called automatically via AJAX
    console.log('Event clicked:', arg.event.title);
  }
  
  onDateClick(arg: any) {
    // Server-side handler is called automatically via AJAX
    console.log('Date clicked:', arg.dateStr);
  }
  
  onEventDrop(arg: any) {
    // Server-side handler is called automatically via AJAX
    // Validate response and update UI if needed
    if (!arg.event.backgroundColor) {
      arg.event.setExtendedProp('backgroundColor', '#667eea');
    }
  }
  
  onEventResize(arg: any) {
    // Server-side handler is called automatically via AJAX
    console.log('Event resized:', arg.event.title);
  }
  
  isWeekend(date: Date): boolean {
    const day = date.getDay();
    return day === 0 || day === 6;
  }
  
  isToday(date: Date): boolean {
    const today = new Date();
    return date.toDateString() === today.toDateString();
  }
}
```

---

## Complete Example: Event Management System

```java
@Component
public class EventManagementComponent extends FullCalendar<EventManagementComponent> {
    
    private EventService eventService;
    
    public EventManagementComponent(EventService eventService) {
        this.eventService = eventService;
        
        FullCalendarOptions options = new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setHeaderToolbar(/* config */);
        
        this.setOptions(options);
        this.eventService = eventService;
        
        // Register all handlers
        registerEventHandlers();
    }
    
    private void registerEventHandlers() {
        setEventClickEvent(new EventClickHandler(eventService));
        setDateClickEvent(new DateClickHandler(eventService));
        setEventDropEvent(new EventDropHandler(eventService));
        setEventResizeEvent(new EventResizeHandler(eventService));
        setSelectEvent(new SelectHandler(eventService));
    }
    
    // Handler for event clicks
    private static class EventClickHandler extends FullCalendarEventClickEvent {
        private final EventService eventService;
        
        EventClickHandler(EventService eventService) {
            this.eventService = eventService;
        }
        
        @Override
        public void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, 
                                FullCalendarEventInfo event) {
            // Load full event details from database
            Event fullEvent = eventService.findEventById(event.getId());
            response.setResponseBody(Map.of(
                "event", fullEvent,
                "action", "show-detail"
            ));
        }
    }
    
    // Handler for date clicks (new event creation)
    private static class DateClickHandler extends FullCalendarDateClickEvent {
        private final EventService eventService;
        
        DateClickHandler(EventService eventService) {
            this.eventService = eventService;
        }
        
        @Override
        public void onDateClick(AjaxCall<?> call, AjaxResponse<?> response, 
                               FullCalendarDateClickEventInfo dateInfo) {
            response.setResponseBody(Map.of(
                "action", "show-create-form",
                "startDate", dateInfo.getDate(),
                "allDay", dateInfo.getAllDay()
            ));
        }
    }
    
    // Handler for event drag/drop
    private static class EventDropHandler extends FullCalendarEventDropEvent {
        private final EventService eventService;
        
        EventDropHandler(EventService eventService) {
            this.eventService = eventService;
        }
        
        @Override
        public void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response,
                               FullCalendarResourceItem oldResource, 
                               FullCalendarResourceItem newResource,
                               FullCalendarEventInfo oldEvent, 
                               FullCalendarEventInfo newEvent) {
            try {
                eventService.updateEventTime(newEvent.getId(), 
                    newEvent.getStart(), newEvent.getEnd());
                
                response.setResponseBody(Map.of("success", true));
            } catch (ConflictException e) {
                response.setResponseBody(Map.of(
                    "success", false,
                    "error", "Time slot conflicts with another event"
                ));
            }
        }
    }
    
    // Handler for event resize
    private static class EventResizeHandler extends FullCalendarEventResizeEvent {
        private final EventService eventService;
        
        EventResizeHandler(EventService eventService) {
            this.eventService = eventService;
        }
        
        @Override
        public void onEventResize(AjaxCall<?> call, AjaxResponse<?> response,
                                 FullCalendarEventInfo oldEvent, 
                                 FullCalendarEventInfo newEvent) {
            try {
                eventService.updateEventDuration(newEvent.getId(),
                    newEvent.getStart(), newEvent.getEnd());
                
                response.setResponseBody(Map.of("success", true));
            } catch (ValidationException e) {
                response.setResponseBody(Map.of(
                    "success", false,
                    "error", e.getMessage()
                ));
            }
        }
    }
    
    // Handler for range selection
    private static class SelectHandler extends FullCalendarSelectEvent {
        private final EventService eventService;
        
        SelectHandler(EventService eventService) {
            this.eventService = eventService;
        }
        
        @Override
        public void onSelect(AjaxCall<?> call, AjaxResponse<?> response, 
                            FullCalendarSelectEventInfo selectionInfo) {
            response.setResponseBody(Map.of(
                "action", "show-create-form",
                "startDate", selectionInfo.getStart(),
                "endDate", selectionInfo.getEnd(),
                "allDay", selectionInfo.getAllDay()
            ));
        }
    }
}
```

---

## Error Handling Patterns

### Validation Errors

```java
@Override
public void onEventDrop(AjaxCall<?> call, AjaxResponse<?> response,
                       FullCalendarResourceItem oldResource, 
                       FullCalendarResourceItem newResource,
                       FullCalendarEventInfo oldEvent, 
                       FullCalendarEventInfo newEvent) {
    try {
        // Validate move
        if (!isValidMove(oldEvent, newEvent)) {
            response.setResponseBody(Map.of(
                "success", false,
                "error", "Invalid move - conflicts with existing event"
            ));
            return; // IMPORTANT: Return early to not update database
        }
        
        // Safe to update
        updateEvent(newEvent);
        response.setResponseBody(Map.of("success", true));
        
    } catch (Exception e) {
        response.setResponseBody(Map.of(
            "success", false,
            "error", e.getMessage()
        ));
    }
}
```

### Resource Management

```java
@Override
public void onEventClick(AjaxCall<?> call, AjaxResponse<?> response, 
                        FullCalendarEventInfo event) {
    // Handle resource if present
    FullCalendarEventResourceInfo resource = event.getResource();
    if (resource != null) {
        String resourceId = resource.getId();
        // Load resource-specific data
    }
}
```

---

## Testing Event Handlers & Templates

### Unit Test Example: Event Handler

```java
@Test
void testEventClickHandler() {
    // Setup
    EventService mockService = mock(EventService.class);
    EventClickHandler handler = new EventClickHandler(mockService);
    
    // Create test event
    FullCalendarEventInfo event = new FullCalendarEventInfo()
        .setId("event-123")
        .setStart(LocalDateTime.now())
        .setEnd(LocalDateTime.now().plusHours(1));
    
    // Setup mocks
    AjaxCall<?> call = mock(AjaxCall.class);
    AjaxResponse<?> response = mock(AjaxResponse.class);
    Event fullEvent = new Event("event-123");
    when(mockService.findEventById("event-123")).thenReturn(fullEvent);
    
    // Execute
    handler.onEventClick(call, response, event);
    
    // Verify
    verify(response).setResponseBody(any());
    verify(mockService).findEventById("event-123");
}
```

### Unit Test Example: Template Generation

```java
@Test
void testEventContentTemplateGeneration() {
    // Setup
    FullCalendar calendar = new FullCalendar<>("calendar");
    calendar.setEnableEventContentTemplate(true);
    
    // Create custom template
    NgTemplateElement customTemplate = new NgTemplateElement("eventContent")
        .withLetArg()
        .add("<div class=\"custom\">{{ arg?.event?.title }}</div>");
    
    calendar.add(customTemplate);
    
    // Execute - render calendar HTML
    String html = calendar.toHtml();
    
    // Verify - template is in output
    assertTrue(html.contains("<ng-template #eventContent let-arg"));
    assertTrue(html.contains("<div class=\"custom\">{{ arg?.event?.title }}</div>"));
}
```

### Angular Component Test with Templates

```typescript
describe('CalendarComponent with Templates', () => {
  let component: CalendarComponent;
  let fixture: ComponentFixture<CalendarComponent>;
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarComponent ],
      imports: [ FullCalendarModule, HttpClientTestingModule ]
    })
    .compileComponents();
  });
  
  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarComponent);
    component = fixture.componentInstance;
  });
  
  it('should render event template with custom styling', () => {
    // Setup calendar options with enabled templates
    component.calendarOptions = {
      ...defaultOptions,
      plugins: [dayGridPlugin]
    };
    
    fixture.detectChanges();
    
    // Get template references
    const compiled = fixture.nativeElement;
    const eventTemplate = compiled.querySelector('ng-template[#eventContent]');
    
    expect(eventTemplate).toBeTruthy();
    expect(eventTemplate.textContent).toContain('arg?.event?.title');
  });
  
  it('should handle event click with server-side event handler', fakeAsync(() => {
    // Setup
    const httpMock = TestBed.inject(HttpTestingController);
    
    component.calendarOptions = {
      ...defaultOptions,
      eventClick: (arg: any) => {
        // Simulate server-side handler call
        httpMock.expectOne('/api/events/click').flush({ success: true });
      }
    };
    
    // Execute
    component.triggerEventClick({ event: { id: 'event-1', title: 'Test Event' } });
    
    // Verify
    tick();
  }));
});
```

### Integration Test: Templates + Event Handlers

```java
@Test
void testTemplateRenderingWithEventHandler() {
    // Create component with enabled templates
    FullCalendar calendar = new FullCalendar<>("calendar");
    calendar.setEnableEventContentTemplate(true);
    calendar.setEnableDayCellTemplate(true);
    
    // Add custom templates
    NgTemplateElement eventTemplate = new NgTemplateElement("eventContent")
        .withLetArg()
        .add("<strong>{{ arg?.event?.title }}</strong>");
    calendar.add(eventTemplate);
    
    // Register event handler
    calendar.setEventClickEvent(new TestEventClickHandler());
    
    // Configure options
    FullCalendarOptions options = new FullCalendarOptions()
        .setInitialView("dayGridMonth");
    calendar.setOptions(options);
    
    // Verify templates and handlers are properly integrated
    String html = calendar.toHtml();
    assertTrue(html.contains("<ng-template #eventContent"));
    
    // Simulate event click (would trigger server-side handler)
    // ... test handler response
}
```

---

## Best Practices

### Event Handlers

1. **Validate user input** before updating database
2. **Check permissions** for event modifications
3. **Handle exceptions** gracefully with informative responses
4. **Log important operations** for auditing
5. **Optimize queries** - don't load unnecessary data
6. **Return meaningful error messages** to client
7. **Use transactions** for database operations
8. **Implement conflict detection** for overlapping changes
9. **Test with actual calendar interactions** not just unit tests
10. **Monitor performance** - watch for slow event handlers

### Angular Templates

1. **Lazy-load template data** - don't compute everything in the template
2. **Use OnPush change detection** in Angular components for performance
3. **Cache template content** when expensive to compute
4. **Avoid complex logic in templates** - use component methods instead
5. **Use trackBy with *ngFor** if iterating in templates
6. **Test template rendering** with actual calendar data
7. **Consider accessibility** - add aria labels to template content
8. **Keep templates responsive** - test with different screen sizes
9. **Use CSS classes for styling** - avoid inline styles in templates
10. **Document template context variables** - what properties are available

### Combined Approach

1. **Use templates for presentation** - formatting, styling, conditional display
2. **Use event handlers for logic** - validation, persistence, state management
3. **Separate concerns** - keep template markup clean, handlers focused on data
4. **Handle async operations** - use Mutiny for server-side async patterns
5. **Communicate between client and server** - use AjaxResponse consistently
6. **Test both layers** - unit test handlers, component test templates
7. **Monitor payload size** - large responses slow down the calendar
8. **Implement client-side feedback** - show loading states during AJAX calls
9. **Use Angular guards** if restricting access to certain operations
10. **Version your API** - templates should be compatible with backend

---

## Related Documentation

- [Phase 2: Event Callbacks & Advanced Options](./PHASE_2_IMPLEMENTATION.md)
- [ANGULAR_INTEGRATION_GUIDE.md](./ANGULAR_INTEGRATION_GUIDE.md)
- JWebMP Framework Documentation
- FullCalendar v6.1.19 API Reference

---

## Troubleshooting

### Event Handlers

**Issue**: Handler method not being executed
**Solutions**:
- Ensure handler is registered with the correct setter (e.g., `calendar.setEventClickEvent(...)`, `setDateClickEvent(...)`, `setEventDropEvent(...)`)
- Check handler extends correct base class
- Verify component is rendered with handlers
- Check browser console for JavaScript errors

**Issue**: Event data is null
**Solutions**:
- Call `updateDates()` to parse date strings
- Check event data was sent from client
- Verify Jackson deserialization configuration
- Log `call.getUnknownFields()` to debug

**Issue**: Date parsing issues
**Solutions**:
- Check dateStr format matches expected format
- Verify LocalDateTimeDeserializer configuration
- Use `getDateStr()` to get raw string for debugging
- Check timezone settings

**Issue**: Performance issues
**Solutions**:
- Optimize database queries
- Use caching for frequently accessed data
- Batch multiple operations together
- Consider async processing with Mutiny
- Profile and identify bottlenecks

### Angular Templates

**Issue**: Template not rendering
**Solutions**:
- Verify `setEnableEventContentTemplate(true)` is called
- Check template syntax for Angular interpolation errors
- Ensure `ng-template` tag is properly formed
- Verify `let-arg` is present on ng-template
- Check browser DevTools for template errors

**Issue**: Template context is undefined
**Solutions**:
- Verify event data is being sent from calendar
- Check `arg` object structure in browser console
- Use safe navigation operator: `{{ arg?.event?.title }}`
- Verify FullCalendar plugin configuration

**Issue**: Template styling not applied
**Solutions**:
- Use `:host ::ng-deep` for deep CSS styling
- Verify CSS class names match your stylesheet
- Check FullCalendar CSS isn't overriding your styles
- Use `!important` judiciously if needed
- Verify component encapsulation settings

**Issue**: Angular pipes/directives not working in template
**Solutions**:
- Verify CommonModule is imported in feature module
- Check pipe is declared/imported in module
- Verify directive is available in template scope
- Use async pipe carefully with change detection

**Issue**: Memory leaks with event listeners
**Solutions**:
- Unsubscribe from event handlers on component destroy
- Use takeUntil pattern with subscription cleanup
- Avoid creating new handler instances in loops
- Monitor memory usage in browser DevTools

### Combined Issues

**Issue**: Event handler called but template not updating
**Solutions**:
- Trigger change detection: `ref.detectChanges()` in component
- Update model before returning from handler
- Verify response body is being set
- Check Angular change detection strategy

**Issue**: Server-side validation fails but user sees success
**Solutions**:
- Return `success: false` in response body
- Handle error response on client side
- Show error message from server response
- Don't update calendar state on error

---

## Advanced Scenarios

### Using Custom Angular Components in Templates

```java
// Java backend - using NgTemplateSlot enum for type safety
NgTemplateElement eventTemplate = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
    .withLetArg()
    .add("<app-event-detail [event]=\"arg?.event\"></app-event-detail>");

calendar.add(eventTemplate);
```

```typescript
// Angular component
@Component({
  selector: 'app-event-detail',
  template: `
    <div class="event-detail">
      <h4>{{ event.title }}</h4>
      <p>{{ event.start | date:'medium' }}</p>
      <button (click)="onEdit()">Edit</button>
      <button (click)="onDelete()">Delete</button>
    </div>
  `
})
export class EventDetailComponent {
  @Input() event: any;
  
  onEdit() {
    // Handle edit
  }
  
  onDelete() {
    // Handle delete
  }
}
```

### Reactive Event Handler with Mutiny

```java
@Override
public Uni<Void> onClick(AjaxCall<?> call, AjaxResponse<?> response) {
    LinkedHashMap map = (LinkedHashMap) call.getUnknownFields().get("infoObj");
    FullCalendarEventInfo event = mapToEvent(map);
    
    return eventService.loadEventDetailsAsync(event.getId())
        .onItem().transform(details -> {
            response.setResponseBody(details);
            return null;
        })
        .onFailure().invoke(err -> {
            response.setResponseBody(Map.of(
                "error", err.getMessage()
            ));
        });
}
```

### Multiple Calendar Instances with Shared Templates

```java
// Shared template utilities with NgTemplateSlot enum for type safety
class CalendarTemplateFactory {
    public static NgTemplateElement createEventTemplate(String cssClass) {
        return new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)  // Type safe!
            .withLetArg()
            .add("<div class=\"" + cssClass + "\">" +
                 "  {{ arg?.event?.title }}" +
                 "</div>");
    }
}

// Multiple calendars with consistent templates
FullCalendar calendar1 = new FullCalendar<>("calendar1");
calendar1.setEnableEventContentTemplate(true);
calendar1.add(CalendarTemplateFactory.createEventTemplate("calendar1-event"));

FullCalendar calendar2 = new FullCalendar<>("calendar2");
calendar2.setEnableEventContentTemplate(true);
calendar2.add(CalendarTemplateFactory.createEventTemplate("calendar2-event"));
```

---

## Version Information

- **FullCalendar Version**: v6.1.19
- **FullCalendar Angular Plugin**: v6.x (official)
- **JWebMP Version**: Latest
- **Java Version**: Java 25 LTS
- **Angular Version**: 12-20
- **Reactive Framework**: Smallrye Mutiny
- **DI Framework**: GuicedEE

---

**Last Updated**: December 4, 2025  
**Documentation Quality**: Complete  
**Code Examples**: 15+  
**Event Types Covered**: 6 (Click, DateClick, Drop, EventDrop, Resize, Select)  
**Template Types Covered**: 8 (eventContent, dayHeaderContent, dayCellContent, weekNumberContent, moreLinkContent, noEventsContent, slotLabelContent, listDayHeaderContent)
