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
                if (entity.getLocation().distance(location) <= 7) {

                    Vector vector = new Vector(location.getX() - entity.getEyeLocation().getX(),
                            location.getY() - entity.getEyeLocation().getY() + 1,
                            location.getZ() - entity.getEyeLocation().getZ());

                    vector = vector.normalize().multiply(0.05);

                    Vector vectorInit = vector.clone();

                    for (int i = 0; i <= 30; i++) {
                        ParticleEffect.FLAME.display(0F, 0F, 0F, 0, 3, location.subtract(vector), 20);
                        vector.add(vectorInit);

                        if (vector.length() >= entity.getLocation().distance(location)) continue;
                    }
                }
            }
        }
    }


    @Override
    public void onDestroy() {

    }
}
