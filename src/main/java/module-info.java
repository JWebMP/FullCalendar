import com.jwebmp.plugins.fullcalendar.implementations.FullCalendarInclusionModule;

module com.jwebmp.plugins.fullcalendar {
    exports com.jwebmp.plugins.fullcalendar;
    exports com.jwebmp.plugins.fullcalendar.options;
    exports com.jwebmp.plugins.fullcalendar.options.enumerations;

    requires transitive com.jwebmp.core.base.angular.client;
    requires com.jwebmp.client;
    requires com.jwebmp.core;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.guicedee.jsonrepresentation;
    requires com.guicedee.vertx;
    requires com.jwebmp.core.angular;

    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;

    provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions with FullCalendarInclusionModule;


    opens com.jwebmp.plugins.fullcalendar to com.fasterxml.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.fullcalendar.options to com.fasterxml.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.fullcalendar.options.enumerations to com.fasterxml.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.events;
    opens com.jwebmp.plugins.fullcalendar.events to com.fasterxml.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.views;
    opens com.jwebmp.plugins.fullcalendar.options.views to com.fasterxml.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.views.defaults;
    opens com.jwebmp.plugins.fullcalendar.options.views.defaults to com.fasterxml.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.toolbars;
    opens com.jwebmp.plugins.fullcalendar.options.toolbars to com.fasterxml.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.titles;
    opens com.jwebmp.plugins.fullcalendar.options.titles to com.fasterxml.jackson.databind, com.jwebmp.core;
    exports com.jwebmp.plugins.fullcalendar.options.resources;
    opens com.jwebmp.plugins.fullcalendar.options.resources to com.fasterxml.jackson.databind, com.jwebmp.core;
}
