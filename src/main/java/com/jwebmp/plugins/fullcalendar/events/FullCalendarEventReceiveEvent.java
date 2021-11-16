package com.jwebmp.plugins.fullcalendar.events;

import com.guicedee.guicedinjection.json.*;
import com.jwebmp.core.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.*;
import com.jwebmp.core.events.click.*;

public abstract class FullCalendarEventReceiveEvent extends ClickAdapter<FullCalendarEventReceiveEvent>
{
	public FullCalendarEventReceiveEvent()
	{
	}
	
	public abstract void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarEventInfo selectEvent);
	
	@Override
	public void onClick(AjaxCall<?> call, AjaxResponse<?> response)
	{
	
	}
	
	@Override
	protected void onCreate()
	{
		Event<?,?> e = this;
		String command = //"jwCntrl.jw.isLoading || " +
				com.jwebmp.core.utilities.StaticStrings.STRING_ANGULAR_EVENT_START +
				e.renderVariables() +
				StaticStrings.STRING_CLOSING_BRACKET_SEMICOLON;
		
		if (e.getComponent().asAttributeBase()
		     .getAttribute(String.valueOf(AngularAttributes.ngClick)) == null)
		{
			e.getComponent().asAttributeBase()
			 .addAttribute(String.valueOf(AngularAttributes.ngClick), command);
		}
		else
		{
			e.getComponent().asAttributeBase()
			 .addAttribute(String.valueOf(AngularAttributes.ngClick), e.getComponent().asAttributeBase()
			                                                           .getAttribute(String.valueOf(AngularAttributes.ngClick)) + command);
		}
		
		if (e.getComponent().asAttributeBase()
		     .getAttribute(String.valueOf(AngularAttributes.ngDisabled)) == null)
		{
			e.getComponent().asAttributeBase()
			 .addAttribute(String.valueOf(AngularAttributes.ngDisabled), "jwCntrl.jw.isLoading");
		}
		else if (!"jwCntrl.jw.isLoading".equals(e.getComponent().asAttributeBase()
		                                         .getAttribute(String.valueOf(AngularAttributes.ngDisabled))))
		{
			String disabledOn = "jwCntrl.jw.isLoading ";
			if (!"".equals(e.getComponent().asAttributeBase()
			                .getAttribute(String.valueOf(AngularAttributes.ngDisabled))) && e.getComponent().asAttributeBase()
			                                                                                 .getAttribute(String.valueOf(AngularAttributes.ngDisabled)) != null)
			{
				disabledOn += " || " + e.getComponent().asAttributeBase()
				                        .getAttribute(String.valueOf(AngularAttributes.ngDisabled));
			}
			e.getComponent().asAttributeBase()
			 .addAttribute(String.valueOf(AngularAttributes.ngDisabled), disabledOn.trim());
		}
	}
}
