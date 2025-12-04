# Sequence – Render Calendar Page

```mermaid
sequenceDiagram
    participant Dev as Host Developer
    participant Page as JWebMP Page
    participant FC as FullCalendar Component
    participant Opt as FullCalendarOptions
    participant Config as FullCalendarPageConfigurator
    participant Browser as Browser

    Dev->>Page: Add FullCalendar component to page
    Page->>FC: Instantiate component with options/events
    FC->>Opt: Build options (views, resources, toolbars)
    FC->>Config: Register scripts/css via service loader
    Page-->>Browser: Deliver rendered HTML/JS
    Browser-->>FC: Execute FullCalendar JS with serialized options
```

Notes: Based on component construction and service registrations in `module-info.java` and `META-INF/services/`.
