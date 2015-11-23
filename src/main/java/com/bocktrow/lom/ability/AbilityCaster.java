package com.bocktrow.lom.ability;

import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityCaster implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR)
                && (event.getPlayer().getInventory().getHeldItemSlot() >= 1 && event.getPlayer().getInventory().getHeldItemSlot() <= 4)) {
            if (GamePlayer.getGamePlayer(event.getPlayer()) != null) {
                event.setCancelled(true);
                GamePlayer gamePlayer = GamePlayer.getGamePlayer(event.getPlayer());
                gamePlayer.getAbility(event.getPlayer().getInventory().getHeldItemSlot()).cast(gamePlayer, event);
            }
        }

    }

}
