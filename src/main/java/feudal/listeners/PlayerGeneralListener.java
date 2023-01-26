package feudal.listeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.gameClassesEnums.MoneyForMobsEnum;
import feudal.utils.gameClassesEnums.RedtoneMaterialEnum;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGeneralListener implements Listener {

    @EventHandler
    public void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);
    }
    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (block.getType() == null && !RedtoneMaterialEnum.getByMaterial(block.getType())) return;

        Chunk chunk = event.getBlock().getChunk();

        int count = 0;

        for(int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {

            for(int z = chunk.getZ() * 16; z < chunk.getZ() * 16 + 16; z++) {

                for(int y = 0; y < 256; y++) {

                    Block blockChunk = chunk.getBlock(x, y, z);

                    if (blockChunk.getType().equals(Material.TNT))
                        count += 5;

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

    @EventHandler
    public void entityDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;

        Player player = event.getEntity().getKiller();
        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(player);

        if (event.getEntityType() == EntityType.PLAYER) {

            PlayerInfo playerDeathInfo = CachePlayers.getPlayerInfo().get((Player) event.getEntity());

            int temp = (int) (playerDeathInfo.getBalance() / 100 * (3 + Math.random() * 5));

            playerInfo.addBalance(temp);

            playerDeathInfo.takeBalance((int) (temp + (1 + Math.random() * 3)));
        }
        else
            playerInfo.addBalance(MoneyForMobsEnum.getByEntity(event.getEntityType()));

    }
}
