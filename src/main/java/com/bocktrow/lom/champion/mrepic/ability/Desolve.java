package com.bocktrow.lom.champion.mrepic.ability;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;

public class Desolve extends Ability {

    @Override
    public String getName() {
        return "Desolve";
    }

    @Override
    public String[] getDescription(GamePlayer player) {
        return new String[]{"Causes an explosion that deals [Y]" + player.getStatistic(Statistic.ABILITY_POWER) + " magic damage [G]to enemy units!"};
    }

    @Override
    public boolean isCastable() {
        return false;
    }

    @Override
    public void visualize(GamePlayer player) {
        Player player1 = player.getPlayer();
        Location location = player1.getLocation();

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 8) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, location.clone().add(5 * Math.sin(i), 0, 5 * Math.cos(i)), player1);
        }
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
