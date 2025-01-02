package org.gumang.deathRanking.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static org.gumang.deathRanking.DeathRanking.deathMsg;
import static org.gumang.deathRanking.command.command.allplayerNum;
import static org.gumang.deathRanking.command.command.deathType;

public class event implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        if (deathMsg == true) {
            e.setDeathMessage(ChatColor.WHITE + "#" + allplayerNum + " " + e.getEntity().getName());

            //메시지
            if (deathType == "msg") {
                p.sendMessage("사망하였습니다!");
            } else if (deathType == "kick") {
                p.kickPlayer("죽었습니다! 순위 : " + allplayerNum);
            } else if (deathType == "ban") {
                p.banPlayer("죽었습니다! 순위 : " + allplayerNum);
            }

            allplayerNum = allplayerNum - 1;
        } else if (deathMsg == false) {

        }
    }
}
