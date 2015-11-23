package com.bocktrow.lom.champion.mrepic;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.champion.Champion;
import com.bocktrow.lom.champion.mrepic.ability.Desolve;

public class MrEpic extends Champion {

    @Override
    public double getBaseAP() {
        return 10;
    }

    @Override
    public double getScalePerLevelAP() {
        return 2;
    }

    @Override
    public Class<? extends Ability>[] getAbilities() {
        Class<? extends Ability>[] abilities = (Class<? extends Ability>[]) new Object[4];
        abilities[0] = Desolve.class;
        abilities[1] = Desolve.class;
        abilities[2] = Desolve.class;
        abilities[3] = Desolve.class;
        return abilities;
    }
}
