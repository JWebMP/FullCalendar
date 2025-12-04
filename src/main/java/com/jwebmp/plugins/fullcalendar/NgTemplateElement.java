package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import jakarta.validation.constraints.NotNull;

/**
 * Lightweight wrapper to emit an Angular <ng-template> block as part of the
 * generated component template. This element implements FullCalendarChildren
 * so it can be added to the FullCalendar component.
 *
 * Usage:
 * <pre>
 * // Using enum for type safety (PREFERRED)
 * NgTemplateElement template = new NgTemplateElement(NgTemplateSlot.EVENT_CONTENT)
 *     .withLetArg()
 *     .add("<div>{{ arg?.event?.title }}</div>");
 * calendar.add(template);
 *
 * // Or using string directly
 * NgTemplateElement template = new NgTemplateElement("eventContent")
 *     .withLetArg()
 *     .add("<div>{{ arg?.event?.title }}</div>");
 * calendar.add(template);
 * </pre>
 */
public class NgTemplateElement extends DivSimple<NgTemplateElement> implements FullCalendarChildren
{
    public NgTemplateElement()
    {
        setTag("ng-template");
    }

    /**
     * Creates an ng-template with the specified slot enum.
     * This is the preferred constructor for type safety.
     *
     * @param slot the NgTemplateSlot enum value
     */
    public NgTemplateElement(@NotNull NgTemplateSlot slot)
    {
        this();
        if (slot != null)
        {
            addAttribute("#" + slot.getSlotName(), "");
        }
    }

    /**
     * Creates an ng-template with the specified template reference name.
     * Use NgTemplateSlot enum when possible for type safety.
     *
     * @param templateRefName the template reference name (e.g., "eventContent")
     */
    public NgTemplateElement(String templateRefName)
    {
        this();
        if (templateRefName != null && !templateRefName.isEmpty())
        {
            addAttribute("#" + templateRefName, "");
        }
    }

    /**
     * Adds the standard Angular local variable binding used by FullCalendar templates: let-arg
     */
    public NgTemplateElement withLetArg()
    {
        addAttribute("let-arg", "");
        return this;
    }

    /**
     * Add raw inner HTML/text into the template.
     */
    @Override
    public @NotNull NgTemplateElement add(@NotNull String textToAdd)
    {
        return super.add(textToAdd);
    }

    /**
     * Add child components as inner content of the template.
     */
    @Override
    public @NotNull NgTemplateElement add(@NotNull GlobalChildren newChild)
    {
        return super.add(newChild);
    }
}
