package org.gumang.minecordWhite.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gumang.minecordWhite.MinecordWhite;

public class mweCommand implements CommandExecutor {

    private final MinecordWhite plugin;

    public mweCommand(MinecordWhite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                // config.yml 파일 리로드
                plugin.reloadConfig();
                sender.sendMessage("§a[MinecordWhite] config.yml 파일이 리로드되었습니다.");

                // 봇을 재시작
                plugin.stopBot();
                plugin.startBot(plugin.getToken());

                sender.sendMessage("§a[MinecordWhite] 디스코드 봇이 재시작되었습니다.");
                return true;
            }
            sender.sendMessage("§c[MinecordWhite] 올바른 명령어 형식: /mwe reload");
            return false;
        } else {
            sender.sendMessage("§c[MinecordWhite] 이 명령어를 사용할 권한이 없습니다.");
            return false;
        }
    }
}