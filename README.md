# JWebMP FullCalendar

[![Maven Central](https://img.shields.io/maven-central/v/com.jwebmp.plugins/full-calendar)](https://central.sonatype.com/artifact/com.jwebmp.plugins/full-calendar)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue)](https://www.apache.org/licenses/LICENSE-2.0)

![Java 25+](https://img.shields.io/badge/Java-25%2B-green)
![Modular](https://img.shields.io/badge/Modular-JPMS-green)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?logo=angular)
![TypeScript](https://img.shields.io/badge/TypeScript-5-3178C6?logo=typescript)

<!-- Tech icons row -->
![FullCalendar](https://img.shields.io/badge/FullCalendar-6.1.19-4285F4)
![JWebMP](https://img.shields.io/badge/JWebMP-2.0-0A7)

Full-featured calendar integration for JWebMP with Angular 21. Provides server-driven calendar configuration with FullCalendar 6.1.19 for drag-and-drop event scheduling, multiple calendar views, and full timezone support.

Built on [FullCalendar 6](https://fullcalendar.io/) · [Angular FullCalendar](https://github.com/fullcalendar/fullcalendar-angular) · [JWebMP Core](https://jwebmp.com/) · JPMS module `com.jwebmp.plugins.fullcalendar` · Java 25+

**Version: 6.1.19** — Complete FullCalendar integration with Angular 21 and server-side configuration.

## Installation

```xml
<dependency>
  <groupId>com.jwebmp.plugins</groupId>
  <artifactId>full-calendar</artifactId>
  <version>2.0.0-RC4</version>
</dependency>
```

<details>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
implementation("com.jwebmp.plugins:full-calendar:2.0.0-RC4")
```
</details>

### NPM Dependencies

The plugin automatically includes FullCalendar dependencies:

```json
{
  "dependencies": {
    "@fullcalendar/angular": "^6.1.19",
    "@fullcalendar/adaptive": "^6.1.19",
    "@fullcalendar/daygrid": "^6.1.19",
    "@fullcalendar/timegrid": "^6.1.19",
    "@fullcalendar/list": "^6.1.19",
    "@fullcalendar/interaction": "^6.1.19",
    "@fullcalendar/scrollgrid": "^6.1.19",
    "@fullcalendar/bootstrap5": "^6.1.19",
    "@fullcalendar/luxon2": "^6.1.19",
    "@fullcalendar/moment": "^6.1.19",
    "@fullcalendar/moment-timezone": "^6.1.19"
  }
}
```

## Features

- **Full Calendar Views** — Day, Week, Month, List, Timeline, and custom views
- **Drag & Drop Events** — Interactive event creation, editing, and rescheduling
- **Multiple Calendar Types** — DayGrid, TimeGrid, List, Resource Timeline
- **Timezone Support** — Full IANA timezone database support via Moment Timezone
- **Localization** — Multi-language support with 50+ locales
- **Event Sources** — JSON feeds, functions, Google Calendar integration
- **Recurring Events** — Support for repeating events with RRule
- **Resource Management** — Resource timeline for scheduling across resources
- **Theming** — Bootstrap 5 theme integration
- **Mobile Adaptive** — Responsive design with touch support
- **Type-Safe Java API** — CRTP fluent API for calendar configuration
- **Angular Integration** — Seamless integration with Angular 21
- **Server-Driven Config** — Configure calendar from Java backend
- **JSON Serialization** — Automatic Jackson serialization for Angular binding

## Quick Start

### Prerequisites

- **Java 25 LTS** (required)
- **Maven 3.8+**
- **Node.js 18+** (for frontend builds)
- **Angular 21+** (auto-integrated via JWebMP)

### Basic Usage

```java
import com.jwebmp.plugins.fullcalendar.options.FullCalendarOptions;
import com.jwebmp.plugins.fullcalendar.options.Event;

public class CalendarConfig {
    public FullCalendarOptions getCalendarOptions() {
        return new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setLocale("en")
            .setTimeZone("UTC")
            .setEditable(true)
            .setHeaderToolbar(new Toolbar()
                .setLeft("prev,next today")
                .setCenter("title")
                .setRight("dayGridMonth,timeGridWeek,timeGridDay,listWeek"))
            .setEvents(getEvents());
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event()
            .setTitle("Team Meeting")
            .setStart("2025-03-15T10:00:00")
            .setEnd("2025-03-15T11:00:00")
            .setBackgroundColor("#4285F4"));
        return events;
    }
}
```

### Angular Integration

```typescript
import { Component, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';

@Component({
  selector: 'app-calendar',
  template: `<full-calendar [options]="calendarOptions"></full-calendar>`
})
export class CalendarComponent implements OnInit {
  calendarOptions?: CalendarOptions;

  ngOnInit() {
    this.http.get<CalendarOptions>('/api/calendar/options')
      .subscribe(opts => {
        this.calendarOptions = {
          ...opts,
          plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
          dateClick: (arg) => this.handleDateClick(arg),
          eventClick: (arg) => this.handleEventClick(arg)
        };
      });
  }

  handleDateClick(arg: any) {
    alert('Date clicked: ' + arg.dateStr);
  }

  handleEventClick(arg: any) {
    alert('Event: ' + arg.event.title);
  }
}
```

### REST API Example (GuicedEE REST)

```java
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.jwebmp.plugins.fullcalendar.options.*;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/calendar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalendarResource {

    @Inject
    private EventService eventService;

    @GET
    @Path("/options")
    public FullCalendarOptions getCalendarOptions() {
        return new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setLocale("en")
            .setTimeZone("America/New_York")
            .setEditable(true)
            .setSelectable(true)
            .setSelectMirror(true)
            .setDayMaxEvents(true)
            .setWeekends(true)
            .setNowIndicator(true);
    }

    @GET
    @Path("/events")
    public List<Event> getEvents() {
        return eventService.findAll().stream()
            .map(e -> new Event()
                .setId(e.getId())
                .setTitle(e.getTitle())
                .setStart(e.getStartTime())
                .setEnd(e.getEndTime())
                .setAllDay(e.isAllDay())
                .setBackgroundColor(e.getColor()))
            .collect(Collectors.toList());
    }

    @POST
    @Path("/events")
    public Event createEvent(Event event) {
        return eventService.save(event);
    }

    @PUT
    @Path("/events/{id}")
    public Event updateEvent(@PathParam("id") String id, Event event) {
        return eventService.update(id, event);
    }

    @DELETE
    @Path("/events/{id}")
    public Response deleteEvent(@PathParam("id") String id) {
        eventService.delete(id);
        return Response.noContent().build();
    }
}
```

---

## Architecture

### Technology Stack

- **Backend**: Java 25 LTS, Maven, GuicedEE (IoC), Vert.x 5
- **Frontend**: Angular 21, TypeScript, FullCalendar 6.1.19
- **Integration**: JWebMP Page Configurators, ServiceLoader SPI
- **Calendar**: FullCalendar Core with Angular adapter
- **Plugins**: DayGrid, TimeGrid, List, Interaction, Bootstrap5, Moment, Luxon
- **Module System**: JPMS with explicit dependencies

### Module Structure

```
src/main/java/com/jwebmp/plugins/fullcalendar/
├── FullCalendar.java                     # Main calendar component
├── FullCalendarPageConfigurator.java     # Auto-configuration
├── options/
│   ├── FullCalendarOptions.java          # Main calendar configuration
│   ├── Event.java                        # Event data model
│   ├── EventSource.java                  # Event source configuration
│   ├── View.java                         # View configuration
│   ├── Toolbar.java                      # Toolbar configuration
│   ├── BusinessHours.java                # Business hours configuration
│   ├── DateRange.java                    # Date range helpers
│   └── ...                               # Additional option models
└── ...
```

### FullCalendar Views

| View Type | Plugin | Description | Java Constant |
|-----------|--------|-------------|---------------|
| **dayGridMonth** | daygrid | Month view with day cells | `"dayGridMonth"` |
| **dayGridWeek** | daygrid | Week view with day cells | `"dayGridWeek"` |
| **dayGridDay** | daygrid | Single day view | `"dayGridDay"` |
| **timeGridWeek** | timegrid | Week view with time slots | `"timeGridWeek"` |
| **timeGridDay** | timegrid | Day view with time slots | `"timeGridDay"` |
| **listWeek** | list | List view for week | `"listWeek"` |
| **listMonth** | list | List view for month | `"listMonth"` |
| **listYear** | list | List view for year | `"listYear"` |

### FullCalendar Plugins

```java
// Core plugins auto-loaded by PageConfigurator
@TsDependency(value = "@fullcalendar/angular", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/daygrid", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/timegrid", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/list", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/interaction", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/scrollgrid", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/bootstrap5", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/luxon2", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/moment", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/moment-timezone", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/adaptive", version = "^6.1.19")
```

### Key Configuration Options

```java
// Display options
.setInitialView("dayGridMonth")           // Starting view
.setInitialDate("2025-03-15")             // Starting date
.setWeekends(true)                        // Show/hide weekends
.setDayMaxEvents(3)                       // Max events per day
.setNowIndicator(true)                    // Show current time indicator

// Interaction options
.setEditable(true)                        // Enable drag & drop
.setSelectable(true)                      // Enable date selection
.setSelectMirror(true)                    // Mirror while selecting
.setEventStartEditable(true)              // Allow event time editing
.setEventDurationEditable(true)           // Allow event duration editing

// Localization
.setLocale("en")                          // Language (en, es, fr, de, etc.)
.setTimeZone("America/New_York")          // IANA timezone
.setDirection("ltr")                      // Text direction (ltr/rtl)

// Event defaults
.setDefaultAllDay(false)                  // Events all-day by default
.setDefaultTimedEventDuration("01:00")    // Default event duration
.setSlotDuration("00:30")                 // Time slot duration

// Header/Footer toolbars
.setHeaderToolbar(new Toolbar()
    .setLeft("prev,next today")
    .setCenter("title")
    .setRight("dayGridMonth,timeGridWeek,timeGridDay"))

// Business hours
.setBusinessHours(new BusinessHours()
    .setDaysOfWeek([1,2,3,4,5])
    .setStartTime("09:00")
    .setEndTime("17:00"))
```

---

## API Reference

### FullCalendarOptions Class

Main configuration class for calendar setup:

```java
public class FullCalendarOptions {
    // Display
    FullCalendarOptions setInitialView(String view)
    FullCalendarOptions setInitialDate(String date)
    FullCalendarOptions setWeekends(boolean show)
    FullCalendarOptions setDayMaxEvents(Integer max)
    FullCalendarOptions setNowIndicator(boolean show)

    // Interaction
    FullCalendarOptions setEditable(boolean editable)
    FullCalendarOptions setSelectable(boolean selectable)
    FullCalendarOptions setSelectMirror(boolean mirror)

    // Localization
    FullCalendarOptions setLocale(String locale)
    FullCalendarOptions setTimeZone(String timezone)
    FullCalendarOptions setDirection(String dir)

    // Events
    FullCalendarOptions setEvents(List<Event> events)
    FullCalendarOptions setEventSources(List<EventSource> sources)

    // Toolbars
    FullCalendarOptions setHeaderToolbar(Toolbar toolbar)
    FullCalendarOptions setFooterToolbar(Toolbar toolbar)

    // Business hours
    FullCalendarOptions setBusinessHours(BusinessHours hours)
}
```

### Event Class

Event data model:

```java
public class Event {
    Event setId(String id)
    Event setTitle(String title)
    Event setStart(String start)
    Event setEnd(String end)
    Event setAllDay(boolean allDay)
    Event setUrl(String url)
    Event setBackgroundColor(String color)
    Event setBorderColor(String color)
    Event setTextColor(String color)
    Event setClassNames(String... classNames)
    Event setEditable(boolean editable)
    Event setExtendedProps(Map<String, Object> props)
}
```

---

## Testing

### Run Unit Tests

```bash
mvn clean test
```

### Code Coverage Report

```bash
mvn clean test jacoco:report
# Open: target/site/jacoco/index.html
```

### Test Example

```java
import com.jwebmp.plugins.fullcalendar.options.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FullCalendarTest {

    @Test
    public void testCalendarOptionsCreation() {
        FullCalendarOptions options = new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setEditable(true)
            .setLocale("en");

        assertNotNull(options);
        assertEquals("dayGridMonth", options.getInitialView());
        assertTrue(options.getEditable());
    }

    @Test
    public void testEventCreation() {
        Event event = new Event()
            .setTitle("Test Event")
            .setStart("2025-03-15T10:00:00")
            .setEnd("2025-03-15T11:00:00");

        assertEquals("Test Event", event.getTitle());
        assertNotNull(event.getStart());
    }
}
```

---

## Configuration

### Auto-Configuration via PageConfigurator

The plugin is automatically configured when present on the classpath:

```java
@PluginInformation(
    pluginName = "Full Calendar",
    pluginUniqueName = "full-calendar",
    pluginVersion = "6.1.19"
)
@TsDependency(value = "@fullcalendar/angular", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/daygrid", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/timegrid", version = "^6.1.19")
public class FullCalendarPageConfigurator implements IPageConfigurator {
    // Auto-loaded via ServiceLoader SPI
}
```

---

## Module Graph

```
com.jwebmp.plugins.fullcalendar
 ├── com.jwebmp.core              (JWebMP core)
 ├── com.jwebmp.plugins.angular   (Angular integration)
 ├── com.jwebmp.plugins.typescript.client (TypeScript generation)
 ├── com.jwebmp.vertx             (Vert.x async runtime)
 └── jakarta.validation           (Bean validation)
```

### Exported Packages

- `com.jwebmp.plugins.fullcalendar` — Core FullCalendar component
- `com.jwebmp.plugins.fullcalendar.options` — Calendar configuration models

---

## Documentation

### Core Resources

- **[FullCalendar Docs](https://fullcalendar.io/docs)** — Official FullCalendar documentation
- **[Angular FullCalendar](https://github.com/fullcalendar/fullcalendar-angular)** — Angular component library
- **[FullCalendar Examples](https://fullcalendar.io/docs#toc)** — Interactive demos
- **[JWebMP Home](https://jwebmp.com/)** — JWebMP framework documentation

### Project Files

| File | Purpose |
|------|---------|
| `FullCalendar.java` | Main calendar component |
| `FullCalendarOptions.java` | Calendar configuration model |
| `Event.java` | Event data model |
| `FullCalendarPageConfigurator.java` | Auto-configuration |
| `pom.xml` | Maven build configuration |
| `module-info.java` | JPMS module descriptor |

### Additional Documentation

- **`docs/architecture/`** — Architecture diagrams and technical documentation

---

## Common Use Cases

### Employee Scheduling Calendar

```java
public class EmployeeScheduler {
    public FullCalendarOptions createSchedule() {
        return new FullCalendarOptions()
            .setInitialView("timeGridWeek")
            .setSlotMinTime("06:00:00")
            .setSlotMaxTime("22:00:00")
            .setSlotDuration("00:30:00")
            .setBusinessHours(new BusinessHours()
                .setDaysOfWeek([1,2,3,4,5])
                .setStartTime("09:00")
                .setEndTime("17:00"))
            .setEvents(loadShifts());
    }
}
```

### Event Management Dashboard

```java
public class EventDashboard {
    public FullCalendarOptions createEventCalendar() {
        return new FullCalendarOptions()
            .setInitialView("dayGridMonth")
            .setEditable(true)
            .setSelectable(true)
            .setHeaderToolbar(new Toolbar()
                .setLeft("prev,next today")
                .setCenter("title")
                .setRight("dayGridMonth,timeGridWeek,listMonth"))
            .setEventSources(List.of(
                new EventSource().setUrl("/api/events"),
                new EventSource().setUrl("/api/holidays").setColor("red")
            ));
    }
}
```

### Multi-Timezone Conference Calendar

```java
public class ConferenceCalendar {
    public FullCalendarOptions createConferenceCalendar(String userTimezone) {
        return new FullCalendarOptions()
            .setInitialView("timeGridWeek")
            .setTimeZone(userTimezone)
            .setLocale(getUserLocale())
            .setNowIndicator(true)
            .setSlotLabelFormat(Map.of(
                "hour", "2-digit",
                "minute", "2-digit",
                "meridiem", "short"
            ))
            .setEvents(loadConferenceSessions());
    }
}
```

### Resource Timeline

```java
public class ResourceScheduler {
    public FullCalendarOptions createResourceTimeline() {
        return new FullCalendarOptions()
            .setInitialView("resourceTimeGridDay")
            .setResources(loadRooms())
            .setEvents(loadBookings())
            .setEditable(true)
            .setResourceAreaHeaderContent("Conference Rooms")
            .setSlotMinTime("08:00:00")
            .setSlotMaxTime("20:00:00");
    }
}
```

---

## Migration from v5 to v6

### Breaking Changes

1. **initialView**: Now a String ("dayGridMonth") not an object
2. **eventLimit**: Removed (use `dayMaxEvents` instead)
3. **Timezone handling**: Enhanced with explicit `timeZone` option

### Migration Guide

```java
// v5 (old)
options.setView(new View().setType(ViewType.MONTH));
options.setEventLimit(5);

// v6 (new)
options.setInitialView("dayGridMonth");
options.setDayMaxEvents(5);
```

See `docs/FULLCALENDAR_V6_ALIGNMENT_CHANGES.md` for complete migration guide.

---

## Security

This project takes security seriously.

**Key Security Features**:
- No external network calls (calendar loaded from NPM packages)
- Input validation via Jakarta Validation
- JSON serialization via Jackson with safe defaults
- OWASP Dependency-Check in CI/CD
- GitHub Dependabot enabled
- JSpecify null-safety annotations

---

## Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork** the repository
2. **Create a feature branch** (`git checkout -b feature/my-feature`)
3. **Commit with clear messages** (`git commit -m "feat: add resource timeline support"`)
4. **Push to your fork** (`git push origin feature/my-feature`)
5. **Open a Pull Request**

### Code Standards

- **Java**: Follow JWebMP conventions (CRTP fluent API, proper null safety)
- **Tests**: JUnit 5, ≥80% coverage (Jacoco enforced)
- **Formatting**: Maven Spotless plugin enforced
- **Documentation**: Update README for new features

---

## Project Status

| Aspect | Status |
|--------|--------|
| **Version** | 6.1.19 / 2.0.0-RC4 |
| **FullCalendar** | 6.1.19 |
| **Angular** | 12-20 (aligned) |
| **Java** | 25 LTS (required) |
| **Build** | Passing |
| **License** | Apache 2.0 |
| **Maintenance** | Active |

---

## Links

- **GitHub Repository**: https://github.com/JWebMP/JWebMP
- **Maven Central**: https://mvnrepository.com/artifact/com.jwebmp.plugins/full-calendar
- **FullCalendar Home**: https://fullcalendar.io/
- **FullCalendar Docs**: https://fullcalendar.io/docs
- **FullCalendar Examples**: https://fullcalendar.io/docs#toc
- **Angular FullCalendar**: https://github.com/fullcalendar/fullcalendar-angular
- **JWebMP Home**: https://jwebmp.com/

---

## License

Licensed under the [Apache License 2.0](LICENSE).

```
Copyright 2025 JWebMP Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

## Acknowledgments

- **[FullCalendar](https://fullcalendar.io/)** — The most popular JavaScript calendar
- **[FullCalendar Angular](https://github.com/fullcalendar/fullcalendar-angular)** — Official Angular adapter
- **[JWebMP](https://jwebmp.com/)** — Server-driven web framework
- **[Angular](https://angular.dev/)** — Modern web framework

---

## Support

### For JWebMP-Specific Issues

- **GitHub Issues**: https://github.com/JWebMP/JWebMP/issues
- **Discussions**: https://github.com/JWebMP/JWebMP/discussions

### For FullCalendar Questions

- **FullCalendar Docs**: https://fullcalendar.io/docs
- **FullCalendar Support**: https://fullcalendar.io/support
- **FullCalendar Premium**: https://fullcalendar.io/pricing

---

**Made with JWebMP**
