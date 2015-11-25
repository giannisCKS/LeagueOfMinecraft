package com.bocktrow.lom.building;

import com.bocktrow.lom.LeagueOfMinecraft;
import com.bocktrow.lom.building.tower.Tower;
import com.bocktrow.lom.team.Team;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.apache.commons.lang.SystemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class BuildingManager {

    private static String escapeCharacterForFiles = "\\";
    private static ArrayList<Building> buildings = new ArrayList<>();

    public static void init() {
        buildNexus(new Location(Bukkit.getWorld("world"), 50, 3, 0), Team.RED);
        buildNexus(new Location(Bukkit.getWorld("world"), -50, 3, 0), Team.BLUE);

        buildTower(new Location(Bukkit.getWorld("world"), -41, 3, 4), Team.BLUE);
        buildTower(new Location(Bukkit.getWorld("world"), -41, 3, -4), Team.BLUE);

        buildTower(new Location(Bukkit.getWorld("world"), 41, 3, 4), Team.RED);
        buildTower(new Location(Bukkit.getWorld("world"), 41, 3, -4), Team.RED);

        Bukkit.getScheduler().runTaskTimer(LeagueOfMinecraft.INSTANCE, () -> {
            buildings.stream().forEach(Building::tick);
        }, 1L, 1L);
    }

    public static void buildNexus(Location location, Team team) {
        if (SystemUtils.IS_OS_UNIX) escapeCharacterForFiles = "/";
        File file = new File(LeagueOfMinecraft.INSTANCE.getDataFolder() + escapeCharacterForFiles + "schematics" + escapeCharacterForFiles + "nexus" + ".schematic");
        if (!file.exists()) {
            throw new RuntimeException("File '" + file.getName() + "' does not exist, while it should.");
        }

        try {
            MCEditSchematicFormat schematicFormat = (MCEditSchematicFormat) SchematicFormat.getFormat(file);
            CuboidClipboard region = schematicFormat.load(file);
            BukkitWorld world = new BukkitWorld(Bukkit.getWorld("world"));
            EditSession session = new EditSession(world, 1000000);

            color(region, team);

            org.bukkit.util.Vector vector = location.toVector();
            region.paste(session, new Vector(vector.getX(), vector.getY(), vector.getZ()), false);

        } catch (IOException | DataException | MaxChangedBlocksException e) {
            e.printStackTrace();
        }

    }

    private static void color(CuboidClipboard region, Team team) {
        for (int y = 0; y < region.getHeight() + 1; ++y) {
            for (int x = 0; x < region.getWidth(); ++x) {
                for (int z = 0; z < region.getLength(); ++z) {
                    try {
                        final BaseBlock block = region.getBlock(new Vector(x, y, z));

                        if (block == null) {
                            continue;
                        }

                        switch (block.getType()) {
                            case (19):
                                block.setType(35);
                                break;
                            case (35):
                            case (95):
                            case (171):
                                switch (team) {
                                    case BLUE:
                                        block.setData(11);
                                        break;
                                    case RED:
                                        block.setData(14);
                                        break;
                                }
                                break;
                            default: continue;
                        }
                        region.setBlock(new Vector(x, y, z), block);

                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }

    public static void buildTower(Location location, Team team) {
        if (SystemUtils.IS_OS_UNIX) escapeCharacterForFiles = "/";
        File file = new File(LeagueOfMinecraft.INSTANCE.getDataFolder() + escapeCharacterForFiles + "schematics" + escapeCharacterForFiles + "tower" + ".schematic");
        if (!file.exists()) {
            throw new RuntimeException("File '" + file.getName() + "' does not exist, while it should.");
        }

        try {
            MCEditSchematicFormat schematicFormat = (MCEditSchematicFormat) SchematicFormat.getFormat(file);
            CuboidClipboard region = schematicFormat.load(file);
            BukkitWorld world = new BukkitWorld(Bukkit.getWorld("world"));
            EditSession session = new EditSession(world, 1000000);

            org.bukkit.util.Vector vector = location.toVector();

            color(region, team);


            if (team == Team.RED) {
                region.rotate2D(180);
            }

            region.paste(session, new Vector(vector.getX(), vector.getY(), vector.getZ()), false);

            ArrayList<Block> blocks = new ArrayList<>();
            ArrayList<Block> blocks2 = new ArrayList<>();

            for (int x = -1; x <= 1; x++) {
                for (int y = 1; y <= 5; y++) {
                    for (int z = -1; z <= 1; z++) {
                        Block block = location.clone().add(x, y, z).getBlock();
                        if (block != null && block.getType() != Material.AIR) {
                            blocks.add(block);
                            if (block.getType() == Material.SEA_LANTERN) {
                                blocks2.add(block);
                            }
                        }
                    }
                }
            }

            Tower tower = new Tower(team, location.getBlock(), blocks, blocks2, true);

            buildings.add(tower);

        } catch (IOException | DataException | MaxChangedBlocksException e) {
            e.printStackTrace();
        }

    }

}
