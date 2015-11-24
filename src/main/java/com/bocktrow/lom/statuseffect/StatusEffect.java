package com.bocktrow.lom.statuseffect;

import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statistic.Statistic;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class StatusEffect {

    public abstract SEffect getEffect();
    public abstract String getName();
    public abstract boolean isPositive();

    private HashMap<Statistic, Double> bonusStats = new HashMap<>();
    private int duration;
    private String source;

    public StatusEffect(int duration, String source) {
        this.duration = duration;
        this.source = source;
    }

    public void tick(GamePlayer gamePlayer) {
        if (duration > 0) {
            duration--;
            effectTick(gamePlayer);
        }
    }

    public abstract void effectTick(GamePlayer gamePlayer);

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getSource() {
        return source;
    }

    public HashMap<Statistic, Double> getBonusStats() {
        return bonusStats;
    }
}
