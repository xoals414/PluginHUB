package kr.kro.woolbeak.gs_plugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class gm1 extends JavaPlugin implements Listener {

    HashMap<UUID, Location> home;

    public gm1() {
        home = new HashMap<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command gm1, @NotNull String label, @NotNull String[] args) {

        if (label.equalsIgnoreCase("gm")) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0")) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage("게임모드가 서바이벌 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("1")) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage("게임모드가 크레이티브 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("2")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage("게임모드가 모험 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("3")) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage("게임모드가 관전자 모드로 변경되었습니다!");
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);

                if (args[0].equalsIgnoreCase("0")) {
                    assert target != null;
                    Objects.requireNonNull(target).setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(args[1] + "게임모드가 서바이벌 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("1")) {
                    assert target != null;
                    target.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(args[1] + "게임모드가 크레이티브 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("2")) {
                    assert target != null;
                    target.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(args[1] + "게임모드가 모험 모드로 변경되었습니다!");
                } else if (args[0].equalsIgnoreCase("3")) {
                    assert target != null;
                    target.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(args[1] + "게임모드가 관전자 모드로 변경되었습니다!");
                }
            } else {
                p.sendMessage("다음과 같이 명령어를 입력해주세요 : /gm <0,1,2,3>");
            }
        } else if (label.equalsIgnoreCase("sethome")) {

            Player p = (Player) sender;
            Location loc = p.getLocation();

            home.put(p.getUniqueId(), loc);
            p.sendMessage("집을 설정하였습니다!");
        } else if (label.equalsIgnoreCase("home")) {
            Player p = (Player) sender;

            if(home.containsKey(p.getUniqueId())) {

                Location loc = home.get(p.getUniqueId());
                p.teleport(loc);
            } else {
                p.sendMessage("집을 먼저 설정해주세요...");
            }

        } else if(label.equalsIgnoreCase("ec")) {

            Player p = (Player) sender;
            p.openInventory(p.getEnderChest());
        }else if(label.equalsIgnoreCase("craft")) {

            Player p = (Player) sender;
            p.openWorkbench(null, true);
        }


        return true;
    }
}