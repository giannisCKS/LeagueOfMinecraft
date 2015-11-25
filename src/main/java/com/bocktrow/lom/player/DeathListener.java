package com.bocktrow.lom.player;

import com.bocktrow.lom.utils.ActionBarUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (GamePlayer.getGamePlayer(event.getEntity()) != null) {
            event.setDeathMessage("");
            event.getEntity().setHealth(event.getEntity().getMaxHealth());
            event.getEntity().setGameMode(GameMode.ADVENTURE);
            Location location = event.getEntity().getLocation();
            location.setY(12);
            event.getEntity().teleport(location);
            event.getEntity().setAllowFlight(true);
            event.getEntity().setFlying(true);
            ActionBarUtil.sendToPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You have died!", event.getEntity());
            GamePlayer.getGamePlayer(event.getEntity()).die();
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (GamePlayer.getGamePlayer(event.getPlayer()) != null && GamePlayer.getGamePlayer(event.getPlayer()).isDead() && event.getTo().getX() <= 10) {
            Location location = event.getPlayer().getLocation();
            location.setY(12);
            event.getPlayer().teleport(location);
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }

}
