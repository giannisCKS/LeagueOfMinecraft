package com.bocktrow.lom.statuseffect.global;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.SEffect;
import com.bocktrow.lom.statuseffect.StatusEffect;

public class Ignited extends StatusEffect {

    int tick = 200;

    public Ignited(String source) {
        super(200, source);
    }

    @Override
    public SEffect getEffect() {
        return SEffect.IGNITED;
    }

    @Override
    public String getName() {
        return "Ignited";
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public void effectTick(GamePlayer gamePlayer) {
        if (--tick % 20 == 0) gamePlayer.getPlayer().damage(20.0);
    }
}
