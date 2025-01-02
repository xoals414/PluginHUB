package org.gumang.sImple_JoinQuit.event;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.gumang.sImple_JoinQuit.SImple_JoinQuit;

public class JoinQuitEvent implements Listener {

    FileConfiguration config = SImple_JoinQuit.getPlugin(SImple_JoinQuit.class).getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        String playerName = p.getName();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',config.getString("message.join-message.message")
                .replace("{player}",playerName)));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        String playerName = p.getName();
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',config.getString("message.quit-message.message")
                .replace("{player}",playerName)));

    }
}
