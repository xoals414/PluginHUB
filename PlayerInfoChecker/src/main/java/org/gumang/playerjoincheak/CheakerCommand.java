package org.gumang.playerjoincheak;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CheakerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (p.isOp()) {
            if (args.length == 0) {
                p.sendMessage("§cPlease enter a player.");
            } else {
                Player tP = Bukkit.getPlayer(args[0]);
                p.sendMessage("§d" + tP.getName() + " §ePlayer's Address §a: §b" + tP.getAddress());
                p.sendMessage("§d" + tP.getName() + " §ePlayer's Ping §a: §b" + tP.getPing());
                p.sendMessage("§d" + tP.getName() + " §ePlayer's GameMode §a: §b" + tP.getGameMode());
                p.sendMessage("§d" + tP.getName() + " §ePlayer's Exp §a: §b" + tP.getExp());
                p.sendMessage("§d" + tP.getName() + " §ePlayer's Level §a: §b" + tP.getLevel());
                p.sendMessage("§d" + tP.getName() + " §ePlayer's Health §a: §b" + tP.getHealth());
            }
        } else {
            p.sendMessage("§4You do not have permission to use");
        }
        return false;
    }
}
