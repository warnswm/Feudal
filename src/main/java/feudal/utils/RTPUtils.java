package feudal.utils;


import feudal.data.cache.CacheFeudalKingdoms;
import feudal.utils.wrappers.ChunkWrapper;
import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RTPUtils {

    @Contract("_, _, _, _, _ -> new")
    public @NotNull Location rtpCalc(Player player, int minX, int maxX, int minZ, int maxZ) {

        World world = Bukkit.getWorld("world");
        int xCord = ThreadLocalRandom.current().nextInt(minX, maxX), zCord = ThreadLocalRandom.current().nextInt(minZ, maxZ), yCord = 0;

        Chunk chunk = world.getChunkAt(xCord, zCord);
        if (CacheFeudalKingdoms.checkPrivate(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode(), player))
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

        return rtpCalc(player, minX, maxX, minZ, maxZ);

    }
}
