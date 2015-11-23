package com.bocktrow.lom.player;

import com.bocktrow.lom.champion.mrepic.MrEpic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicListener implements Listener {

    public void join(PlayerJoinEvent event) {
        GamePlayer.createGamePlayer(event.getPlayer(), new MrEpic());
    }

}
