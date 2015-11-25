package com.bocktrow.lom.building;

import com.bocktrow.lom.LeagueOfMinecraft;
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

import java.io.File;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class BuildingManager {

    private static String escapeCharacterForFiles = "\\";

    public static void init() {
        buildNexus(new Location(Bukkit.getWorld("world"), 50, 3, 0), Team.RED);
        buildNexus(new Location(Bukkit.getWorld("world"), -50, 3, 0), Team.BLUE);
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

            for (int y = 0; y < region.getHeight() + 1; ++y) {
                for (int x = 0; x < region.getWidth(); ++x) {
                    for (int z = 0; z < region.getLength(); ++z) {
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
                        }
                    }
                }
            }

            org.bukkit.util.Vector vector = location.toVector();
            region.paste(session, new Vector(vector.getX(), vector.getY(), vector.getZ()), false);

        } catch (IOException | DataException | MaxChangedBlocksException e) {
            e.printStackTrace();
        }

    }

}
