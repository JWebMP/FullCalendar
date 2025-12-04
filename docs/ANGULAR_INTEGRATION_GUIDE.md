# JWebMP FullCalendar + Angular Integration Guide

**Version**: v6.1.19 Aligned  
**Target**: Angular 12-20 with FullCalendar 6.1.19  
**JWebMP**: FullCalendar Plugin v2.0.0-SNAPSHOT  

---

## Quick Start

### 1. Backend Setup (Java/JWebMP)

```java
import com.jwebmp.plugins.fullcalendar.options.FullCalendarOptions;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarEventsList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
  
  @GetMapping("/options")
  public FullCalendarOptions getCalendarOptions() {
    FullCalendarOptions options = new FullCalendarOptions();
    
    // Core configuration
    options.setInitialView("dayGridMonth");
    options.setLocale("en");
    options.setTimeZone("UTC");
    options.setEditable(true);
    options.setHeaderToolbar(buildHeaderToolbar());
    
    // Events (can be static or dynamic)
    options.setEvents(getCalendarEvents());
    
    return options;
  }
  
  @GetMapping("/events")
  public FullCalendarEventsList getCalendarEvents() {
    FullCalendarEventsList events = new FullCalendarEventsList();
    // Populate with your events
    return events;
  }
  
  private FullCalendarHeaderToolBarOptions buildHeaderToolbar() {
    return new FullCalendarHeaderToolBarOptions()
        .setLeft("prev,next today")
        .setCenter("title")
        .setRight("dayGridMonth,timeGridWeek,timeGridDay");
  }
}
```

### 2. Frontend Setup (Angular)

**Install dependencies**:
```bash
npm install @fullcalendar/core @fullcalendar/angular @fullcalendar/daygrid @fullcalendar/timegrid @fullcalendar/interaction
```

**Module setup**:
```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarComponent } from './calendar/calendar.component';

@NgModule({
  imports: [CommonModule, FullCalendarModule],
  declarations: [CalendarComponent]
})
export class CalendarModule {}
```

**Component**:
```typescript
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';

@Component({
  selector: 'app-calendar',
  template: `<full-calendar [options]="calendarOptions"></full-calendar>`,
  styles: [`
    :host ::ng-deep .fc {
      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', sans-serif;
    }
  `]
})
export class CalendarComponent implements OnInit {
  calendarOptions?: CalendarOptions;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Fetch options from JWebMP backend
    this.http.get<CalendarOptions>('/api/calendar/options')
      .subscribe(options => {
        this.calendarOptions = {
          ...options,
          plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
          dateClick: (arg) => this.onDateClick(arg),
          eventClick: (arg) => this.onEventClick(arg)
        };
      });
  }

  onDateClick(arg: any) {
    console.log('Date clicked:', arg.dateStr);
  }

  onEventClick(arg: any) {
    console.log('Event clicked:', arg.event.title);
  }
}
```

**Template**:
```html
<div class="calendar-container">
  <full-calendar *ngIf="calendarOptions" [options]="calendarOptions"></full-calendar>
</div>
```

---

## Common Use Cases

### Use Case 1: Dynamic Event Updates

**Backend**:
```java
@PostMapping("/events")
public FullCalendarEventsList saveEvent(@RequestBody CalendarEvent event) {
  eventService.save(event);
  return eventService.getAllEvents();
}
```

**Frontend**:
```typescript
addEvent(title: string, date: string) {
  const newEvent = { title, start: date };
  
  this.http.post<FullCalendarEventsList>('/api/calendar/events', newEvent)
    .subscribe(events => {
      if (this.calendarOptions) {
        this.calendarOptions.events = events;
      }
    });
}
```

### Use Case 2: User-Specific Calendar Configuration

**Backend**:
```java
@GetMapping("/options/{userId}")
public FullCalendarOptions getUserCalendarOptions(@PathVariable String userId) {
  User user = userService.findById(userId);
  FullCalendarOptions options = new FullCalendarOptions();
  
  // Apply user preferences
  options.setLocale(user.getPreferredLocale());
  options.setTimeZone(user.getTimeZone());
  options.setFirstDay(user.getFirstDayOfWeek());
  options.setEditable(user.canEditEvents());
  options.setEvents(eventService.getUserEvents(userId));
  
  return options;
}
```

