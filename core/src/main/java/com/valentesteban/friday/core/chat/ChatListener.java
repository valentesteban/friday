package com.valentesteban.friday.core.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.valentesteban.friday.api.logger.Logger;
import com.valentesteban.friday.api.timer.Timer;
import com.valentesteban.friday.api.timer.impl.DoubleTimer;
import com.valentesteban.friday.core.Friday;

import java.util.List;

import static com.valentesteban.friday.api.util.ChatUtil.translate;

public class ChatListener implements Listener {

    private final Friday plugin = Friday.getInstance();

    private Timer chatCooldownTimer;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // put the chat format with the ranks, prefix and everything
        // TODO: NEED TO IMPLEMENT A PERMISSION CORE LIKE LUCKPERMS FOR EXAMPLE
        event.setFormat(translate(player.getName() + "&7: &f" + message));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChatCooldown(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Timer timer = getChatCooldownTimer();

        if (plugin.getChatFile().getBoolean("BOOLEANS.COOLDOWN-CHAT")) {
            if (timer.isActive()) {
                if (player.hasPermission("friday.chat.bypass")) {
                    return;
                }

                event.setCancelled(true);

                plugin.getChatFile().getStringList("MESSAGES.DELAY-MESSAGE").forEach(line -> {
                    line = line.replaceAll("<time>", String.valueOf(getChatCooldownTimer()));

                    player.sendMessage(translate(line));
                });
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChatMonitor(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        List<String> badWords = plugin.getChatFile().getStringList("BAD-WORDS");

        for (String message : badWords) {
            if (event.getMessage().toLowerCase().equalsIgnoreCase(message)) {
                // return the event (the player can't say the bad word)
                event.setCancelled(true);

                // send a message to alert the player, but if the player is in
                // chat cooldown send the cooldown message and not the alert message
                if (plugin.getChatFile().getBoolean("BOOLEANS.WARNING-MESSAGE")) {
                    plugin.getChatFile().getStringList("MESSAGES.WARNING-MESSAGE").forEach(line -> {
                        line = line.replaceAll("<player>", player.getName());

                        player.sendMessage(translate(line));
                    });

                    if (plugin.getChatFile().getBoolean("BOOLEANS.WARNING-STAFF-MESSAGE")) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("friday.access.chat.badwords")) {
                                plugin.getChatFile().getStringList("MESSAGES.WARNING-STAFF-MESSAGE").forEach(line -> {
                                    line = line.replaceAll("<player>", player.getName());
                                    line = line.replaceAll("<bad-word>", event.getMessage());

                                    players.sendMessage(translate(line));
                                });
                            }
                        }
                    }

                    // send a log to the console with a warning message
                    Logger.sendConsoleMessage(player.getName() + " &chas been said a bad word! &7('" + event.getMessage() + "')");
                }
            }
        }
    }

    public Timer getChatCooldownTimer() {
        if (chatCooldownTimer == null) {
            chatCooldownTimer = new DoubleTimer(3);
        }

        return chatCooldownTimer;
    }
}