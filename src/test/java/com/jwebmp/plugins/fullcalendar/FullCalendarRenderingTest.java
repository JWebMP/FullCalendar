package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.plugins.fullcalendar.events.FullCalendarEventClickEvent;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestFullCalendar extends FullCalendar<TestFullCalendar> {
    public TestFullCalendar(String id) {
        super(id);
    }
}

public class FullCalendarRenderingTest
{
    @Test
    public void preservesCustomJsCallbacksAndDoesNotOverrideEditableSelectable()
    {
        TestFullCalendar fc = new TestFullCalendar("fc1");
        FullCalendarOptions opts = fc.getOptions();

        // Preconfigure options with user-provided callbacks and flags
        opts.setEventClick("(arg) => { console.log('custom click'); }");
        opts.setSelectable(false);
        // Explicitly set editable to false to ensure it is not overridden
        opts.setEditable(false);

        // Also attach JWebMP handler class – should NOT be bound because custom JS is present
        fc.setEventClickEvent(new FullCalendarEventClickEvent() {
            @Override
            public void onEventClick(com.jwebmp.core.base.ajax.AjaxCall<?> call,
                                     com.jwebmp.core.base.ajax.AjaxResponse<?> response,
                                     com.jwebmp.plugins.fullcalendar.events.FullCalendarEventInfo selectEvent)
            {
                // no-op
            }
        });

        // Call the component init which wires up options based on provided settings
        fc.init();

        // Assert that our custom JS is preserved and not replaced by built-in handler binding
        assertEquals("(arg) => { console.log('custom click'); }", opts.getEventClick(), "Custom eventClick JS should be preserved");

        // Ensure our explicit flags remain unchanged (no overriding to true)
        assertEquals(Boolean.FALSE, opts.getEditable(), "editable should not be overridden");
        assertEquals(Boolean.FALSE, opts.getSelectable(), "selectable should not be overridden");
    }

    @Test
    public void bindsBuiltInHandlersWhenNoCustomJsProvidedAndSetsDefaultsSafely()
    {
        TestFullCalendar fc = new TestFullCalendar("fc2");
        FullCalendarOptions opts = fc.getOptions();

        // No custom JS set; attach JWebMP event handler to trigger built-in binding
        fc.setEventClickEvent(new FullCalendarEventClickEvent() {
            @Override
            public void onEventClick(com.jwebmp.core.base.ajax.AjaxCall<?> call,
                                     com.jwebmp.core.base.ajax.AjaxResponse<?> response,
                                     com.jwebmp.plugins.fullcalendar.events.FullCalendarEventInfo selectEvent)
            {
                // no-op
            }
        });

        // Do not pre-set editable/selectable to verify safe defaulting logic
        assertNull(opts.getEditable(), "Precondition: editable should initially be null");
        assertNull(opts.getSelectable(), "Precondition: selectable should initially be null");

        fc.init();

        // Expect the built-in binding to be applied
        assertEquals("this.handleEventClick.bind(this)", opts.getEventClick(), "Built-in eventClick handler should be bound");

        // editable should be set to true when binding occurs
        assertEquals(Boolean.TRUE, opts.getEditable(), "editable should default to true when handlers are bound");

        // selectable should remain null unless select/dateClick handlers require it
        assertNull(opts.getSelectable(), "selectable should remain null when not needed");
    }
}
