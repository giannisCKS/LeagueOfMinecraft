package com.bocktrow.lom.ability.global;

import com.avaje.ebean.ValidationException;
import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Flash extends Ability {
    @Override
    public String getName() {
        return "Flash";
    }

    @Override
    public String[] getDescription(GamePlayer player) {

        String cooldown = player.getStatistic(Statistic.COOLDOWN_REDUCTION) == 0 ? cooldown() + "s" :
                ((int) (cooldown() - (player.getStatistic(Statistic.COOLDOWN_REDUCTION) * cooldown()))) + "s" + ChatColor.GRAY +  " (Base: " + cooldown() + "s)";

        return new String[]{"Teleports you instantly 5 meters away.", "", ChatColor.GRAY + "Cooldown: " + ChatColor.AQUA + cooldown};
    }

    @Override
    public boolean isCastable() {
        return true;
    }

    @Override
    public boolean isChanneled() {
        return false;
    }

    @Override
    public void visualize(GamePlayer player) {
        Player player1 = player.getPlayer();
        Location location = player1.getLocation();

        for (double i = 0; i<=2 * Math.PI; i += Math.PI / 32) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, location.clone().add(5 * Math.sin(i), 0, 5 * Math.cos(i)), player1);
        }

        location = player.getPlayer().getEyeLocation();
        Vector vector = player.getPlayer().getEyeLocation().getDirection();
        vector.setY(0);
        vector.normalize();
        Vector toAdd = vector.clone().multiply(5);
        vector.add(toAdd);

        for (double i = 0; i <= 2; i+= 0.2) {
            ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, player1.getLocation().clone().add(vector).add(0, i, 0), player1);
        }
    }

    @Override
    public void cast(GamePlayer player, PlayerInteractEvent event) {
        if (getCurrentCooldown() != 0) return;

        Location location = player.getPlayer().getEyeLocation();
        Vector vector = player.getPlayer().getEyeLocation().getDirection();
        vector.setY(0);
        vector.normalize();
        Vector toAdd = vector.clone().multiply(5);
        vector.add(toAdd);

        player.getPlayer().teleport(location.clone().add(vector));

        setCooldown(cooldown(), player.getStatistic(Statistic.COOLDOWN_REDUCTION));
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
