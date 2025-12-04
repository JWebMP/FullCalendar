# C4 – Container

```mermaid
flowchart TD
    subgraph Library[FullCalendar Plugin (this repo)]
        FCComponent[FullCalendar component]
        FCOptions[Options + Events + Views models]
        PageConfig[FullCalendarPageConfigurator]
        GuiceModules[Inclusion/Exclusion modules]
    end

    subgraph HostApp[Host JWebMP Application]
        Pages[Server-rendered pages using FullCalendar]
        Services[Custom data providers]
    end

    subgraph Runtime[Runtime Platform]
        JWebMPCore[JWebMP Core + Angular bridge]
        GuicedEE[GuicedEE Client]
        Browser[Browser executing FullCalendar JS]
    end

    Pages --> FCComponent
    FCComponent --> FCOptions
    FCComponent --> PageConfig
    FCComponent --> GuiceModules
    Library --> JWebMPCore
    Library --> GuicedEE
    JWebMPCore --> Browser
    Browser --> FullCalendarJS[FullCalendar JS assets]
    Services --> FCOptions
```

Assumptions
- Host application wiring (routing, data sources) is outside this library; integration points are the component APIs and service loader bindings seen in `module-info.java` and `META-INF/services/`.
