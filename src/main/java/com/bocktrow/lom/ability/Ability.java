package com.bocktrow.lom.ability;

import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.entity.Player;

public abstract class Ability {

    public abstract String getName();
    public abstract String[] getDescription(GamePlayer player);

    public abstract boolean isCastable();
    public abstract void visualize(GamePlayer player);
    public abstract void cast(GamePlayer player);
    public abstract void tick(GamePlayer player);

    public abstract int cooldown();
}
