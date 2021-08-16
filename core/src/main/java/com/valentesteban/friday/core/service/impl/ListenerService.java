package com.valentesteban.friday.core.service.impl;

import com.valentesteban.friday.core.Friday;
import com.valentesteban.friday.core.chat.ChatListener;
import com.valentesteban.friday.core.listener.player.PlayerJoinListener;
import com.valentesteban.friday.core.listener.player.PlayerQuitListener;
import com.valentesteban.friday.core.verification.VerificationListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import com.valentesteban.friday.api.logger.Logger;
import com.valentesteban.friday.api.service.Service;

public class ListenerService implements Service {

    private final ChatListener chatListener = new ChatListener();
    private final PlayerJoinListener playerJoinListener = new PlayerJoinListener();
    private final PlayerQuitListener playerQuitListener = new PlayerQuitListener();
    private final VerificationListener verificationListener = new VerificationListener();

    @Override
    public void start() {
        registerListeners(
            chatListener,
            playerJoinListener,
            playerQuitListener,
            verificationListener
        );

        Logger.sendConsoleMessage("The listener service has been initialized.");
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, Friday.getInstance());
        }
    }
}