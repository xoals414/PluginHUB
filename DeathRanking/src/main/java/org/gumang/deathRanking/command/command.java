package org.gumang.deathRanking.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.gumang.deathRanking.DeathRanking.deathMsg;

public class command implements CommandExecutor {

    public static int allplayerNum = 0;
    public static String deathType = "msg";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage("");
            p.sendMessage(ChatColor.WHITE + "/dera on/off/check  msg/kick/ban");
            p.sendMessage("");
        } else if (args.length == 1) {
            if(args[0].contentEquals("on")) {
                if (deathMsg == true) {
                    p.sendMessage(ChatColor.RED + "이미 활성화 되어있습니다.");
                } else if (deathMsg == false) {
                    p.sendMessage(ChatColor.YELLOW + "활성화 되었습니다!");
                    deathMsg = true;
                }
            } else if(args[0].contentEquals("off")) {
                if (deathMsg == false) {
                    p.sendMessage(ChatColor.RED + "이미 비활성화 되어있습니다.");
                } else if (deathMsg == true) {
                    p.sendMessage(ChatColor.YELLOW + "비활성화 되었습니다!");
                    deathMsg = false;
                }
            } else if(args[0].contentEquals("check")) {
                p.sendMessage(ChatColor.YELLOW + "플레이어 확인중..");
                allplayerNum = Bukkit.getOnlinePlayers().size();
                p.sendMessage(ChatColor.YELLOW + "플레이어 확인 완료! (" + allplayerNum + ")");

            //타입:
            } else if(args[0].contentEquals("msg")) {
                if (deathType == "msg") {
                    p.sendMessage(ChatColor.RED + "이미 Message 입니다.");
                } else if (deathType == "kick" | deathType == "ban") {
                    p.sendMessage(ChatColor.YELLOW + "Message로 변경 되었습니다!");
                    deathType = "msg";
                }
            } else if(args[0].contentEquals("kick")) {
                if (deathType == "kick") {
                    p.sendMessage(ChatColor.RED + "이미 Kick 입니다.");
                } else if (deathType == "msg" | deathType == "ban") {
                    p.sendMessage(ChatColor.YELLOW + "Kick으로 변경 되었습니다!");
                    deathType = "kick";
                }
            } else if(args[0].contentEquals("ban")) {
                if (deathType == "ban") {
                    p.sendMessage(ChatColor.RED + "이미 ban 입니다.");
                } else if (deathType == "msg" | deathType == "kick") {
                    p.sendMessage(ChatColor.YELLOW + "ban으로 변경 되었습니다!");
                    deathType = "ban";
                }
            }
        }
        return false;
    }
}
