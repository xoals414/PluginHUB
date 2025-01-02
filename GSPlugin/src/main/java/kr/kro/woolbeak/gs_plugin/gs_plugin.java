package kr.kro.woolbeak.gs_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class gs_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("GS_Plugin - Start!!");
        getCommand("test").setExecutor(new command());
        getCommand("TapListName").setExecutor(new command2());
        getCommand("gm").setExecutor(new gm1());
        getCommand("sethome").setExecutor(new gm1());
        getCommand("home").setExecutor(new gm1());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
