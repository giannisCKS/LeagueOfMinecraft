package com.bocktrow.lom.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Random;

public class ItemUtils {

    public static ItemStack makeItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack makeHead(String username, String name, String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setOwner(username);
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack makeItem(Material material, int count, String name, String... lore) {
        ItemStack item = new ItemStack(material, count);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack makeItem(Material material, int count, int data, String name, String... lore) {
        ItemStack item = new ItemStack(material, count, (short) data);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));

        item.setItemMeta(itemMeta);

        return item;
    }
}
