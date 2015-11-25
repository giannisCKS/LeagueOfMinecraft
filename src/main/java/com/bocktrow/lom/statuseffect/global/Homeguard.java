package com.bocktrow.lom.statuseffect.global;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.SEffect;
import com.bocktrow.lom.statuseffect.StatusEffect;
import com.bocktrow.lom.utils.ParticleEffect;

public class Homeguard extends StatusEffect {

    public double mvmsp = 100D;

    public Homeguard(String source) {
        super(200, source);
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
        mvmsp-= 0.5;
        getBonusStats().put(Statistic.MOVEMENT_SPEED, mvmsp);
        ParticleEffect.CRIT_MAGIC.display(.2F, .5F, .2F, 0, (int) (mvmsp / 10), gamePlayer.getPlayer().getLocation().add(0, 1, 0), 24);
    }
}
