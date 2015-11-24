package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.utils.ActionBarUtil;
import org.bukkit.ChatColor;

public class TextHandler {

    public static void visualize(GamePlayer gamePlayer) {
        String health = ((int) gamePlayer.getPlayer().getHealth()) + "/" + ((int) gamePlayer.getStatistic(Statistic.HEALTH));
        String mana = ((int) gamePlayer.getMana()) + "/" + ((int) gamePlayer.getStatistic(Statistic.MANA));

        ActionBarUtil.sendToPlayer(ChatColor.GREEN + "" + ChatColor.BOLD + "HP: " + ChatColor.RESET + "" + ChatColor.GREEN + health + "     " +
                ChatColor.AQUA + "" + ChatColor.BOLD + "Mana: " + ChatColor.RESET + "" + ChatColor.AQUA + health, gamePlayer.getPlayer()
        );
    }

}
