package com.bocktrow.lom.player;

import com.bocktrow.lom.statistic.Statistic;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerEntity {

    public static void initPlayer(GamePlayer gamePlayer) {
        updatePlayer(gamePlayer);

        Player player = gamePlayer.getPlayer();
        player.setHealth(player.getMaxHealth());
    }

    public static void updatePlayer(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        player.setMaxHealth(gamePlayer.getStatistic(Statistic.HEALTH));
        player.setHealthScale(20.0);
        player.setFoodLevel(25);

        player.setLevel(gamePlayer.getLevel());
        player.setExp((float) gamePlayer.getExperience() / gamePlayer.getExperienceForNext());
    }

    public static void tickPlayer(GamePlayer gamePlayer) {
        gamePlayer.tick();
        gamePlayer.setMana(gamePlayer.getMana() < gamePlayer.getStatistic(Statistic.MANA) ?
                gamePlayer.getMana() + (gamePlayer.getStatistic(Statistic.MANA_REGEN) / 10D) : gamePlayer.getStatistic(Statistic.MANA));
        gamePlayer.getPlayer().setHealth(gamePlayer.getPlayer().getMaxHealth() > gamePlayer.getPlayer().getHealth() + (gamePlayer.getStatistic(Statistic.HEALTH_REGEN) / 10D) ?
                gamePlayer.getPlayer().getHealth() + (gamePlayer.getStatistic(Statistic.HEALTH_REGEN) / 10D) : gamePlayer.getPlayer().getMaxHealth());

        ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
        gamePlayer.getPlayer().getInventory().setBoots(attributes(itemStack, gamePlayer.getStatistic(Statistic.MOVEMENT_SPEED) / 100));
    }

    public static ItemStack attributes(ItemStack itemStack, double movement) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag;
        if (!nmsStack.hasTag()){
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        else {
            tag = nmsStack.getTag();
        }
        NBTTagList am = new NBTTagList();
        NBTTagCompound attribute = new NBTTagCompound();
        //AttributeName:"generic.attackDamage",Name:"generic.attackDamage",Amount:20,Operation:0,UUIDLeast:894654,UUIDMost:2872
        attribute.setString("AttributeName", "generic.movementSpeed");
        attribute.setString("Name", "generic.movementSpeed");
        attribute.setDouble("Amount", movement * 0.7);
        attribute.setInt("Operation", 1);
        attribute.setLong("UUIDLeast", 894654);
        attribute.setLong("UUIDMost", 2872);
        am.add(attribute);
        tag.set("AttributeModifiers", am);
        nmsStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

}
