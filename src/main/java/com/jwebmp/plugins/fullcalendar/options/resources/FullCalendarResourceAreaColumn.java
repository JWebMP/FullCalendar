package com.jwebmp.plugins.fullcalendar.options.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.guicedee.services.jsonrepresentation.IJsonRepresentation;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FullCalendarResourceAreaColumn implements IJsonRepresentation<FullCalendarResourceAreaColumn>
{
	private String field;
	private Boolean group;
	private String headerContent;
	private String width;
	private String headerClassNames;
	private String cellClassNames;
	private String cellContent;
	
	/**
	 * the property of the Resource Object where the data will come from. The data is displayed in the cell of the grid.
	 * @return
	 */
	public String getField()
	{
		return field;
	}
	
	/**
	 * the property of the Resource Object where the data will come from. The data is displayed in the cell of the grid.
	 * @param field
	 * @return
	 */
	public FullCalendarResourceAreaColumn setField(String field)
	{
		this.field = field;
		return this;
	}
	
	/**
	 * a Content Injection Input for the th at the top
	 * @return
	 */
	public String getHeaderContent()
	{
		return headerContent;
	}
	
	/**
	 * a Content Injection Input for the th at the top
	 * @param headerContent
	 * @return
	 */
	public FullCalendarResourceAreaColumn setHeaderContent(String headerContent)
	{
		this.headerContent = headerContent;
		return this;
	}
	
	/**
	 * 	the width of the column, either in a number of pixels or a string percentage like `"50%"`
	 * @return
	 */
	public String getWidth()
	{
		return width;
	}
	
	/**
	 * 	the width of the column, either in a number of pixels or a string percentage like `"50%"`
	 * @param width
	 * @return
	 */
	public FullCalendarResourceAreaColumn setWidth(String width)
	{
		this.width = width;
		return this;
	}
	
	/**
	 *
	 * a ClassName Input for the th at the top
	 * @return
	 */
	public String getHeaderClassNames()
	{
		return headerClassNames;
	}
	
	/**
	 *
	 * a ClassName Input for the th at the top
	 * @param headerClassNames
	 * @return
	 */
	public FullCalendarResourceAreaColumn setHeaderClassNames(String headerClassNames)
	{
		this.headerClassNames = headerClassNames;
		return this;
	}
	
	/**
	 *
	 * a ClassName Input for the td in the body
	 * @return
	 */
	public String getCellClassNames()
	{
		return cellClassNames;
	}
	
	/**
	 *
	 * a ClassName Input for the td in the body
	 * @param cellClassNames
	 * @return
	 */
	public FullCalendarResourceAreaColumn setCellClassNames(String cellClassNames)
	{
		this.cellClassNames = cellClassNames;
		return this;
	}
	
	/**
	 * a Content Injection Input for the <td> in the body
	 * @return
	 */
	public String getCellContent()
	{
		return cellContent;
	}
	
	/**
	 * a Content Injection Input for the <td> in the body
	 * @param cellContent
	 * @return
	 */
	public FullCalendarResourceAreaColumn setCellContent(String cellContent)
	{
		this.cellContent = cellContent;
		return this;
	}
	
	/**
	 * 	If specified as true, resources will be grouped by this column.
	 * @return
	 */
	public Boolean getGroup()
	{
		return group;
	}
	
	/**
	 * 	If specified as true, resources will be grouped by this column.
	 * @param group
	 * @return
	 */
	public FullCalendarResourceAreaColumn setGroup(Boolean group)
	{
		this.group = group;
		return this;
	}
}
