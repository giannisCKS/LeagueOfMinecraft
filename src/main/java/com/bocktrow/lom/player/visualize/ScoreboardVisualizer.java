package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.LeagueOfMinecraft;
import com.bocktrow.lom.gametick.Tick;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.StatusEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Iterator;

public class ScoreboardVisualizer {

    public static void init(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public static void visualize(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();

        int seconds = Tick.getTick() / 20;
        String mins = seconds / 60 + ""; if (mins.length() == 1) mins = "0" + mins;
        String secs = seconds % 60 + ""; if (secs.length() == 1) secs = "0" + secs;

        final String finalMins = mins;
        final String finalSecs = secs;
        Bukkit.getScheduler().runTaskAsynchronously(LeagueOfMinecraft.INSTANCE, () -> {
            if (scoreboard.getObjective(DisplaySlot.SIDEBAR) != null) {
                scoreboard.getObjective(DisplaySlot.SIDEBAR).unregister();
            }

            Objective objective = scoreboard.registerNewObjective("gameSidebar", "dummy");
            int slot = 20;

            objective.setDisplayName(gamePlayer.getTeam().getChatColor() + "" + ChatColor.BOLD + gamePlayer.getChampion().getName());

            objective.getScore(ChatColor.BLACK + "").setScore(--slot);
            objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Time elapsed:").setScore(--slot);
            objective.getScore(ChatColor.YELLOW + "" + finalMins + ":" + finalSecs).setScore(--slot);
            objective.getScore(ChatColor.RED + "").setScore(--slot);
            objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Score:").setScore(--slot);
            objective.getScore(ChatColor.YELLOW + "" + gamePlayer.getKills() + "/" + gamePlayer.getDeaths() + "/" + gamePlayer.getAssists()).setScore(--slot);
            objective.getScore(ChatColor.GREEN + "").setScore(--slot);
            objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Gold earned:").setScore(--slot);
            objective.getScore(ChatColor.YELLOW + "⛃ " + gamePlayer.getGold()).setScore(--slot);

            if (gamePlayer.getStatusEffects().size() != 0) {
                objective.getScore(ChatColor.YELLOW + "").setScore(--slot);
                objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Active effects:").setScore(--slot);
            }
            for (StatusEffect statusEffect : gamePlayer.getStatusEffects()) {
                objective.getScore((statusEffect.isPositive() ? ChatColor.GREEN : ChatColor.RED) +
                        statusEffect.getName() + ChatColor.GRAY + " (" + (statusEffect.getDuration() / 20) + "s)").setScore(--slot);
            }
            objective.getScore(ChatColor.AQUA + "").setScore(--slot);

            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        });
    }

}
