package com.bocktrow.lom.player;

import com.bocktrow.lom.utils.ActionBarUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
            event.getDrops().clear();
            event.setDroppedExp(0);
            event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, (int) 1E10, 1));
            event.getEntity().getInventory().setHeldItemSlot(7);
            ActionBarUtil.sendToPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You have died!", event.getEntity());
            GamePlayer.getGamePlayer(event.getEntity()).die();
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (GamePlayer.getGamePlayer(event.getPlayer()) != null && GamePlayer.getGamePlayer(event.getPlayer()).isDead() && event.getTo().getY() <= 10) {
            Location location = event.getPlayer().getLocation();
            location.setY(12);
            event.getPlayer().teleport(location);
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }

    @EventHandler
    public void slot(PlayerItemHeldEvent event) {
        if (GamePlayer.getGamePlayer(event.getPlayer()) != null && GamePlayer.getGamePlayer(event.getPlayer()).isDead() && event.getNewSlot() != 7) {
            if (event.getPreviousSlot() == 7) {
                event.setCancelled(true);
            } else {
                event.getPlayer().getInventory().setHeldItemSlot(7);
            }
        }
    }

}
