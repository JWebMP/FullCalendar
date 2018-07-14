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
}
