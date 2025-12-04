package com.jwebmp.plugins.fullcalendar;

/**
 * Enumeration of all available Angular ng-template slots in FullCalendar.
 * These slots allow customization of specific calendar rendering areas.
 *
 * Usage:
 * <pre>
 * NgTemplateElement template = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT.getSlotName())
 *     .withLetArg()
 *     .add("<div>{{ arg?.event?.title }}</div>");
 * calendar.add(template);
 * </pre>
 */
public enum NgTemplateSlot {
    /**
     * Customizes event rendering within calendar cells.
     * Context: { timeText: string, event: EventObject }
     * Use: Custom event display, icons, badges, colors
     */
    EVENT_CONTENT("eventContent", "Custom event rendering"),

    /**
     * Customizes day header rendering (e.g., "Mon", "Tue").
     * Context: { text: string, date: Date }
     * Use: Day header styling, custom formatting
     */
    DAY_HEADER_CONTENT("dayHeaderContent", "Day header customization"),

    /**
     * Customizes day cell rendering (the date number area).
     * Context: { date: Date }
     * Use: Date cell styling, conditional formatting
     */
    DAY_CELL_CONTENT("dayCellContent", "Date cell customization"),

    /**
     * Customizes week number rendering (e.g., "W42").
     * Context: { num: number, date: Date }
     * Use: Week number format, styling
     */
    WEEK_NUMBER_CONTENT("weekNumberContent", "Week number format"),

    /**
     * Customizes the "+X more" indicator when events overflow.
     * Context: { num: number, date: Date }
     * Use: Custom overflow indicator text and styling
     */
    MORE_LINK_CONTENT("moreLinkContent", "Overflow indicator"),

    /**
     * Customizes the message displayed when no events exist.
     * Context: { }
     * Use: Custom empty state message
     */
    NO_EVENTS_CONTENT("noEventsContent", "Empty state messaging"),

    /**
     * Customizes time slot labels in time-grid views.
     * Context: { text: string, time: Date }
     * Use: Time slot label formatting
     */
    SLOT_LABEL_CONTENT("slotLabelContent", "Time slot label customization"),

    /**
     * Customizes list view day headers.
     * Context: { date: Date }
     * Use: List view header styling and formatting
     */
    LIST_DAY_HEADER_CONTENT("listDayHeaderContent", "List view header customization");

    private final String slotName;
    private final String description;

    NgTemplateSlot(String slotName, String description) {
        this.slotName = slotName;
        this.description = description;
    }

    /**
     * Returns the Angular template reference name (e.g., "eventContent").
     * Use this value when creating NgTemplateElement instances.
     *
     * @return the template reference name
     */
    public String getSlotName() {
        return slotName;
    }

    /**
     * Returns a human-readable description of this template slot.
     *
     * @return description of the slot's purpose
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the enable flag field name for this template slot.
     * Example: EVENT_CONTENT -> "enableEventContentTemplate"
     *
     * @return the enable flag field name
     */
    public String getEnableFlagName() {
        // Convert enum name to camelCase enable flag
        // EVENT_CONTENT -> enableEventContentTemplate
        String[] parts = name().split("_");
        StringBuilder sb = new StringBuilder("enable");
        for (String part : parts) {
            sb.append(part.charAt(0)).append(part.substring(1).toLowerCase());
        }
        sb.append("Template");
        return sb.toString();
    }

    /**
     * Gets the enum constant for a given slot name.
     * Example: getBySlotName("eventContent") returns EVENT_CONTENT
     *
     * @param slotName the slot name (e.g., "eventContent")
     * @return the matching NgTemplateSlot, or null if not found
     */
    public static NgTemplateSlot getBySlotName(String slotName) {
        if (slotName == null) {
            return null;
        }
        for (NgTemplateSlot slot : values()) {
            if (slot.slotName.equals(slotName)) {
                return slot;
            }
        }
        return null;
    }
}
