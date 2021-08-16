package net.gromer.friday.core.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import net.gromer.friday.core.Friday;

import static net.gromer.friday.api.util.ChatUtil.translate;

public class PlayerQuitListener implements Listener {

    private final Friday plugin = Friday.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // boolean for if the check is in true leave the default quit message
        // and if is the boolean is in false send a custom quit message
        if (!plugin.getChatFile().getBoolean("BOOLENS.DEFAULT-QUIT-MESSAGE")) {
            event.setQuitMessage(null);

            plugin.getChatFile().getStringList("MESSAGES.DEFAULT-QUIT-MESSAGE").forEach(line -> {
                line = line.replaceAll("<player>", player.getName());

                player.sendMessage(translate(line));
            });
        }

        // remove the friday metadata access
        // to the player if the player have op
        if (player.isOp()) {
            player.removeMetadata("fridayAccess", plugin);
        }
    }
}