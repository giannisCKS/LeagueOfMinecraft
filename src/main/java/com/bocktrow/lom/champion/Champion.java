package com.bocktrow.lom.champion;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.ability.global.Recall;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;

import java.util.Arrays;

public abstract class Champion {

    private Ability[] abilitySet;

    public abstract String getName();

    public abstract Class<? extends Ability> getAbilityP();
    public abstract Class<? extends Ability> getAbility1();
    public abstract Class<? extends Ability> getAbility2();
    public abstract Class<? extends Ability> getAbility3();
    public abstract Class<? extends Ability> getAbility4();

    public Ability[] getAbilitySet(GamePlayer gamePlayer) {
        Ability[] abilities = new Ability[5];
        try {
            abilities = new Ability[]{
                    getAbilityP().newInstance(),
                    getAbility1().newInstance(),
                    getAbility2().newInstance(),
                    getAbility3().newInstance(),
                    getAbility4().newInstance(),
                    gamePlayer.getSummonerSpell(),
                    new Recall()
            };
        } catch (InstantiationException | IllegalAccessException e) {
            Arrays.fill(abilities, null);
        }
        return abilities;
    }

    public abstract double getBaseStatIncr(Statistic statistic);

    public abstract double getBaseStat(Statistic statistic);
}
