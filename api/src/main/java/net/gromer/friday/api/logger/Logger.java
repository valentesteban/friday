package net.gromer.friday.api.logger;

import lombok.experimental.UtilityClass;
import net.gromer.friday.api.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@UtilityClass
public class Logger {

    public static void sendConsoleMessage(String s) {
        Bukkit.getConsoleSender().sendMessage
                (ChatColor.translateAlternateColorCodes('&', "&lFriday &8> &7" + s));
    }

    public static String prefix = ChatUtil.translate("&lFriday &7> ");
}