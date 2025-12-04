# C4 – Context

```mermaid
flowchart TD
    Dev[Host developer] --> App[Host JWebMP app]
    App --> Plugin[FullCalendar plugin library]
    Plugin --> JWebMPCore[JWebMP Core + Angular wrappers]
    Plugin --> Guice[GuicedEE Client runtime]
    Plugin --> FullCalendarJS[FullCalendar JS assets]
    Browser[Web browser] --> App
    App --> Browser
    FullCalendarJS --> Browser
```

Notes
- Scope is a server-rendered JWebMP library consumed by host apps; browser executes the bundled FullCalendar JavaScript.
- External integrations (BrowserStack, CI, etc.) are documented separately; unknown runtime hosting details remain open.
