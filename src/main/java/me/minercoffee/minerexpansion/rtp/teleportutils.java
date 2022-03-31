package me.minercoffee.minerexpansion.rtp;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class teleportutils {
    static MinerExpansion plugin;
    public static HashSet<Material> bad_blocks = new HashSet<>();
    static{
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location generateLocation(Player player){
        //Generate Random Location
        Random random = new Random();

        int x = 0;
        int z = 0;
        int y = 0;

        if(plugin.getConfig().getBoolean("world-border")){ //If they want to limit the distance
            x = random.nextInt(plugin.getConfig().getInt("border"));
            z = random.nextInt(plugin.getConfig().getInt("border"));
            y = 100;
        }else if(!plugin.getConfig().getBoolean("world-border")){ //If they dont
            x = random.nextInt(25000); //25000 is default
            z = random.nextInt(25000);
            y = 100;
        }
        Location randomLocation = new Location(player.getWorld(), x, y + 2, z);
        if(!randomLocation.getChunk().isLoaded()){
            randomLocation.getChunk().load(true);
            y = player.getWorld().getHighestBlockYAt(randomLocation);
            randomLocation.setY(y);
        }
        return randomLocation;
    }

    public static Location findSafeLocation(Player player){

        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)){
            //Keep looking for a safe location
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid()) || (!above.isLiquid());
    }
    public static Location findSafeSpawnLocation(Location spawn) {
        World world = spawn.getWorld();
        int blockX = spawn.getBlockX();
        int blockY = spawn.getBlockY();
        int blockZ = spawn.getBlockZ();

        int highestY = 100;
        int y = blockY;
        boolean wasPreviousSafe = false;
        for (; y <= highestY; y++) {
            Material type = null;
            if (world != null) {
                type = world.getBlockAt(blockX, y, blockZ).getType();
            }
            boolean safe = Material.AIR.equals(type);
            if (wasPreviousSafe && safe) {
                y--;
                break;
            }
            wasPreviousSafe = safe;
        }
        return new Location(world, blockX + 0.5, y, blockZ + 0.5);
    }
}