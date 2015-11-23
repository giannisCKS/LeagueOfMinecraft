package com.bocktrow.lom.champion.mrepic.ability;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import org.bukkit.entity.Player;

public class Desolve extends Ability {

    @Override
    public String getName() {
        return "Desolve";
    }

    @Override
    public String[] getDescription(GamePlayer player) {
        return new String[]{"Causes an explosion that deals [y]" + player.getAbilityPower() + " magic damage [G]to enemy units!"};
    }

    @Override
    public boolean isCastable() {
        return false;
    }

    @Override
    public void cast(GamePlayer player) {

    }

    @Override
    public void tick(GamePlayer player) {

    }

    @Override
    public int cooldown() {
        return 10;
    }

}
