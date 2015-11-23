package com.bocktrow.lom;

import com.bocktrow.lom.ability.AbilityCaster;
import com.bocktrow.lom.player.BasicListener;
import org.bukkit.plugin.java.JavaPlugin;

public class LeagueOfMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Worked! :D");
        getServer().getPluginManager().registerEvents(new BasicListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityCaster(), this);
    }
}
