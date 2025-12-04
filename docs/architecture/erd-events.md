# ERD – Calendar Domain Objects

```mermaid
erDiagram
    FullCalendarOptions ||--o{ FullCalendarEvent : manages
    FullCalendarOptions ||--o{ FullCalendarView : defines
    FullCalendarOptions ||--o{ FullCalendarResourceItem : references
    FullCalendarOptions ||--|| FullCalendarVisibleRange : uses
    FullCalendarOptions ||--|| FullCalendarBusinessHours : uses
    FullCalendarEvent }o--|| FullCalendarResourceItem : assigned_to
    FullCalendarEvent ||--|| FullCalendarTimeSlot : spans
    FullCalendarView ||--|| FullCalendarTitleFormat : displays
    FullCalendarView ||--|| FullCalendarHeaderToolBarOptions : configures
    FullCalendarEventSource ||--o{ FullCalendarEvent : produces
```

Interpretation
- Classes derived from option/event models under `src/main/java/com/jwebmp/plugins/fullcalendar/options/**`.
- Relationships are logical (in-memory configuration) rather than persisted database tables; adjust if host apps introduce persistence.
