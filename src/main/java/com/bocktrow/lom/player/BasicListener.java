package com.bocktrow.lom.player;

import com.bocktrow.lom.champion.mrepic.MrEpic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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

}
