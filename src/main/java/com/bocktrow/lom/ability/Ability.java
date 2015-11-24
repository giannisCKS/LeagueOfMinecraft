package com.bocktrow.lom.ability;

import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class Ability {

    private int currentCooldown = 0;

    public abstract String getName();
    public abstract String[] getDescription(GamePlayer player);

    public abstract boolean isCastable();
    public abstract void visualize(GamePlayer player);
    public abstract void cast(GamePlayer player, PlayerInteractEvent event);

    public void decreaseCooldown() {
        if (currentCooldown != 0) currentCooldown--;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCooldown(int cooldown) {
        currentCooldown = cooldown;
    }

    public abstract void tick(GamePlayer player);

    public abstract int cooldown();
    public abstract int manaCost();
}
