package kr.kro.woolbeak.gs_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public  class command2 implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            if (strings.length == 0) {
                p.sendMessage("다음과 같이 명령어를 입력해주세요 : /TapListName <NickName>");
            } else if (strings.length == 1) {

                setTapList.setTaplistName(p, strings[0]);
                p.sendMessage("당신의 탭 리스트의 닉네임이 성공적으로 변경되었습니다! " + strings[0]);

            }

        } else {
            commandSender.sendMessage("플레이어만 사용 가능!");

        }
        return false;
    }
}
