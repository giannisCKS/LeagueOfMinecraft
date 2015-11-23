package com.bocktrow.lom.player.visualize;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class AbilityVisualizer {

    public static void visualize(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        PlayerInventory inventory = player.getInventory();

        try {
            Ability ability1 = gamePlayer.getChampion().getAbilities()[0].newInstance();
            Ability ability2 = gamePlayer.getChampion().getAbilities()[1].newInstance();
            Ability ability3 = gamePlayer.getChampion().getAbilities()[2].newInstance();
            Ability ability4 = gamePlayer.getChampion().getAbilities()[3].newInstance();

            inventory.setItem(1, ItemUtils.makeItem(Material.INK_SACK, ability1.getName(), ability1.getDescription(gamePlayer)));
            inventory.setItem(2, ItemUtils.makeItem(Material.INK_SACK, ability1.getName(), ability2.getDescription(gamePlayer)));
            inventory.setItem(3, ItemUtils.makeItem(Material.INK_SACK, ability1.getName(), ability3.getDescription(gamePlayer)));
            inventory.setItem(4, ItemUtils.makeItem(Material.INK_SACK, ability1.getName(), ability4.getDescription(gamePlayer)));

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
