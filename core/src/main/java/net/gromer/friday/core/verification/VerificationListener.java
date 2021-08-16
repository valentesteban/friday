package net.gromer.friday.core.verification;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.gromer.friday.api.logger.Logger;
import net.gromer.friday.core.Friday;

import static net.gromer.friday.api.util.ChatUtil.translate;

public class VerificationListener implements Listener {

    private final Friday plugin = Friday.getInstance();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (event.getMessage().contains(plugin.getVerificationFile().getString("VERIFICATION.PASSWORD")) && player.hasMetadata("fridayAccess")) {
            // hide the message
            event.setCancelled(true);

            player.sendTitle
                    (translate("&a&lACCESS GRANTED"),
                    translate("&7It's good to have you back, &f" + player.getName() + "&7!"));

            /*if (player.hasMetadata("fridayAcess")) {
                // put the code
                // of metadata here
            }*/
        } else {
            // hide the message
            event.setCancelled(true);

            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasMetadata("fridayAccess")) {
                    players.sendMessage(translate(Logger.prefix + player.getName() + "  &chas tried to access to Friday interface!"));
                    players.sendMessage(translate("defensive measures have been taken."));
                }
            }

            player.sendTitle
                    (translate("&c&lACCESS DENIED"),
                    translate("&7What are you trying to do?"));

            Logger.sendConsoleMessage(player.getName() + " &chas tried to access to Friday interface!");
        }
    }
}