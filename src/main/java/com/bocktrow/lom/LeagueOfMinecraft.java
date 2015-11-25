package com.bocktrow.lom;

import com.bocktrow.lom.ability.AbilityCaster;
import com.bocktrow.lom.building.BuildingManager;
import com.bocktrow.lom.gametick.Tick;
import com.bocktrow.lom.player.BasicListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LeagueOfMinecraft extends JavaPlugin {

    public static Plugin INSTANCE;

    @Override
    public void onEnable() {

        INSTANCE = this;

        getLogger().info("Worked! :D");
        getServer().getPluginManager().registerEvents(new BasicListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityCaster(), this);

        BuildingManager.init();
        Tick.init();
    }
}
