package org.gumang.deathRanking;

import org.bukkit.plugin.java.JavaPlugin;
import org.gumang.deathRanking.command.command;
import org.gumang.deathRanking.event.event;
import org.gumang.deathRanking.scoreboard.scorebaord;

public final class DeathRanking extends JavaPlugin {

    public static Boolean deathMsg = true;

    @Override
    public void onEnable() {
        getLogger().info("플러그인이 활성화 되었습니다!");
        getServer().getPluginManager().registerEvents(new event(), this);
        //getServer().getPluginManager().registerEvents(new scorebaord(), this);
        getServer().getPluginCommand("dera").setExecutor(new command());
    }

    @Override
    public void onDisable() {

    }
}
