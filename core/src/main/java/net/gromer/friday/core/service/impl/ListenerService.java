package net.gromer.friday.core.service.impl;

import net.gromer.friday.core.Friday;
import net.gromer.friday.core.chat.ChatListener;
import net.gromer.friday.core.listener.player.PlayerJoinListener;
import net.gromer.friday.core.listener.player.PlayerQuitListener;
import net.gromer.friday.core.verification.VerificationListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import net.gromer.friday.api.logger.Logger;
import net.gromer.friday.api.service.Service;

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