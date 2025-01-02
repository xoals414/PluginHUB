package org.gumang.sImple_JoinQuit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.gumang.sImple_JoinQuit.event.JoinQuitEvent;

public final class SImple_JoinQuit extends JavaPlugin {

    FileConfiguration config = this.getConfig();

    private void initConfig(){
        config.addDefault("message.join-message.message", "&l[ &a+ &f] &e{player}");
        config.addDefault("message.quit-message.message", "&l[ &c- &f] &e{player}");
        config.options().copyDefaults(true);
        saveConfig();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
    }

    private void registerCommands(){

    }

    @Override
    public void onEnable() {
        getLogger().info("=Start Plugin!=");
        registerCommands();
        registerEvents();
        initConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("=Stop Plugin!=");
    }

    public static SImple_JoinQuit getPlugin() {
        return JavaPlugin.getPlugin(SImple_JoinQuit.class);
    }
}
