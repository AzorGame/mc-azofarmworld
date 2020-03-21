package io.github.azorgame.azofarmworld.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class FarmWorld {

    private final int MAX_DISTANCE = 10000;

    private Date createdAt;
    private String name;
    private String displayName;

    /**
     * The minecraft world. Can be {@code null}, if the world does not exist.
     */
    private World world;

    private boolean randomTPEnabled;

    public FarmWorld(String name, String displayName, UUID worldID,
                     boolean randomTPEnabled, Date createdAt) {
        this.name = name;
        this.displayName = displayName;
        this.randomTPEnabled = randomTPEnabled;

        world = Bukkit.getWorld(worldID);

        this.createdAt = createdAt;
    }

    public void spawnPlayer(Player player) {
        if (randomTPEnabled) {
            player.teleport(getValidRandomLocation(MAX_DISTANCE));
        } else {
            player.teleport(world.getSpawnLocation());
        }
    }

    private Location getValidRandomLocation(int maxDistance) {
        Random random = new Random();
        int x = random.nextInt(maxDistance);
        int z = random.nextInt(maxDistance);
        int y = 0;
        while (!world.getBlockAt(x, y, z).isEmpty()) {
            y = y + 2;
        }
        return new Location(world, x, y, z);
    }

    public FarmWorldType getWorldEnvType() {
        if (world == null) {
            return null;
        }

        switch (world.getEnvironment()) {
        case NETHER:
            return FarmWorldType.NETHER;
        case NORMAL:
            return FarmWorldType.NORMAL;
        case THE_END:
            return FarmWorldType.END;
        default:
            return FarmWorldType.UNDEFINED;
        }
    }

    public String getDisplayName() {
        return displayName;
    }
}
