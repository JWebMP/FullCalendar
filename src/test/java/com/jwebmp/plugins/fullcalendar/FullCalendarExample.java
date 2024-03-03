package com.jwebmp.plugins.fullcalendar;

import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.guicedservlets.undertow.GuicedUndertow;
import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.IComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgApp;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.services.compiler.JWebMPTypeScriptCompiler;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.fullcalendar.events.FullCalendarSelectEventTest;

import java.io.IOException;

import static com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils.getTsFilename;

@NgComponent(value = "full-calendar-example")
public class FullCalendarExample extends DivSimple<FullCalendarExample>
        implements INgComponent<FullCalendarExample>
{
    @Override
    public void init()
    {
        add(new FullCalendarComponentExample().setSelectEvent(new FullCalendarSelectEventTest()));
        super.init();
    }

    public static void main(String[] args) throws Exception
    {

        GuicedUndertow.boot("localhost", 6524);
    }

    public void testAppSearch() throws IOException
    {
        GuiceContext.instance()
                    .inject();
        for (INgApp<?> app : JWebMPTypeScriptCompiler.getAllApps())
        {
            JWebMPTypeScriptCompiler compiler = new JWebMPTypeScriptCompiler(app);

            System.out.println("Generating @NgApp (" + getTsFilename(app.getClass()) + ") " +
                    "in folder " + IComponent.getClassDirectory(app.getClass()));
            System.out.println("================");
            //	compiler.renderAppTS(app);
            System.out.println("================");
        }
    }
}
