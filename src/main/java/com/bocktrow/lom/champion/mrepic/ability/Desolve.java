package com.bocktrow.lom.champion.mrepic.ability;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.ChatColor;
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

        double damage = 25 + player.getStatistic(Statistic.ABILITY_POWER);
        String cooldown = player.getStatistic(Statistic.COOLDOWN_REDUCTION) == 0 ? cooldown() + "s" :
                ((int) (cooldown() - (player.getStatistic(Statistic.COOLDOWN_REDUCTION) * cooldown()))) + "s" + ChatColor.GRAY +  " (Base: " + cooldown() + "s)";

        return new String[]{"Causes an explosion that deals [Y]" + damage + " magic damage [G]to enemy units!", "", ChatColor.GRAY + "Cooldown: " + ChatColor.AQUA + cooldown};
    }

    @Override
    public boolean isCastable() {
        return true;
    }

    @Override
    public boolean isChanneled() {
        return false;
    }

    @SuppressWarnings("Duplicates")
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

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 16) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, location.clone().add(vector).add(1.2 * Math.sin(i), 0, 1.2 * Math.cos(i)), player1);
        }
    }

    @Override
    public void cast(GamePlayer player, PlayerInteractEvent event) {
        if (getCurrentCooldown() != 0) return;
        if (player.getMana() < manaCost()) return;

        Location location = player.getPlayer().getEyeLocation();
        Vector vector = player.getPlayer().getEyeLocation().getDirection();
        vector.normalize();
        Vector toAdd = vector.clone().multiply(0.1);

        while (location.clone().add(vector).getBlock() == null || location.clone().add(vector).getBlock().getType() == Material.AIR) {
            vector.add(toAdd);
            if (vector.length() >= 10) return;
        }

        double damage = 25 + player.getStatistic(Statistic.ABILITY_POWER);
        location.getWorld().getLivingEntities().stream().filter(entity -> entity.getLocation().distance(location.clone().add(vector)) <= 1.2 && entity != player.getPlayer()).forEach(entity1 -> entity1.damage(damage));

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 8) {
            for (double j = 0; j<= Math.PI; j += Math.PI / 8) {
                double x = 1.2 * Math.cos(i) * Math.sin(j);
                double y = 1.2 * Math.sin(i) * Math.sin(j);
                double z = 1.2 * Math.cos(j);
                ParticleEffect.FLAME.display(0F, 0F, 0F, 0, 1, location.clone().add(vector).add(x, y, z), 24);
            }
        }

        setCooldown(cooldown(), player.getStatistic(Statistic.COOLDOWN_REDUCTION));
        player.setMana(player.getMana() - manaCost());
    }

    @Override
    public void tick(GamePlayer player) {

    }

    @Override
    public int cooldown() {
        return 10;
    }

    public int manaCost() { return 100; }


}