**Frontend**:
```typescript
ngOnInit() {
  const userId = this.route.snapshot.paramMap.get('userId');
  
  this.http.get<CalendarOptions>(`/api/calendar/options/${userId}`)
    .subscribe(options => {
      this.calendarOptions = {
        ...options,
        plugins: [...], // Always required
        // Client-side enhancements
        dateClick: (arg) => this.handleDateClick(arg)
      };
    });
}
```

### Use Case 3: Multi-Language Support

**Backend**:
```java
@GetMapping("/options")
public FullCalendarOptions getCalendarOptions(
    @RequestParam(required = false) String locale) {
  FullCalendarOptions options = new FullCalendarOptions();
  
  // Set primary locale
  String userLocale = locale != null ? locale : "en";
  options.setLocale(userLocale);
  
  // Optional: support multiple locales
  options.setLocales(Arrays.asList("en", "fr", "de", "es"));
  
  return options;
}
```

**Frontend**:
```typescript
changeLocale(locale: string) {
  this.http.get<CalendarOptions>('/api/calendar/options', {
    params: { locale }
  })
  .subscribe(options => {
    if (this.calendarOptions) {
      this.calendarOptions.locale = options.locale;
      this.calendarOptions.locales = options.locales;
    }
  });
}
```

### Use Case 4: Custom Event Rendering

**Frontend with ng-template**:
```html
<full-calendar [options]="calendarOptions">
  <ng-template #eventContent let-arg>
    <div class="custom-event">
      <div class="event-time">{{ arg.event.start | date:'short' }}</div>
      <div class="event-title">{{ arg.event.title }}</div>
      <div class="event-actions">
        <button (click)="editEvent(arg.event)">Edit</button>
        <button (click)="deleteEvent(arg.event)">Delete</button>
      </div>
    </div>
  </ng-template>
</full-calendar>
```

```typescript
editEvent(event: any) {
  // Call backend to update
  this.http.put(`/api/calendar/events/${event.id}`, event)
    .subscribe(() => this.refreshCalendar());
}

deleteEvent(event: any) {
  this.http.delete(`/api/calendar/events/${event.id}`)
    .subscribe(() => this.refreshCalendar());
}

refreshCalendar() {
  // Re-fetch options or just events
  this.http.get<CalendarEventsList>('/api/calendar/events')
    .subscribe(events => {
      if (this.calendarOptions) {
        this.calendarOptions.events = events;
      }
    });
}
```

### Use Case 5: Timezone-Aware Calendar

**Backend**:
```java
@GetMapping("/options")
public FullCalendarOptions getCalendarOptions(
    @RequestParam(required = false) String timeZone) {
  FullCalendarOptions options = new FullCalendarOptions();
  
  String tz = timeZone != null ? timeZone : "UTC";
  options.setTimeZone(tz);
  
  // Events will be rendered in the specified timezone
  options.setEvents(eventService.getEventsInTimeZone(tz));
  
  return options;
}
```

**Frontend**:
```typescript
onTimeZoneChange(newTimeZone: string) {
  this.http.get<CalendarOptions>('/api/calendar/options', {
    params: { timeZone: newTimeZone }
  })
  .subscribe(options => {
    if (this.calendarOptions) {
      this.calendarOptions.timeZone = options.timeZone;
      // Calendar will re-render events in new timezone
    }
  });
}
```

---

## Available JWebMP Options (v6 Aligned)

### Core Display Options

```java
// View and Initial State
options.setInitialView("dayGridMonth");          // 'dayGridMonth', 'timeGridWeek', etc.
options.setInitialDate("2024-01-15");            // Specific date to load

// Localization
options.setLocale("en-US");                      // Single locale
options.setLocales(Arrays.asList("en", "fr"));   // Multiple locales
options.setTimeZone("America/New_York");         // IANA timezone
options.setDirection("rtl");                     // 'ltr' or 'rtl'

// Week Configuration  
options.setFirstDay(0);                          // 0=Sunday, 1=Monday, etc.
options.setWeekends(true);                       // Show weekends
options.setNow(getCurrentDateTime());            // Override current time

// Default Event Duration
options.setDefaultAllDay(false);                 // Default event type
options.setDefaultAllDayEventDuration("24:00");  // ISO duration format
options.setDefaultTimedEventDuration("01:00");   // ISO duration format
```

### Interaction Options

