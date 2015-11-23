package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.player.GamePlayer;
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

        if (scoreboard.getObjective(DisplaySlot.SIDEBAR) != null) scoreboard.getObjective(DisplaySlot.SIDEBAR).unregister();

        Objective objective = scoreboard.registerNewObjective("gameSidebar", "dummy");
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + gamePlayer.getChampion().getName());

        objective.getScore(ChatColor.YELLOW + "").setScore(10);

        if (player.getInventory().getHeldItemSlot() == 7) {

            objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Statistics:").setScore(9);
            objective.getScore(ChatColor.YELLOW + "Attack damage: " + ChatColor.AQUA + '0').setScore(8);
            objective.getScore(ChatColor.YELLOW + "Ability power: " + ChatColor.AQUA + gamePlayer.getAbilityPower()).setScore(7);
            objective.getScore(ChatColor.YELLOW + "Cr. strike ch.: " + ChatColor.AQUA + (0.3 * 100) + "%").setScore(6);
            objective.getScore(ChatColor.YELLOW + "Life steal:").setScore(5);
            objective.getScore(ChatColor.YELLOW + "Max. health: ").setScore(4);
            objective.getScore(ChatColor.YELLOW + "Armor:").setScore(3);
            objective.getScore(ChatColor.YELLOW + "Magic Resist.:").setScore(2);
            objective.getScore(ChatColor.YELLOW + "Movement speed:").setScore(1);
        } else {
            objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Details:").setScore(9);
        }

        objective.getScore(ChatColor.RED + "").setScore(0);

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);


    }

}
