package com.bocktrow.lom.champion;

import com.bocktrow.lom.ability.Ability;

import java.util.Arrays;

public abstract class Champion {

    private Ability[] abilitySet;

    public abstract double getBaseAP();
    public abstract double getScalePerLevelAP();

    public abstract Class<? extends Ability> getAbilityP();
    public abstract Class<? extends Ability> getAbility1();
    public abstract Class<? extends Ability> getAbility2();
    public abstract Class<? extends Ability> getAbility3();
    public abstract Class<? extends Ability> getAbility4();

    public Ability[] getAbilitySet() {
        Ability[] abilities = new Ability[5];
        try {
            abilities = new Ability[]{
                    getAbilityP().newInstance(),
                    getAbility1().newInstance(),
                    getAbility2().newInstance(),
                    getAbility3().newInstance(),
                    getAbility4().newInstance()
            };
        } catch (InstantiationException | IllegalAccessException e) {
            Arrays.fill(abilities, null);
        }
        return abilities;
    }
}
