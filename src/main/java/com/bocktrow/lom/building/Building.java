package com.bocktrow.lom.building;

import com.bocktrow.lom.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

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

        armorStand = (ArmorStand) top.getWorld().spawnEntity(top.getLocation().add(0.5, -0.5, 0.5), EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
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


    public void atick() {
        tick();

        String hbar = ChatColor.GREEN + "";
        int boxes = 10;
        int gboxes = (int) ((health / maxhealth) * 10);

        while (gboxes > 0) {
            boxes--;
            gboxes--;
            hbar += "█";
        }

        hbar += ChatColor.GRAY + "";

        while (boxes > 0) {
            boxes--;
            hbar += "█";
        }


        armorStand.setCustomName(ChatColor.AQUA + "" + ((int) health) + " " + ChatColor.RESET + hbar + " " + ChatColor.YELLOW + ((int) maxhealth));
    }
}
