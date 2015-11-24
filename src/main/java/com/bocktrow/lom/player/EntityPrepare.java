package com.bocktrow.lom.player;

import com.bocktrow.lom.statistic.Statistic;
import org.bukkit.entity.Player;

public class EntityPrepare {

    public static void initPlayer(GamePlayer gamePlayer) {
        updatePlayer(gamePlayer);

        Player player = gamePlayer.getPlayer();
        player.setHealth(player.getMaxHealth());
    }

    public static void updatePlayer(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        player.setMaxHealth(gamePlayer.getStatistic(Statistic.HEALTH));
        player.setHealthScale(20.0);
        player.setFoodLevel(25);

        player.setLevel(gamePlayer.getLevel());
        player.setExp((float) gamePlayer.getExperience() / gamePlayer.getExperienceForNext());
    }

}
