package com.bocktrow.lom.champion.mrepic;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.champion.Champion;
import com.bocktrow.lom.champion.mrepic.ability.Desolve;

public class MrEpic extends Champion {

    @Override
    public String getName() {
        return "Mr. Epic";
    }

    @Override
    public double getBaseAP() {
        return 10;
    }

    @Override
    public double getScalePerLevelAP() {
        return 2;
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
}
