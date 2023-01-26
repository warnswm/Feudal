package feudal.listeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.gameClassesEnums.MoneyForMobsEnum;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGeneralListener implements Listener {

    @EventHandler
    public void playerTeleport(PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);
    }
    @EventHandler
    public void playerPlaceBlock(BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType() == null && block.getType() != Material.REDSTONE_BLOCK &&
                block.getType() != Material.REDSTONE &&
                block.getType() != Material.REDSTONE_COMPARATOR &&
                block.getType() != Material.REDSTONE_TORCH_OFF &&
                block.getType() != Material.REDSTONE_TORCH_ON &&
                block.getType() != Material.PISTON_BASE &&
                block.getType() != Material.PISTON_MOVING_PIECE &&
                block.getType() != Material.PISTON_EXTENSION &&
                block.getType() != Material.PISTON_STICKY_BASE &&
                block.getType() != Material.LEVER &&
                block.getType() != Material.STONE_BUTTON &&
                block.getType() != Material.WOOD_BUTTON &&
                block.getType() != Material.WOOD_PLATE &&
                block.getType() != Material.GOLD_PLATE &&
                block.getType() != Material.STONE_PLATE &&
                block.getType() != Material.IRON_PLATE &&
                block.getType() != Material.RAILS &&
                block.getType() != Material.ACTIVATOR_RAIL &&
                block.getType() != Material.POWERED_RAIL &&
                block.getType() != Material.DETECTOR_RAIL &&
                block.getType() != Material.TRIPWIRE_HOOK &&
                block.getType() != Material.TRAPPED_CHEST) return;

        Chunk chunk = event.getBlock().getChunk();

        int count = 0;

        for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {

            for (int z = chunk.getZ() * 16; z < chunk.getZ() * 16 + 16; z++) {

                for (int y = 0; y < 256; y++) {

                    Block chunkBlock = chunk.getBlock(x, y, z);

                    if (chunkBlock.getType() != null && chunkBlock.getType() == Material.REDSTONE_BLOCK ||
                            chunkBlock.getType() == Material.REDSTONE ||
                            chunkBlock.getType() == Material.REDSTONE_COMPARATOR ||
                            chunkBlock.getType() == Material.REDSTONE_TORCH_OFF ||
                            chunkBlock.getType() == Material.REDSTONE_TORCH_ON ||
                            chunkBlock.getType() == Material.PISTON_BASE ||
                            chunkBlock.getType() == Material.PISTON_MOVING_PIECE ||
                            chunkBlock.getType() == Material.PISTON_EXTENSION ||
                            chunkBlock.getType() == Material.PISTON_STICKY_BASE ||
                            chunkBlock.getType() == Material.LEVER ||
                            chunkBlock.getType() == Material.STONE_BUTTON ||
                            chunkBlock.getType() == Material.WOOD_BUTTON ||
                            chunkBlock.getType() == Material.WOOD_PLATE ||
                            chunkBlock.getType() == Material.GOLD_PLATE ||
                            chunkBlock.getType() == Material.STONE_PLATE ||
                            chunkBlock.getType() == Material.IRON_PLATE ||
                            chunkBlock.getType() == Material.RAILS ||
                            chunkBlock.getType() == Material.ACTIVATOR_RAIL ||
                            chunkBlock.getType() == Material.POWERED_RAIL ||
                            chunkBlock.getType() == Material.DETECTOR_RAIL ||
                            chunkBlock.getType() == Material.TRIPWIRE_HOOK ||
                            chunkBlock.getType() == Material.TRAPPED_CHEST)
                        count++;

                    if (count > 50) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Слишком много редстоун блоков!");
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerAttack(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        playerInfo.addBalance(MoneyForMobsEnum.getByEntity(event.getEntityType()));
    }
}