```java
// Editing
options.setEditable(true);                       // Allow event drag/resize
options.setEventStartEditable(true);             // Allow dragging
options.setEventDurationEditable(true);          // Allow resizing

// Selecting
options.setSelectable(true);                     // Allow date selection
options.setSelectOverlap(true);                  // Allow selection overlap

// Clicking
options.setNavLinks(true);                       // Click day/week names
options.setDayMaxEvents(5);                      // Limit events per day
```

### Toolbar Configuration

```java
FullCalendarHeaderToolBarOptions toolbar = new FullCalendarHeaderToolBarOptions();
toolbar.setLeft("prev,next today");
toolbar.setCenter("title");
toolbar.setRight("dayGridMonth,timeGridWeek,timeGridDay");
options.setHeaderToolbar(toolbar);

// Optional footer toolbar
FullCalendarHeaderToolBarOptions footer = new FullCalendarHeaderToolBarOptions();
footer.setCenter("title");
options.setFooterToolbar(footer);
```

### Event Source Configuration

```java
// Direct event array
FullCalendarEventsList events = new FullCalendarEventsList();
// Add events
options.setEvents(events);

// Event source (via URL or function)
// Note: URL sources are handled client-side in Angular
```

### Business Hours

```java
FullCalendarBusinessHours businessHours = new FullCalendarBusinessHours();
businessHours.setDaysOfWeek(Arrays.asList(1, 2, 3, 4, 5)); // Mon-Fri
businessHours.setStartTime("09:00");
businessHours.setEndTime("17:00");
options.setBusinessHours(businessHours);
```

---

## Type Mappings: Java ↔ JSON ↔ TypeScript

| Java (JWebMP) | JSON | TypeScript (Angular) | Notes |
|---------------|------|----------------------|-------|
| `String` | string | `string` | Straightforward |
| `Integer` | number | `number` | Numeric primitives |
| `Boolean` | boolean | `boolean` | TRUE → true |
| `List<String>` | `["a","b"]` | `string[]` | Array serialization |
| `List<Event>` | `[{...}]` | `Event[]` | Complex objects |
| `LocalDateTime` | ISO-8601 | `Date` | Temporal handling |
| Custom Objects | `{...}` | Interface type | Full object serialization |

---

## Event Handling Patterns

### Pattern 1: Client-Side Event Handlers

```typescript
// These must be added on Angular side (can't come from server)
this.calendarOptions = {
  ...receivedOptions,
  
  // Called when user clicks a date
  dateClick: (info) => {
    const selectedDate = info.dateStr;
    this.showCreateEventDialog(selectedDate);
  },
  
  // Called when user clicks an event
  eventClick: (info) => {
    const event = info.event;
    this.showEventDetailsModal(event);
  },
  
  // Called when event is dragged
  eventDrop: (info) => {
    if (!confirm('Are you sure?')) {
      info.revert();
      return;
    }
    this.updateEventDates(info.event);
  },
  
  // Called when event is resized
  eventResize: (info) => {
    this.updateEventDuration(info.event);
  },
  
  // Called after view is rendered
  datesSet: (info) => {
    console.log('Calendar showing:', info.start, 'to', info.end);
  }
};
```

### Pattern 2: Event Update Flow

```
User Action → Angular Handler → JWebMP Backend → Database → Response → Angular Updates Calendar
```

```typescript
export class CalendarComponent {
  createEvent(date: string, title: string) {
    const newEvent = {
      title,
      start: date,
      end: new Date(date).getTime() + 60 * 60 * 1000 // 1 hour
    };

    // Send to backend
    this.http.post<CalendarEvent>('/api/events', newEvent)
      .subscribe(
        savedEvent => {
          // Update calendar display
          if (this.calendarOptions?.events && Array.isArray(this.calendarOptions.events)) {
            this.calendarOptions.events = [
              ...this.calendarOptions.events as any,
              savedEvent
            ];
          }
        },
        error => alert('Failed to save event')
      );
  }

  updateEvent(eventId: string, updatedData: any) {
    this.http.put(`/api/events/${eventId}`, updatedData)
      .subscribe(
        updatedEvent => {
          // Refresh calendar (simple approach)
          this.refreshCalendarEvents();
        },
        error => alert('Failed to update event')
      );
  }

  deleteEvent(eventId: string) {
    this.http.delete(`/api/events/${eventId}`)
      .subscribe(
        () => {
          // Remove from display
          if (this.calendarOptions?.events && Array.isArray(this.calendarOptions.events)) {
            this.calendarOptions.events = (this.calendarOptions.events as any)
              .filter((e: any) => e.id !== eventId);
          }
        },
        error => alert('Failed to delete event')
      );
  }

  private refreshCalendarEvents() {
    this.http.get<FullCalendarEventsList>('/api/events')
      .subscribe(events => {
        if (this.calendarOptions) {
          this.calendarOptions.events = events;
        }
      });
  }
}
```

