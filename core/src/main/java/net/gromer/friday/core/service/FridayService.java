package net.gromer.friday.core.service;

import net.gromer.friday.api.logger.Logger;
import net.gromer.friday.api.service.Service;
import net.gromer.friday.core.service.impl.ListenerService;

public class FridayService implements Service {

    private final ListenerService listenerService = new ListenerService();

    @Override
    public void start() {
        listenerService.start();

        Logger.sendConsoleMessage("All the services has been successfully initialized!");
    }
}