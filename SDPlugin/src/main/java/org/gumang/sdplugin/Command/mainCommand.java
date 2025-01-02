package org.gumang.sdplugin.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class mainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (p.isOp()) {
            p.sendMessage(ChatColor.GOLD + "-------------------------");
            p.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.DARK_GREEN + "Server Info" + ChatColor.YELLOW + "-------");
            p.sendMessage(ChatColor.RED + "ServerOlinePlayer : " + Bukkit.getServer().getOnlinePlayers());
            p.sendMessage(ChatColor.GOLD + "ServerTPS : " + Bukkit.getServer().getTPS());
            p.sendMessage(ChatColor.YELLOW + "ServerIp : " + Bukkit.getServer().getIp());
            p.sendMessage(ChatColor.GREEN + "ServerPort : " + Bukkit.getServer().getPort());
            p.sendMessage(ChatColor.BLUE + "ServerVersion : " + Bukkit.getServer().getVersion());
            p.sendMessage(ChatColor.DARK_PURPLE + "ServerDefaultGameMode : " + Bukkit.getServer().getDefaultGameMode());
            p.sendMessage(ChatColor.GOLD + "-------------------------");
        } else {
            p.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
        }

        return false;
    }
}
