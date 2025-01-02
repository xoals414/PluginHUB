package org.gumang.playerjoincheak;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerJoinCheak extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("--------------------" + "\n [PlayerCheaker] Plugin is start! (Ver : 1.0) " + "\n--------------------");
        getCommand("checker").setExecutor(new CheakerCommand());
    }

    @Override
    public void onDisable() {

    }
}
