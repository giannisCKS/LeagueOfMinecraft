package com.bocktrow.lom.statuseffect.global;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.SEffect;
import com.bocktrow.lom.statuseffect.StatusEffect;

public class Homeguard extends StatusEffect {

    public Homeguard(String source) {
        super(50, source);
        getBonusStats().put(Statistic.MOVEMENT_SPEED, 100D);
    }

    @Override
    public SEffect getEffect() {
        return SEffect.HOMEGUARD;
    }

    @Override
    public String getName() {
        return "Homeguard";
    }

    @Override
    public boolean isPositive() {
        return true;
    }

    @Override
    public void effectTick(GamePlayer gamePlayer) {

    }
}
