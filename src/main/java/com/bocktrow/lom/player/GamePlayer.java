package com.bocktrow.lom.player;

import com.bocktrow.lom.ability.Ability;
import com.bocktrow.lom.ability.global.Flash;
import com.bocktrow.lom.champion.Champion;
import com.bocktrow.lom.item.Item;
import com.bocktrow.lom.player.visualize.ScoreboardVisualizer;
import com.bocktrow.lom.player.visualize.TeamVisualizer;
import com.bocktrow.lom.statistic.Statistic;
import com.bocktrow.lom.statuseffect.StatusEffect;
import com.bocktrow.lom.statuseffect.global.Homeguard;
import com.bocktrow.lom.team.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GamePlayer {

    private static HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public static void createGamePlayer(Player player, Champion champion, Team team) {
        gamePlayers.put(player, new GamePlayer(champion, player, team));
    }

    public static void deleteGamePlayer(Player player) {
        gamePlayers.remove(player);
    }

    //Actual class

    public static GamePlayer getGamePlayer(Player player) {
        return gamePlayers.get(player);
    }

    private Team team;
    private Champion champion;
    private Player player;
    private boolean dead = false;
    private int level = 1;
    private int experience = 0;
    private Ability[] abilities;
    private double mana;
    private ArrayList<StatusEffect> statusEffects = new ArrayList<>();

    private int kills = 0, deaths = 0, assists = 0;
    private int gold = 0;

    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<Statistic, Double> statistics = new HashMap<>();

    public GamePlayer(Champion champion, Player player, Team team) {
        this.champion = champion;
        this.player = player;
        this.team = team;

        mana = (int) getStatistic(Statistic.MANA);
        abilities = champion.getAbilitySet(this);
        ScoreboardVisualizer.init(player);
        PlayerEntity.initPlayer(this);
    }

    public static HashMap<Player, GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public boolean isDead() {
        return dead;
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
        TeamVisualizer.updateEntry(this);

        if (isDead()) return;

        Iterator<StatusEffect> iterator = statusEffects.iterator();
        while (iterator.hasNext()) {
            StatusEffect statusEffect = iterator.next();
            statusEffect.tick(this.getPlayer());
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

    public void die() {
        dead = true;
        statusEffects.clear();
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public Team getTeam() {
        return team;
    }
}
