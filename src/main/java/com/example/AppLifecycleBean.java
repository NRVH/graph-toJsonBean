package com.example;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean
{
    private static final Logger  LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent evt)
    {
        LOGGER.info("La aplicacion esta iniciando ....");
    }

    void onStop(@Observes ShutdownEvent evt)
    {
        LOGGER.info("La aplicacion se detuvo ....");
    }
}
