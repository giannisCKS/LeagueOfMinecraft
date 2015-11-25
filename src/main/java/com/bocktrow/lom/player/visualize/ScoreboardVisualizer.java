package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.LeagueOfMinecraft;
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

        Bukkit.getScheduler().runTaskAsynchronously(LeagueOfMinecraft.INSTANCE, () -> {
            if (scoreboard.getObjective(DisplaySlot.SIDEBAR) != null) {
                scoreboard.getObjective(DisplaySlot.SIDEBAR).unregister();
            }

            Objective objective = scoreboard.registerNewObjective("gameSidebar", "dummy");
            objective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + gamePlayer.getChampion().getName());

            objective.getScore(ChatColor.YELLOW + "").setScore(10);

                objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Active effects:").setScore(9);
                Iterator<StatusEffect> statusEffectIterator = gamePlayer.getStatusEffects().iterator();
                int score = 8;
                while (statusEffectIterator.hasNext() && score > 0) {
                    StatusEffect statusEffect = statusEffectIterator.next();
                    objective.getScore((statusEffect.isPositive() ? ChatColor.GREEN : ChatColor.RED) +
                            statusEffect.getName() + ChatColor.GRAY + " (" + (statusEffect.getDuration() / 20) + "s)").setScore(score--);
                }

            objective.getScore(ChatColor.RED + "").setScore(0);

            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        });
    }

}
