package com.valentesteban.friday.core;

import lombok.Getter;
import com.valentesteban.friday.core.service.FridayService;
import org.bukkit.plugin.java.JavaPlugin;
import com.valentesteban.friday.api.configuration.Configuration;
import com.valentesteban.friday.api.logger.Logger;

@Getter
public final class Friday extends JavaPlugin {

    @Getter
    private static Friday instance;

    private Configuration
        configFile, verificationFile, chatFile;

    @Override
    public void onEnable() {
        instance = this;

        Logger.sendConsoleMessage("Initializing and reading all the files and services of Friday..");

        loadConfig();

        FridayService fridayService = new FridayService();
        fridayService.start();
    }

    @Override
    public void onDisable() {
        Logger.sendConsoleMessage("Disabling all the services of Friday..");

        instance = null;
    }

    void loadConfig() {
        configFile = new Configuration(this, "config.yml");
        verificationFile = new Configuration(this, "verification.yml");
        chatFile = new Configuration(this, "chat.yml");
    }
}