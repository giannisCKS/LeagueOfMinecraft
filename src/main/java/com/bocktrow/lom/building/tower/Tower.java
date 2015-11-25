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
        block.getWorld().getLivingEntities().stream().filter(entity -> location.distance(entity.getLocation()) <= 8 && entity instanceof Player).forEach(entity -> {
            Vector vector = location.toVector().add(entity.getEyeLocation().getDirection());

            Vector cut = vector.normalize().multiply(0.1);
            Vector work = cut.clone();

            while (vector.length() > work.length()) {
                ParticleEffect.FLAME.display(.1F, .1F, .1F, 0, 10, location.clone().add(work), 24);
                work.add(cut);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
