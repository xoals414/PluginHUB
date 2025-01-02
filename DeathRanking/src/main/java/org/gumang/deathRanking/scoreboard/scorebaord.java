package org.gumang.deathRanking.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.gumang.deathRanking.DeathRanking;

public class scorebaord implements Listener {
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();
                Objective objective = board.registerNewObjective("DRscore", "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName("마인그라운드");

                Score score8 = objective.getScore("§6닉네임 : " + p.getName());
                score8.setScore(8);
                Score score7 = objective.getScore("§f---------------");
                score7.setScore(7);
                Score score6 = objective.getScore("§e생존자 수 : " + Bukkit.getOnlinePlayers().size());
                score6.setScore(6);
                p.setScoreboard(board);
            }
        }.runTaskTimer(DeathRanking.getPlugin(DeathRanking.class), 0L, 20L); // 20 ticks = 1 second
    }
}
