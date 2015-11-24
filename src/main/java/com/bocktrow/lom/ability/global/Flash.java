package com.bocktrow.lom.ability.global;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.event.player.PlayerInteractEvent;

public class Flash extends Ability {
    @Override
    public String getName() {
        return "Flash";
    }

    @Override
    public String[] getDescription(GamePlayer player) {
        return new String[]{"Teleports you instantly 5 meters away."};
    }

    @Override
    public boolean isCastable() {
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
        return 300;
    }

    @Override
    public int manaCost() {
        return 0;
    }
}
