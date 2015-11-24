package com.bocktrow.lom.ability.global;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.event.player.PlayerInteractEvent;

public class Recall extends Ability {

    @Override
    public String getName() {
        return "Recall";
    }

    @Override
    public String[] getDescription(GamePlayer player) {
        return new String[0];
    }

    @Override
    public boolean isCastable() {
        return true;
    }

    @Override
    public boolean isChanneled() {
        return true;
    }

    @Override
    public void visualize(GamePlayer player) {

    }

    @Override
    public void cast(GamePlayer player, PlayerInteractEvent event) {

    }

    @Override
    public void tick(GamePlayer player) {

    }

    @Override
    public int cooldown() {
        return 0;
    }

    @Override
    public int manaCost() {
        return 0;
    }
}
