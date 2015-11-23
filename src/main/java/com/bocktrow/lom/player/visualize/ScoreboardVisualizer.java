package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.LeagueOfMinecraft;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardVisualizer {

    public static void init(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public static void visualize(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();

        Bukkit.getScheduler().runTaskAsynchronously(LeagueOfMinecraft.INSTANCE, () -> {
            if (scoreboard.getObjective(DisplaySlot.SIDEBAR) != null) {
                scoreboard.getObjective(DisplaySlot.SIDEBAR).unregister();
            }

            Objective objective = scoreboard.registerNewObjective("gameSidebar", "dummy");
            objective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + gamePlayer.getChampion().getName());

            objective.getScore(ChatColor.YELLOW + "").setScore(10);

            if (player.getInventory().getHeldItemSlot() == 7) {

                objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Statistics:").setScore(9);
                objective.getScore(ChatColor.YELLOW + "Attack damage: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.ATTACK_DAMAGE)).setScore(8);
                objective.getScore(ChatColor.YELLOW + "Ability power: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.ABILITY_POWER)).setScore(7);
                objective.getScore(ChatColor.YELLOW + "Cr. strike ch.: " + ChatColor.AQUA + (gamePlayer.getStatistic(Statistic.CRITICAL_DAMAGE) * 100) + "%").setScore(6);
                objective.getScore(ChatColor.YELLOW + "Life steal: " + ChatColor.AQUA + (gamePlayer.getStatistic(Statistic.LIFE_STEAL)* 100) + "%").setScore(5);
                objective.getScore(ChatColor.YELLOW + "Max. health: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.HEALTH)).setScore(4);
                objective.getScore(ChatColor.YELLOW + "Armor: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.ARMOR)).setScore(3);
                objective.getScore(ChatColor.YELLOW + "Magic Resist.: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.MAGIC_RESISTANCE)).setScore(2);
                objective.getScore(ChatColor.YELLOW + "Movement speed: " + ChatColor.AQUA + gamePlayer.getStatistic(Statistic.MOVEMENT_SPEED)).setScore(1);
            } else {
                objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Details:").setScore(9);
            }

            objective.getScore(ChatColor.RED + "").setScore(0);

            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        });
    }

}
