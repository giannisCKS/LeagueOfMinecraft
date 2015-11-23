package com.bocktrow.lom.champion;

import com.bocktrow.lom.ability.Ability;

public abstract class Champion {

    public abstract double getBaseAP();
    public abstract double getScalePerLevelAP();

    public abstract Class<? extends Ability> getAbility1();
    public abstract Class<? extends Ability> getAbility2();
    public abstract Class<? extends Ability> getAbility3();
    public abstract Class<? extends Ability> getAbility4();

}
