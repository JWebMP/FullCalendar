package com.jwebmp.plugins.fullcalendar.implementations;

import com.guicedee.client.services.config.IGuiceScanModuleExclusions;

import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class FullCalendarExclusionsModule
		implements IGuiceScanModuleExclusions<FullCalendarExclusionsModule>
{
	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.plugins.fullcalendar");
		return strings;
	}
}
