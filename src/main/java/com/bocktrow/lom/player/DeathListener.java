package com.bocktrow.lom.player;

import com.bocktrow.lom.utils.ActionBarUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void death(PlayerDeathEvent event) {
        event.setDeathMessage("");
        event.getEntity().setHealth(event.getEntity().getMaxHealth());
        event.getEntity().setGameMode(GameMode.SPECTATOR);
        ActionBarUtil.sendToPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You have died!", event.getEntity());
    }

}
