package feudal.utils;


import feudal.data.cache.CacheFeudalKingdoms;
import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class TeleportUtils {

    @Contract("_, _, _, _, _ -> new")
    public @NotNull Location rtpCalc(Player player, int minX, int maxX, int minZ, int maxZ) {

        World world = Bukkit.getWorld("world");
        int xCord = ThreadLocalRandom.current().nextInt(minX, maxX), zCord = ThreadLocalRandom.current().nextInt(minZ, maxZ), yCord;

        Chunk chunk = world.getChunkAt(xCord, zCord);
        if (CacheFeudalKingdoms.checkPrivate(chunk, player))
            return rtpCalc(player, minX, maxX, minZ, maxZ);

        for (int y = 255; y > 0; y--) {

            Block block = world.getBlockAt(xCord, y, zCord);
            Material material = block.getType();

            if (material.isTransparent() ||
                    material.equals(Material.STATIONARY_LAVA) ||
                    material.equals(Material.AIR))
                continue;

            yCord = block.getY() + 1;

            return new Location(world, xCord, yCord, zCord);

        }

        return player.getLocation();

    }

    public @NotNull Location chunkCalc(Player player, @NotNull Chunk chunk) {

        World world = Bukkit.getWorld("world");

        int xCord = chunk.getX(), yCord, zCord = chunk.getZ();

        for (int y = 255; y > 0; y--) {

            Block block = world.getBlockAt(xCord, y, zCord);
            Material material = block.getType();

            if (material.isTransparent() ||
                    material.equals(Material.STATIONARY_LAVA) ||
                    material.equals(Material.AIR))
                continue;

            yCord = block.getY() + 1;

            return new Location(world, xCord, yCord, zCord);

        }

        return player.getLocation();

    }
}
