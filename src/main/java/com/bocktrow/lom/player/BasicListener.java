package com.bocktrow.lom.player;

import com.bocktrow.lom.champion.mrepic.MrEpic;
import com.bocktrow.lom.statuseffect.global.Homeguard;
import com.bocktrow.lom.statuseffect.global.Ignited;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BasicListener implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent event) {
        GamePlayer.createGamePlayer(event.getPlayer(), new MrEpic());
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        GamePlayer.deleteGamePlayer(event.getPlayer());
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN) != null) {
            if (GamePlayer.getGamePlayer(event.getPlayer()) != null) {
                if (event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.LAPIS_BLOCK ) {
                    GamePlayer.getGamePlayer(event.getPlayer()).addStatusEffect(new Homeguard("Lapis Block"));
                } else if (event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_BLOCK) {
                    GamePlayer.getGamePlayer(event.getPlayer()).addStatusEffect(new Ignited(event.getPlayer().getName()));
                }

            }
        }
    }

}
