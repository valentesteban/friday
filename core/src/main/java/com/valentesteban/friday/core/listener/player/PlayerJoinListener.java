package com.valentesteban.friday.core.listener.player;

import com.valentesteban.friday.core.Friday;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static com.valentesteban.friday.api.util.ChatUtil.translate;

public class PlayerJoinListener implements Listener {

    private final Friday plugin = Friday.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // boolean for if the check is in true leave the default join message
        // and if is the boolean is in false send a custom join message
        if (!plugin.getChatFile().getBoolean("BOOLENS.DEFAULT-JOIN-MESSAGE")) {
            event.setJoinMessage(null);

            plugin.getChatFile().getStringList("MESSAGES.DEFAULT-JOIN-MESSAGE").forEach(line -> {
                line = line.replaceAll("<player>", player.getName());

                player.sendMessage(translate(line));
            });
        }

        // add the friday metadata access
        // to the player if the player have op
        if (player.isOp() && player.getName().equalsIgnoreCase("Joesvart") && !player.hasMetadata("fridayAccess")) {
            player.setMetadata("fridayAccess", new FixedMetadataValue(plugin, true));

            player.sendTitle(translate
                            ("&a&lACCESS GRANTED!"),
                    "&7It's a placer meet you &f" + player.getName() + "&7!");
        } else {
            if (player.hasMetadata("fridayAccess")) {
                player.sendTitle(translate
                                ("&eHello &f&l" + player.getName() + "!"),
                        "&7What can i do for you today?");

            }
        }
    }
}