import com.guicedee.client.services.config.IGuiceScanModuleInclusions;
import com.jwebmp.plugins.fullcalendar.implementations.FullCalendarInclusionModule;

module com.jwebmp.plugins.fullcalendar {
    exports com.jwebmp.plugins.fullcalendar;
    exports com.jwebmp.plugins.fullcalendar.options;
    exports com.jwebmp.plugins.fullcalendar.options.enumerations;

    requires transitive com.jwebmp.core.base.angular.client;
    requires com.jwebmp.client;
    requires com.jwebmp.core;

    requires tools.jackson.databind;
    requires tools.jackson.core;
    requires com.guicedee.jsonrepresentation;
    requires com.guicedee.vertx;
    requires com.jwebmp.core.angular;
    requires static lombok;
    requires com.fasterxml.jackson.annotation;

    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;

    provides IGuiceScanModuleInclusions with FullCalendarInclusionModule;


    opens com.jwebmp.plugins.fullcalendar to tools.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.fullcalendar.options to tools.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.fullcalendar.options.enumerations to tools.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.events;
    opens com.jwebmp.plugins.fullcalendar.events to tools.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.views;
    opens com.jwebmp.plugins.fullcalendar.options.views to tools.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.views.defaults;
    opens com.jwebmp.plugins.fullcalendar.options.views.defaults to tools.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.toolbars;
    opens com.jwebmp.plugins.fullcalendar.options.toolbars to tools.jackson.databind, com.jwebmp.core;

    exports com.jwebmp.plugins.fullcalendar.options.titles;
    opens com.jwebmp.plugins.fullcalendar.options.titles to tools.jackson.databind, com.jwebmp.core;
    exports com.jwebmp.plugins.fullcalendar.options.resources;
    opens com.jwebmp.plugins.fullcalendar.options.resources to tools.jackson.databind, com.jwebmp.core;
}
