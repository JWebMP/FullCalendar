# Guides – How to Apply the Rules

## Getting Started
- Read `PACT.md` and `RULES.md` for policies (forward-only, document-modular, CRTP, Log4j2).
- Load `docs/PROMPT_REFERENCE.md` for stack selections and rule links.
- Review architecture sources: `docs/architecture/README.md` (context, container, component, sequence, ERD).

## Build and Tooling
- Java 25 LTS + Maven: follow `rules/generative/language/java/java-25.rules.md` and `build-tooling.md`.
- JPMS/GuicedEE scanning: keep exports and service registrations consistent with `module-info.java` and `META-INF/services/`.
- Logging: align with `rules/generative/backend/logging/README.md` (Log4j2-first).

## Component Usage (JWebMP FullCalendar)
- JWebMP + Angular bridge: see `rules/generative/frontend/jwebmp/README.md` and `rules/generative/frontend/jwebmp/angular/README.md`.
- Angular 20 specifics: `rules/generative/language/angular/angular-20.rules.md` (pair with base Angular rules).
- Options/events/views models: reference component diagram `docs/architecture/c4-component-fullcalendar.md` and sequences (`sequence-render.md`, `sequence-event.md`) when wiring callbacks.
- Fluent API: apply CRTP chaining per `rules/generative/backend/fluent-api/crtp.rules.md`; avoid builders.
- TypeScript bindings/client integration: `rules/generative/frontend/jwebmp/typescript/README.md`.

## Testing and Quality
- Coverage and unit testing: `rules/generative/platform/testing/jacoco.rules.md`, `rules/generative/platform/testing/java-micro-harness.rules.md`.
- Browser coverage: `rules/generative/platform/testing/browserstack.rules.md` (align with README references).
- TDD posture and docs-first: `rules/generative/architecture/tdd/README.md`.

## FullCalendar v6.1.19 API Alignment
- **Version**: JWebMP FullCalendar now aligns with FullCalendar v6.1.19 using forward-only approach.
- **Removed Deprecated**: `eventLimit` (v5 legacy); use `moreLinkClick`, `moreLinkText`, `moreLinkHint` instead.
- **Type Changes**: `initialView` changed from object (FullCalendarView<?>) to String ("dayGridMonth", "timeGridWeek", etc.). See migration guide below.
- **New Options** (v6 additions):
  - **Localization**: `locale` (String), `locales` (List<String>) for multi-language support.
  - **Timezone**: `timeZone` (IANA string, e.g., "America/New_York") for explicit timezone display.
  - **RTL Support**: `direction` ("ltr" or "rtl") for right-to-left layouts.
  - **Week Configuration**: `firstDay` (Integer, 0-6) alternative to hiddenDays.
  - **Event Defaults**: `defaultAllDayEventDuration`, `defaultTimedEventDuration`, `defaultAllDay` for new event creation.
  - **Dynamic Now**: `now` (String/Date) to override system time reference.
- **Documentation**: Full details in `docs/FULLCALENDAR_V6_ALIGNMENT_CHANGES.md` with examples and testing guidance.

## Angular Integration (Official Angular Plugin v6 Alignment)
- **Official Plugin**: JWebMP FullCalendar is compatible with `@fullcalendar/angular` (v12-20 support).
- **Architecture**: The Angular plugin is a **thin wrapper** around `@fullcalendar/core`. JWebMP's `FullCalendarOptions` directly maps to the core `CalendarOptions` interface.
- **Data Flow**: JWebMP (Java) → `FullCalendarOptions` → JSON serialization → Angular `CalendarOptions` interface → FullCalendar component.
- **Key Documentation**:
  - **Analysis**: `docs/ANGULAR_ALIGNMENT_ANALYSIS.md` – Architecture overview, alignment points, and alignment checklist.
  - **Integration Guide**: `docs/ANGULAR_INTEGRATION_GUIDE.md` – Practical setup, use cases, event handling patterns, and troubleshooting.
- **Quick Pattern**:
  ```java
  // Backend (JWebMP)
  @RestController
  public FullCalendarOptions getCalendarOptions() {
    return new FullCalendarOptions()
        .setInitialView("dayGridMonth")
        .setLocale("en")
        .setTimeZone("UTC")
        .setEditable(true)
        .setEvents(eventService.getEvents());
  }
  ```
  ```typescript
  // Frontend (Angular 12-20)
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
  ```
  ```html
  <!-- Angular template -->
  <full-calendar [options]="calendarOptions"></full-calendar>
  ```
- **Template Customization**: Use Angular's `ng-template` with `@ContentChild` for custom event/day rendering (independent of JWebMP backend).
- **Next Phases**:
  - Phase 2: Event mutation callbacks (eventDrop, eventResize, eventDragStop, etc.)
  - Phase 3: Advanced view/constraint options
  - Phase 4: Premium resource scheduler support

### Migration from v5 to v6 API
```java
// OLD (v5 - no longer supported)
options.setInitialView(new FullCalendarViewDayGridMonth<>());
options.setEventLimit(true);

// NEW (v6 - current standard)
options.setInitialView("dayGridMonth");
// For more-events popup, use:
// options.put("moreLinkClick", "popover");
// options.put("moreLinkText", "+%d more");

// NEW: Localization
options.setLocale("es");
options.setLocales(Arrays.asList("es", "en"));

// NEW: Timezone
options.setTimeZone("America/New_York");

// NEW: Event defaults
options.setDefaultAllDay(false);
options.setDefaultTimedEventDuration("01:00:00");
```

### Common View Names (String-based in v6)
- `dayGridMonth` – Month view (default)
- `timeGridWeek` – Week view with time slots
- `timeGridDay` – Day view with time slots
- `dayGridWeek` – Week view (no times)
- `dayGridDay` – Day view (no times)
- `listMonth` – List view for month
- `resourceTimelineDay` – Timeline with resources (premium)

## CI/CD and Environment
- GitHub Actions guidance: `rules/generative/platform/ci-cd/providers/github-actions.md`.
- Secrets/env alignment: `rules/generative/platform/secrets-config/env-variables.md`; mirror keys in `.env.example`.
- Observability/security (if added): use `rules/generative/platform/observability/README.md` and `rules/generative/platform/security-auth/README.md` as needed.

## Traceability
- Implementation details: `IMPLEMENTATION.md` (module map, rollout plan) with links back to guides and diagrams.
- Glossary alignment: `GLOSSARY.md` (topic-first) before naming APIs or components.
