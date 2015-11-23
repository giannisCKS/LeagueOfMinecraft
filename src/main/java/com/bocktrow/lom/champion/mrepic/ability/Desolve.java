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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    }

    @Override
    public void cast(GamePlayer player, PlayerInteractEvent event) {
        Set<Material> materials = new HashSet<>();
        materials.addAll(Arrays.asList(Material.AIR, Material.LONG_GRASS));
        Block block = player.getPlayer().getTargetBlock((Set<Material>) materials, 20);

        if (block != null && block.getLocation().distance(player.getPlayer().getLocation()) <= 10 && block.getType() != Material.AIR) {
            Location location = block.getLocation().add(0.5, 1.5, 0.5);
            block.getWorld().getLivingEntities().stream().filter(entity -> entity.getLocation().distance(location) <= 1.2).forEach(entity1 -> entity1.damage(player.getStatistic(Statistic.ABILITY_POWER)));
            ParticleEffect.FLAME.display(1.2F, 1.2F, 1.2F, 0, 100, location, 24);
        }
    }

    @Override
    public void tick(GamePlayer player) {

    }

    @Override
    public int cooldown() {
        return 10;
    }

}
