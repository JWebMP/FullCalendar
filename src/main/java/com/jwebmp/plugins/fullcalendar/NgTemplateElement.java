package com.jwebmp.plugins.fullcalendar;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import jakarta.validation.constraints.NotNull;

/**
 * Lightweight wrapper to emit an Angular <ng-template> block as part of the
 * generated component template. This element implements FullCalendarChildren
 * so it can be added to the FullCalendar component.
 */
public class NgTemplateElement extends DivSimple<NgTemplateElement> implements FullCalendarChildren
{
    public NgTemplateElement()
    {
        setTag("ng-template");
    }

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
