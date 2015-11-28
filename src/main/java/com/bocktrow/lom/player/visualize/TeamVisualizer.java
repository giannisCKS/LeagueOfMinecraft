package com.bocktrow.lom.player.visualize;

import com.avaje.ebean.PagingList;
import com.bocktrow.lom.player.GamePlayer;
import com.bocktrow.lom.statuseffect.SEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class TeamVisualizer {

    static class SEntry {

        private String prefix, suffix;

        public SEntry(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getSuffix() {
            return suffix;
        }
    }

    public static HashMap<String, SEntry> entries = new HashMap<>();

    public static void updateEntry(GamePlayer gamePlayer) {
        entries.put(gamePlayer.getPlayer().getName(), new SEntry(
                ChatColor.GRAY + "[" + gamePlayer.getChampion().getName()  + "] " + gamePlayer.getTeam().getChatColor(),
                ChatColor.GRAY + " [" + gamePlayer.getKills() + "/" +  gamePlayer.getDeaths() + "/" + gamePlayer.getAssists()  + "]"
        ));
    }

    public static void updateScoreboards() {
        Bukkit.getOnlinePlayers().stream().filter(player -> GamePlayer.getGamePlayer(player) != null).forEach(player -> {
            GamePlayer gamePlayer = GamePlayer.getGamePlayer(player);
            Player player1 = gamePlayer.getPlayer();
            Scoreboard scoreboard = player1.getScoreboard();

            entries.keySet().stream().filter(s -> scoreboard.getTeam(s) == null).forEach(s -> {
                Team team = scoreboard.registerNewTeam(s);
                team.setPrefix(entries.get(s).getPrefix());
                team.setSuffix(entries.get(s).getSuffix());
            });
            scoreboard.getTeams().stream().filter(team -> !entries.containsKey(team.getName())).forEach(Team::unregister);

            scoreboard.getTeams().stream().forEach(team -> {
                if (entries.containsKey(team.getName())) {
                    if (!entries.get(team.getName()).getPrefix().equals(team.getPrefix())) {
                        team.setPrefix(entries.get(team.getName()).getPrefix());
                    }
                    if (!entries.get(team.getName()).getSuffix().equals(team.getSuffix())) {
                        team.setSuffix(entries.get(team.getName()).getSuffix());
                    }
                }
            });
        });
    }
}