---

## Breaking Changes from v5 → v6

### 1. initialView Type Change

```java
// ❌ OLD (v5) - No longer works
options.setInitialView(new FullCalendarViewDayGridMonth<>());

// ✅ NEW (v6) - Use String
options.setInitialView("dayGridMonth");
```

**View Names**:
- `dayGridMonth` - Month view
- `timeGridWeek` - Week view with times
- `timeGridDay` - Day view with times
- `listWeek` - List view
- `multiMonthYear` - Multi-month view

### 2. eventLimit Removed

```java
// ❌ OLD (v5) - No longer exists
options.setEventLimit(5);

// ✅ NEW (v6) - Use dayMaxEvents
options.setDayMaxEvents(5);
```

### 3. New Timezone Support

```java
// 🆕 NEW in v6
options.setTimeZone("America/New_York");
```

All events are automatically rendered in the specified timezone.

---

## Performance Optimization

### Lazy Load Events

```typescript
// Instead of loading all events upfront
// Load events for the visible date range

export class CalendarComponent implements OnInit {
  calendarOptions?: CalendarOptions;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<CalendarOptions>('/api/calendar/options')
      .subscribe(baseOptions => {
        this.calendarOptions = {
          ...baseOptions,
          plugins: [...],
          
          // Lazy load events when dates change
          datesSet: (info) => {
            this.loadEventsForRange(info.start, info.end);
          }
        };
      });
  }

  loadEventsForRange(start: Date, end: Date) {
    this.http.get<FullCalendarEventsList>('/api/events', {
      params: {
        start: start.toISOString(),
        end: end.toISOString()
      }
    })
    .subscribe(events => {
      if (this.calendarOptions) {
        this.calendarOptions.events = events;
      }
    });
  }
}
```

### Use deepChangeDetection Wisely

```html
<!-- Use only when modifying nested objects frequently -->
<full-calendar 
  [options]="calendarOptions" 
  deepChangeDetection="true"
></full-calendar>
```

**When to use**:
- Modifying event properties in-place
- Changing nested toolbar/view configurations

**When NOT to use**:
- Replacing entire events array
- Simple property updates
- Performance-critical calendars

---

## Testing

```typescript
import { TestBed, ComponentFixture } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CalendarComponent } from './calendar.component';

describe('CalendarComponent with JWebMP', () => {
  let component: CalendarComponent;
  let fixture: ComponentFixture<CalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, FullCalendarModule],
      declarations: [CalendarComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(CalendarComponent);
    component = fixture.componentInstance;
  });

  it('should load calendar options from backend', () => {
    const mockOptions = {
      initialView: 'dayGridMonth',
      locale: 'en',
      events: []
    };

    const req = httpTestingController.expectOne('/api/calendar/options');
    req.flush(mockOptions);
    fixture.detectChanges();

    expect(component.calendarOptions).toEqual(mockOptions);
  });
});
```

---

## Troubleshooting

### Issue: Events not displaying

**Check**:
1. Events are in correct format: `{ title, start, end?, ...}`
2. Dates are valid ISO-8601 strings
3. `initialView` is set and plugin for view is loaded
4. Check browser console for errors

### Issue: Timezone conversion issues

**Solution**:
```java
// Server must handle timezone conversion
options.setTimeZone("America/New_York");
// Events should be stored in UTC, FullCalendar displays in specified timezone
```

### Issue: Angular change detection not triggering

**Solution**:
```typescript
// Instead of modifying events array in-place
this.calendarOptions.events.push(newEvent); // ❌ Won't trigger

// Create new reference
this.calendarOptions.events = [
  ...this.calendarOptions.events,
  newEvent
];

// Or use deepChangeDetection="true" in template
```

---

## Conclusion

The JWebMP FullCalendar plugin is **fully compatible** with Angular 12-20 and the official FullCalendar Angular adapter. All options generated by JWebMP serialize correctly to JSON and work seamlessly with Angular's template binding and change detection.

**Key Takeaway**: JWebMP handles server-side configuration; Angular handles client-side interaction and rendering.

