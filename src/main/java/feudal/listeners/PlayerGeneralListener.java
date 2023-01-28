package feudal.listeners;

import feudal.info.CachePlayers;
import feudal.info.PlayerInfo;
import feudal.utils.CreateItemUtil;
import feudal.utils.gameClassesEnums.GameClassesIDEnum;
import feudal.utils.gameClassesEnums.MoneyForMobsEnum;
import feudal.utils.gameClassesEnums.RedtoneMaterialEnum;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerGeneralListener implements Listener {

    @EventHandler
    public void playerTeleport(@NotNull PlayerTeleportEvent event) {

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.getPlayer().setCooldown(Material.ENDER_PEARL, 220);
    }
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

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (block.hasMetadata("PLACED")) return;

        if (playerInfo.getAClassID() == GameClassesIDEnum.MINER.getId() &&
                block.getType().equals(Material.GOLD_ORE) ||
                block.getType().equals(Material.IRON_ORE))
            block.getWorld().dropItemNaturally(block.getLocation(), block.getType().equals(Material.GOLD_ORE) ? playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.GOLD_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.GOLD_INGOT) : playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.IRON_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.IRON_INGOT));

        else if (playerInfo.getAClassID() == GameClassesIDEnum.WOODCUTTER.getId())
            cutDownTree(block.getLocation(), event.getPlayer().getItemInHand());

    }
    private void cutDownTree(@NotNull Location location, ItemStack handStack) {

        LinkedList<Block> blocks = new LinkedList<>();


        for (int i = location.getBlockY(); i < location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ());) {

            Location l = location.add(0, 1, 0);
            Block block = l.getBlock();

            if (block.getType().equals(Material.LOG) || block.getType().equals(Material.LEAVES)) {

                blocks.add(l.getBlock());

                i++;
                continue;
            }
            break;
        }

        for (Block block : blocks) {

            if (!block.breakNaturally(handStack)) return;

            handStack.setDurability((short)(handStack.getDurability() + 1));

            if (handStack.getType().getMaxDurability() == handStack.getDurability()) {
                handStack.setType(Material.AIR);
                return;
            }

        }
    }
}
