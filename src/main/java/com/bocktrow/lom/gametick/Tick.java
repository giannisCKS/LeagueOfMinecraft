package com.bocktrow.lom.gametick;

import com.bocktrow.lom.LeagueOfMinecraft;
import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.player.visualize.AbilityVisualizer;
import com.bocktrow.lom.player.visualize.ScoreboardVisualizer;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

public class Tick {

    public static int currentTick = 0;

    public static void init() {
        Bukkit.getScheduler().runTaskTimer(LeagueOfMinecraft.INSTANCE, Tick::tick, 2L, 2L);
    }

    public static void tick() {
        GamePlayer.getGamePlayers().values().forEach(gamePlayer -> {
            AbilityVisualizer.visualize(gamePlayer);
            if (currentTick % 4 == 0) ScoreboardVisualizer.visualize(gamePlayer);
            if (currentTick % 10 == 0) {
                for (Ability ability : gamePlayer.getAbilities()) {
                    ability.decreaseCooldown();
                }
            }
        });

        currentTick++;
    }

}
