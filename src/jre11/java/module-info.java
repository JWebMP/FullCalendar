module com.jwebmp.plugins.fullcalendar {
	exports com.jwebmp.plugins.fullcalendar;
	exports com.jwebmp.plugins.fullcalendar.options;
	exports com.jwebmp.plugins.fullcalendar.options.enumerations;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;
	requires java.logging;
	requires com.jwebmp.guicedinjection;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;

	provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;

	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.plugins.fullcalendar.implementations.FullCalendarExclusionsModule;
	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions with com.jwebmp.plugins.fullcalendar.implementations.FullCalendarExclusionsModule;

	opens com.jwebmp.plugins.fullcalendar to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.fullcalendar.options to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.fullcalendar.options.enumerations to com.fasterxml.jackson.databind, com.jwebmp.core;
}
