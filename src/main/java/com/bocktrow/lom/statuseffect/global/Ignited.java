package com.bocktrow.lom.statuseffect.global;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.SEffect;
import com.bocktrow.lom.statuseffect.StatusEffect;
import com.bocktrow.lom.utils.ParticleEffect;
import org.bukkit.entity.LivingEntity;

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
    public void effectTick(LivingEntity entity) {
        ParticleEffect.FLAME.display(.2F, .5F, .2F, 0, (int) (tick / 80) + 1, entity.getLocation().add(0, 1, 0), 24);
        if (--tick % 20 == 0) entity.damage(20.0);
    }
}
