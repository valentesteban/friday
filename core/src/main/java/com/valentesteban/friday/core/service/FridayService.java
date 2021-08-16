package com.valentesteban.friday.core.service;

import com.valentesteban.friday.core.service.impl.ListenerService;
import com.valentesteban.friday.api.logger.Logger;
import com.valentesteban.friday.api.service.Service;

public class FridayService implements Service {

    private final ListenerService listenerService = new ListenerService();

    @Override
    public void start() {
        listenerService.start();

        Logger.sendConsoleMessage("All the services has been successfully initialized!");
    }
}