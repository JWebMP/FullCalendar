# Sequence – Receive Client Event

```mermaid
sequenceDiagram
    participant Browser as Browser
    participant FCJS as FullCalendar JS
    participant FC as FullCalendar Component
    participant EventModel as Event Payload
    participant Host as Host Listener

    Browser->>FCJS: User clicks/selects/resizes event
    FCJS-->>FC: Emit event data via generated callback
    FC->>EventModel: Map payload to event POJO (e.g., FullCalendarEventInfo)
    FC->>Host: Invoke registered listener (test/examples drive rendering)
    Host-->>FC: Optionally mutate options/events
```

Notes: Event payload classes under `src/main/java/com/jwebmp/plugins/fullcalendar/events` and usage in tests guide the mapping; actual transport wiring is handled by JWebMP/Angular bridges.
