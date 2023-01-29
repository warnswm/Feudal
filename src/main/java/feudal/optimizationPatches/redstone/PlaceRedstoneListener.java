package feudal.optimizationPatches.redstone;

import feudal.utils.enums.RedtoneMaterialEnum;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class PlaceRedstoneListener implements Listener {
    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType() == null || !RedtoneMaterialEnum.getByMaterial(block.getType())) return;

        Chunk chunk = event.getBlock().getChunk();

        new Thread(() -> {

            AtomicInteger count = new AtomicInteger();

            for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {

                for (int z = chunk.getZ() * 16; z < chunk.getZ() * 16 + 16; z++) {

                    for (int y = 0; y < 256; y++) {

                        Block blockChunk = chunk.getBlock(x, y, z);

                        if (blockChunk.getType().equals(Material.TNT))
                            count.addAndGet(5);

                        if (RedtoneMaterialEnum.getByMaterial(blockChunk.getType()))
                            count.getAndIncrement();
                    }

                }

            }

            if (count.get() > 50) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("Слишком много редстоун блоков!");
            }
        }).start();

    }
}
