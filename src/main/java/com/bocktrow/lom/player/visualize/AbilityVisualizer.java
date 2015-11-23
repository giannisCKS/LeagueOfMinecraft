package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class AbilityVisualizer {

    public static void visualize(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        PlayerInventory inventory = player.getInventory();

        for (int i = 1; i <= 4; i++) {
            Ability ability = gamePlayer.getAbility(i);
            inventory.setItem(i, ItemUtils.makeItem(Material.INK_SACK, 1, 10, ChatColor.YELLOW + "" + ChatColor.BOLD + ability.getName(),formatText(ability.getDescription(gamePlayer))));
        }
    }

    private static String[] formatText(String[] des) {
        String[] description = new String[des.length];
        System.arraycopy(des, 0, description, 0, des.length);
        for (int i = 0; i < description.length; i++) {
            String temp = description[i];
            temp = ChatColor.GRAY + temp;
            temp = temp.replace("[G]", ChatColor.GRAY + "");
            temp = temp.replace("[Y]", ChatColor.YELLOW + "");
            description[i] = temp;
        }
        return description;
    }

}
