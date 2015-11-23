package com.bocktrow.lom.champion.mrepic.ability;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.*;
import org.bukkit.util.Vector;

import java.util.*;

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

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 32) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, location.clone().add(10 * Math.sin(i), 0, 10 * Math.cos(i)), player1);
        }

        location = player.getPlayer().getEyeLocation();
        Vector vector = player.getPlayer().getEyeLocation().getDirection();
        vector.normalize();
        Vector toAdd = vector.clone().multiply(0.1);

        while (location.clone().add(vector).getBlock() == null || location.clone().add(vector).getBlock().getType() == Material.AIR) {
            vector.add(toAdd);
            if (vector.length() >= 10) return;
        }

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 32) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, location.clone().add(vector).add(1.2 * Math.sin(i), 0, 1.2 * Math.cos(i)), player1);
        }
    }

    @Override
    public void cast(GamePlayer player, PlayerInteractEvent event) {

        if (getCurrentCooldown() != 0) return;

        Location location = player.getPlayer().getEyeLocation();
        Vector vector = player.getPlayer().getEyeLocation().getDirection();
        vector.normalize();
        Vector toAdd = vector.clone().multiply(0.1);

        while (location.clone().add(vector).getBlock() == null || location.clone().add(vector).getBlock().getType() == Material.AIR) {
            vector.add(toAdd);
            if (vector.length() >= 10) return;
        }

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 8) {
            for (double j = 0; j<= Math.PI; j += Math.PI / 8) {
                double x = 1.2 * Math.cos(i) * Math.sin(j);
                double y = 1.2 * Math.sin(i) * Math.sin(j);
                double z = 1.2 * Math.cos(j);
                ParticleEffect.FLAME.display(0F, 0F, 0F, 0, 1, location.clone().add(vector).add(x, y, z), 24);
            }
        }

        setCooldown(cooldown());
    }

    @Override
    public void tick(GamePlayer player) {

    }

    @Override
    public int cooldown() {
        return 10;
    }
}
