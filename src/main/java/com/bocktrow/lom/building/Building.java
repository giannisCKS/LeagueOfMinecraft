package com.bocktrow.lom.building;

import com.bocktrow.lom.team.Team;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

public abstract class Building {

    private Team team;
    private Block center, top;
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Block> functionalBlcoks = new ArrayList<>();
    private boolean isOperational = true;
    private double health, maxhealth;
    private ArmorStand armorStand;

    public Building(Team team, Block center, Block top, ArrayList<Block> blocks, ArrayList<Block> functionalBlcoks, double health) {
        this.team = team;
        this.center = center;
        this.top = top;
        this.blocks = blocks;
        this.functionalBlcoks = functionalBlcoks;
        this.health = health;

        maxhealth = health;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Block> getFunctionalBlcoks() {
        return functionalBlcoks;
    }

    public void damage(double damage) {
        health -= damage;
        if (health <= 0) {
            destroy();
        }
    }

    public boolean isOperational() {
        return isOperational;
    }

    public Block getCenter() {
        return center;
    }

    public Block getTop() {
        return top;
    }

    public double getHealth() {
        return health;
    }

    public Team getTeam() {
        return team;
    }

    public void destroy() {
        isOperational = false;
        onDestroy();
    }

    public void remove() {
        blocks.stream().filter(block -> block.getType().isTransparent()).forEach(block -> block.setType(Material.AIR));
        for (Block block : blocks) {
            block.setType(Material.AIR);
        }
    }

    public abstract void tick();
    public abstract void onDestroy();
    public abstract boolean isDestroyable();


}
