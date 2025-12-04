# Implementation Plan – JWebMP FullCalendar

## Current Layout
- Module: `com.jwebmp.plugins.fullcalendar` (JPMS) exporting components/options/events; service loaders under `META-INF/services/**` bind `FullCalendarPageConfigurator` and Guice inclusion.
- Packages:
  - `com.jwebmp.plugins.fullcalendar` – core component, attributes, features, configurator, Ng template helper.
  - `com.jwebmp.plugins.fullcalendar.events` – event payload classes.
  - `com.jwebmp.plugins.fullcalendar.options` (+ subpackages: enumerations, resources, titles, toolbars, views/defaults) – configuration models.
  - `com.jwebmp.plugins.fullcalendar.implementations` – Guice inclusion/exclusion modules.
- Tests/examples: `src/test/java/com/jwebmp/plugins/fullcalendar/**` cover rendering, events, and example usage.

## Completed Work
1. **FullCalendar v6.1.19 API Alignment** (forward-only approach):
   - Removed deprecated `eventLimit` option (replaced by v6 moreLinkClick/Text/Hint)
   - Changed `initialView` from object type to String (v6 API standard)
   - Added 9 missing core v6 options: locale, locales, timeZone, direction, firstDay, defaultAllDayEventDuration, defaultTimedEventDuration, defaultAllDay, now
   - See: `docs/FULLCALENDAR_API_AUDIT.md` and `docs/FULLCALENDAR_V6_ALIGNMENT_CHANGES.md`

2. Documentation (completed): PACT, RULES, GUIDES, GLOSSARY, PROMPT_REFERENCE, architecture diagrams.

## Planned Work (Forward-Only)
1. Environment and CI scaffolding: add `.env.example` aligned to secrets-config rules; add GitHub Actions Maven workflow with Java 25.
2. AI workspace alignment: `.aiassistant/rules/`, `.github/copilot-instructions.md`, `.mcp.json`.
3. README refresh: state Rules Repository adoption, link PACT/RULES/GUIDES/IMPLEMENTATION/GLOSSARY and diagrams.
4. Future code changes (if requested):
   - Additional v6 options: eventClick/dateClick callbacks, eventDisplay, customButtons, visibleRange improvements
   - CRTP fluent API alignment for complex option builders
   - Log4j2 logging posture enhancements
   - Expand test coverage with Java 25 practices per testing rules

## Changes Detail
- **FullCalendarOptions.java**:
  - Removed: eventLimit field (Boolean), getEventLimit(), setEventLimit()
  - Modified: initialView field (FullCalendarView<?> → String), getter/setter updated
  - Added: locale, locales, timeZone, direction, firstDay, defaultAllDayEventDuration, defaultTimedEventDuration, defaultAllDay, now (with full getter/setter pairs)

## Rollout and Validation
- Build: `mvn -B -ntp verify`.
- Static checks: ensure JPMS module exports match `IMPLEMENTATION.md`; verify service loader entries after changes.
- Docs: keep `docs/architecture/README.md` and cross-links up to date when components change.
- Testing: lean on existing tests; expand with Java Micro Harness if functionality changes; capture coverage via Jacoco.
- Browser behavior: rely on BrowserStack alignment referenced in README and testing rules.
- API Alignment: verify JSON serialization and v6 API compatibility in integration tests.

## Migration Notes
Users must update code using `initialView`:
```java
// OLD (v5 style)
options.setInitialView(new FullCalendarViewDayGridMonth<>());

// NEW (v6 style)
options.setInitialView("dayGridMonth");
```

For `eventLimit`, migrate to v6 options:
- Use `moreLinkClick`, `moreLinkText`, `moreLinkHint` for more-events popup control

## Links
- API Audit: `docs/FULLCALENDAR_API_AUDIT.md`
- API Changes: `docs/FULLCALENDAR_V6_ALIGNMENT_CHANGES.md`
- Diagrams: `docs/architecture/README.md`
- Guides: `GUIDES.md`
- Rules: `RULES.md`
- Glossary: `GLOSSARY.md`
- Prompt seed: `docs/PROMPT_REFERENCE.md`
- FullCalendar Official Docs: https://fullcalendar.io/docs

