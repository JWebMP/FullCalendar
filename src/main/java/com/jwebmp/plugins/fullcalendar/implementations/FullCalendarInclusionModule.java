package com.jwebmp.plugins.fullcalendar.implementations;

import com.guicedee.client.services.config.IGuiceScanModuleInclusions;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class FullCalendarInclusionModule implements IGuiceScanModuleInclusions<FullCalendarInclusionModule>
{
	@Override
	public @NotNull Set<String> includeModules()
	{
		Set<String> set = new HashSet<>();
		set.add("com.jwebmp.plugins.fullcalendar");
		return set;
	}
}