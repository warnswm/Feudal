package feudal.minecraftPatches.redstone;

import feudal.utils.enums.RedtoneMaterialEnum;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class PlaceRedstoneListener implements Listener {
    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType() == null || !RedtoneMaterialEnum.getByMaterial(block.getType())) return;

        Chunk chunk = event.getBlock().getChunk();

        int count = 0;
        Block blockChunk;

        for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {

            for (int z = chunk.getZ() * 16; z < chunk.getZ() * 16 + 16; z++) {

                for (int y = 0; y < 256; y++) {

                    blockChunk = chunk.getBlock(x, y, z);

                    if (blockChunk.getType().equals(Material.TNT))
                        count += 4;

                    if (RedtoneMaterialEnum.getByMaterial(blockChunk.getType()))
                        count++;
                }

            }

        }

        if (count > 50) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Слишком много редстоун блоков!");
        }
    }
}
