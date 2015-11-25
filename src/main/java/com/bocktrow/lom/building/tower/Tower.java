package com.bocktrow.lom.building.tower;

import com.bocktrow.lom.building.Building;
import com.bocktrow.lom.gametick.Tick;
import com.bocktrow.lom.player.GamePlayer;
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
import org.omg.CORBA.REBIND;

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
            if ((entity instanceof Player) && GamePlayer.getGamePlayer((Player) entity) != null) {



                Player player = (Player) entity;
                Location entLoc = entity.getLocation().add(0, 1, 0);

                if (entity.getLocation().distance(getCenter().getLocation().add(0.5, 1, 0.5)) <= 14) {


                    for (double i = getTeam() == Team.BLUE ? 0 : Math.PI; i<= (getTeam() == Team.BLUE ? 1 : 2) *  Math.PI; i += Math.PI / 32) {
                        ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, getCenter().getLocation().add(0.5, 1, 0.5).clone().add(10 * Math.sin(i), 0, 10 * Math.cos(i)), player);
                    }
                    for (int i = -10; i <= 10; i ++) {
                        ParticleEffect.REDSTONE.display(0F, 0F, 0F, 0, 1, getCenter().getLocation().add(0.5, 1, 0.5 + i).clone(), player);
                    }
                }

                if (GamePlayer.getGamePlayer((Player) entity).isDead()) continue;

                damage: {
                    if (entity.getLocation().distance(getCenter().getLocation().add(0.5, 1, 0.5)) <= 10) {
                        Vector vector = entLoc.toVector().subtract(location.toVector()).multiply(0.05);

                        if ((getTeam() == Team.BLUE & vector.getX() < 0) || (getTeam() == Team.RED & vector.getX() > 0)) break damage;

                        Vector originalVector = vector.clone();

                        for (int i = 0; i <= 20; i++) {
                            ParticleEffect.SPELL_WITCH.display(.1F, .1F, .1F, 0, 1, location.clone().add(vector), 24);
                            vector.add(originalVector);
                        }

                        if (Tick.getTick() % 2 == 0) if (entity.getHealth() > 4D) {
                            entity.damage(4D);
                        } else {
                            entity.damage(5.0);
                        }
                    }
                }
            }
        }
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public boolean isDestroyable() {
        return true;
    }
}
