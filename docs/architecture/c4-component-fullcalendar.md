# C4 – Component (FullCalendar Plugin)

```mermaid
flowchart TD
    FCComponent[FullCalendar.java<br/>Main component extending BaseUIComponent]
    FCFeatures[FullCalendarFeature + Features registrar]
    FCChildren[FullCalendarChildren + Events container]
    FCOptions[FullCalendarOptions + nested option models]
    FCEvents[Event/Select/Drop/Resize event payloads]
    FCViews[Views + defaults + title formats + toolbars]
    Resources[Resource items/columns for scheduler views]
    PageConfig[FullCalendarPageConfigurator<br/>binds scripts/css]
    GuiceInclude[FullCalendarInclusionModule]
    GuiceExclude[FullCalendarExclusionsModule]
    NgTemplate[NgTemplateElement helper]

    FCComponent --> FCFeatures
    FCComponent --> FCChildren
    FCComponent --> FCOptions
    FCOptions --> FCEvents
    FCOptions --> FCViews
    FCOptions --> Resources
    FCComponent --> PageConfig
    PageConfig --> FCFeatures
    FCComponent --> NgTemplate
    GuiceInclude --> FCComponent
    GuiceExclude --> FCComponent
```

Evidence
- `src/main/java/com/jwebmp/plugins/fullcalendar/**` for components/options/events.
- `module-info.java` exports and service bindings; `META-INF/services/**` registers configurators.
- Tests under `src/test/java/...` exercise rendering, events, and examples.
