package com.bocktrow.lom.player;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.ability.global.Flash;
import com.bocktrow.lom.champion.Champion;
import com.bocktrow.lom.item.Item;
import com.bocktrow.lom.player.visualize.ScoreboardVisualizer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.StatusEffect;
import com.bocktrow.lom.statuseffect.global.Homeguard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GamePlayer {

    private static HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public static void createGamePlayer(Player player, Champion champion) {
        gamePlayers.put(player, new GamePlayer(champion, player));
    }

    public static void deleteGamePlayer(Player player) {
        gamePlayers.remove(player);
    }

    //Actual class

    public static GamePlayer getGamePlayer(Player player) {
        return gamePlayers.get(player);
    }

    private Champion champion;
    private Player player;
    private int level = 1;
    private int experience = 0;
    private Ability[] abilities;
    private double mana;
    private ArrayList<StatusEffect> statusEffects = new ArrayList<>();

    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<Statistic, Double> statistics = new HashMap<>();

    public GamePlayer(Champion champion, Player player) {
        this.champion = champion;
        this.player = player;

        mana = (int) getStatistic(Statistic.MANA);
        abilities = champion.getAbilitySet(this);
        ScoreboardVisualizer.init(player);
        PlayerEntity.initPlayer(this);
    }

    public static HashMap<Player, GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Player getPlayer() {
        return player;
    }

    public double getStatistic(Statistic statistic) {
        double stat = champion.getBaseStat(statistic) + (1 - level) * champion.getBaseStatIncr(statistic) + (statistics.containsKey(Statistic.ABILITY_POWER) ? statistics.get(Statistic.ABILITY_POWER) : 0);
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect.getBonusStats().containsKey(statistic)) {
                stat += statusEffect.getBonusStats().get(statistic);
            }
        }
        return stat;
    }

    public int getLevel() {
        return level;
    }

    public Champion getChampion() {
        return champion;
    }

    public void earnExperience(int experience) {
        this.experience += experience;
    }

    public Ability getAbility(int i) {
        return abilities[i];
    }

    public void calculateStatsFromItems() {
        statistics.clear();
        for (Item item : items) {
            for (Statistic statistic : Statistic.values()) {
                if (statistics.containsKey(statistic)) {
                    statistics.put(statistic, statistics.get(statistic) + item.getStatisticGain(statistic));
                } else {
                    statistics.put(statistic, item.getStatisticGain(statistic));
                }
            }
        }
    }

    public void calculateExperience() {
        while (experience >= 500) {
            experience-=500;
            level++;
        }
    }

    public void tick() {
        calculateStatsFromItems();
        calculateExperience();

        Iterator<StatusEffect> iterator = statusEffects.iterator();
        while (iterator.hasNext()) {
            StatusEffect statusEffect = iterator.next();
            statusEffect.tick(this);
            if (statusEffect.getDuration() == 0) {
                statusEffects.remove(statusEffect);
                return;
            }
        }
    }

    public ArrayList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public int getExperienceForNext() {
        return 500;
    }

    public int getExperience() {
        return experience;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public Ability getSummonerSpell() {
        return new Flash();
    }

    public void addStatusEffect(StatusEffect statusEffect) {
        for (StatusEffect statusEffect1 : statusEffects) {
            if (statusEffect1.getEffect() == statusEffect.getEffect()) {
                statusEffects.remove(statusEffect1);
                break;
            }
        }

        statusEffects.add(statusEffect);
    }
}
