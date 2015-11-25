package com.bocktrow.lom.building.tower;

import com.bocktrow.lom.building.Building;
import com.bocktrow.lom.team.Team;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Tower extends Building {

    private boolean laserBeam;

    public Tower(Team team, Block center, ArrayList<Block> blocks, ArrayList<Block> functionalBlcoks, boolean laser) {
        super(team, center, center.getRelative(0, 5, 0), blocks, functionalBlcoks, 200);
    }


    @Override
    public void tick() {

    }

    @Override
    public void onDestroy() {

    }
}
