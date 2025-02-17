/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.fullcalendar;

import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.angular.client.services.interfaces.INgApp;
import com.jwebmp.core.base.angular.services.compiler.JWebMPTypeScriptCompiler;
import com.jwebmp.plugins.fullcalendar.options.FullCalendarEventsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils.getTsFilename;
import static com.jwebmp.core.base.angular.client.services.interfaces.IComponent.getClassDirectory;

/**
 * @author GedMarc
 */
public class FullCalendarEventsListTest
{

    public static void main(String[] args)
    {
        IGuiceContext.instance().inject();
    }
    

    public FullCalendarEventsListTest()
    {
    }

    @Test
    public void testAppSearch() throws IOException
    {
        IGuiceContext.instance()
                .inject();
        for (INgApp<?> app : JWebMPTypeScriptCompiler.getAllApps())
        {
            JWebMPTypeScriptCompiler compiler = new JWebMPTypeScriptCompiler(app);

            System.out.println("Generating @NgApp (" + getTsFilename(app.getClass()) + ") " +
                    "in folder " + getClassDirectory(app.getClass()));
            System.out.println("================");
            //	compiler.renderAppTS(app);
            System.out.println("================");
        }
    }

    @Test
    public void testSomeMethod()
    {
        System.out.println(new FullCalendarEventsList());
    }

}
