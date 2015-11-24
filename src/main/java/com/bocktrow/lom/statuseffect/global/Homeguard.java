package com.bocktrow.lom.statuseffect.global;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.SEffect;
import com.bocktrow.lom.statuseffect.StatusEffect;

public class Homeguard extends StatusEffect {

    public double mvmsp = 100D;

    public Homeguard(String source) {
        super(100, source);
        getBonusStats().put(Statistic.MOVEMENT_SPEED, mvmsp);
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
        mvmsp--;
        getBonusStats().put(Statistic.MOVEMENT_SPEED, mvmsp);
    }
}
