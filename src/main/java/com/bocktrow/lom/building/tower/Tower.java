package com.bocktrow.lom.building.tower;

import com.bocktrow.lom.building.Building;
import com.bocktrow.lom.team.Team;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Tower extends Building {

    private boolean laserBeam;

    public Tower(Team team, Block center, ArrayList<Block> blocks, ArrayList<Block> functionalBlcoks, boolean laser) {
        super(team, center, center.getRelative(0, 5, 0), blocks, functionalBlcoks, 200);
    }


    @Override
    public void tick() {
        Block block = getFunctionalBlcoks().get(0);
        Location location = block.getLocation().add(0.5, 0.5, 0.5);

        for (LivingEntity entity : getCenter().getWorld().getLivingEntities()) {
            if ((entity instanceof Player)) {

                Location entLoc = entity.getLocation().add(0, 1, 0);

                if (entity.getLocation().distance(getCenter().getLocation().add(0.5, 1, 0.5)) <= 10) {
                    Vector vector = entLoc.toVector().subtract(location.toVector());

                    ParticleEffect.CRIT_MAGIC.display(.2F, .2F, .2F, 0, 10, location.clone().add(vector), 24);
                }

                if (entity.getLocation().distance(getCenter().getLocation().add(0.5, 1, 0.5)) <= 14) {
                    Player player = (Player) entity;

                    for (double i = 0; i<=2 * Math.PI; i += Math.PI / 16) {
                        ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, getCenter().getLocation().add(0.5, 1, 0.5).clone().add(10 * Math.sin(i), 0, 10 * Math.cos(i)), player);
                    }
                }
            }
        }
    }


    @Override
    public void onDestroy() {

    }
}
