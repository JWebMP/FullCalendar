package com.jwebmp.plugins.fullcalendar.options.titles;

public enum FullCalendarTitleFormatTypes
{
	numeric,
	$2_digit,
	narrow,
	/**
	 * for 12 hour clock
	 */
	$true,
	/**
	 * for 24 hour clock
	 */
	$false,
	$long,
	$short;
	
	@Override
	public String toString()
	{
		return name().replace('$', ' ')
		             .replace('_', '-')
		             .trim();
	}
}
