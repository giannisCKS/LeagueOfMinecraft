package com.bocktrow.lom.gametick;

import com.bocktrow.lom.LeagueOfMinecraft;
import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.PlayerEntity;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.player.visualize.AbilityVisualizer;
import com.bocktrow.lom.player.visualize.ScoreboardVisualizer;
import com.bocktrow.lom.player.visualize.TextHandler;
import org.bukkit.Bukkit;

public class Tick {

    private static int currentTick = 0;

    public static int getTick() {
        return currentTick;
    }

    public static void init() {
        Bukkit.getScheduler().runTaskTimer(LeagueOfMinecraft.INSTANCE, Tick::tick, 1L, 1L);
    }

    public static void tick() {
        GamePlayer.getGamePlayers().values().forEach(gamePlayer -> {
            if (currentTick % 20 == 0) {
                for (Ability ability : gamePlayer.getAbilities()) {
                    ability.decreaseCooldown();
                }
            }

            if (currentTick % 8 == 0) ScoreboardVisualizer.visualize(gamePlayer);
            AbilityVisualizer.visualize(gamePlayer);

            if (gamePlayer.isDead()) return;

            TextHandler.visualize(gamePlayer);
            PlayerEntity.tickPlayer(gamePlayer);
            PlayerEntity.updatePlayer(gamePlayer);
        });

        currentTick++;
    }

}
