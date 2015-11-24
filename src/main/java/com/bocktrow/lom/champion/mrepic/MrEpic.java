package com.bocktrow.lom.champion.mrepic;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.champion.Champion;
import com.bocktrow.lom.champion.mrepic.ability.Desolve;
import com.bocktrow.lom.statistic.Statistic;

public class MrEpic extends Champion {

    @Override
    public String getName() {
        return "Mr. Epic";
    }

    @Override
    public Class<? extends Ability> getAbilityP() {
        return Desolve.class;
    }

    @Override
    public Class<? extends Ability> getAbility1() {
        return Desolve.class;
    }

    @Override
    public Class<? extends Ability> getAbility2() {
        return Desolve.class;
    }

    @Override
    public Class<? extends Ability> getAbility3() {
        return Desolve.class;
    }

    @Override
    public Class<? extends Ability> getAbility4() {
        return Desolve.class;
    }

    @Override
    public double getBaseStatIncr(Statistic statistic) {
        switch (statistic) {
            case ABILITY_POWER: return 2;
            case ATTACK_DAMAGE: return 5;
            case HEALTH: return 25;
            case MANA: return 10;
        }
        return 0;
    }

    @Override
    public double getBaseStat(Statistic statistic) {
        switch (statistic) {
            case ABILITY_POWER: return 10;
            case ATTACK_DAMAGE: return 32;
            case HEALTH: return 300;
            case MANA: return 300;
            case MANA_REGEN: return 5;
            case HEALTH_REGEN: return 3;
            case COOLDOWN_REDUCTION: return 0.9;
        }
        return 0;
    }
}
