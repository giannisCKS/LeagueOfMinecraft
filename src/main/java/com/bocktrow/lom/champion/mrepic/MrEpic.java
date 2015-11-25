package com.bocktrow.lom.champion.mrepic;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.champion.BaseStats;
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
            case ABILITY_POWER: return 0;
            case ATTACK_DAMAGE: return 5;
            case HEALTH: return 35;
            case MANA: return 20;
        }
        return 0;
    }

    @Override
    public double getBaseStat(Statistic statistic) {
        switch (statistic) {
            case ABILITY_POWER: return 0;
            case ATTACK_DAMAGE: return 32;
            case HEALTH: return 700;
            case MANA: return 500;
            case MANA_REGEN: return 5;
            case HEALTH_REGEN: return 3;
        }
        return BaseStats.get(statistic);
    }
}
