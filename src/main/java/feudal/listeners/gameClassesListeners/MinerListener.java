package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.CreateItemUtil;
import feudal.utils.enums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MinerListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (block.hasMetadata("PLACED") ||
                playerInfo.getAClassID() != GameClassesIDEnum.MINER.getId() ||
                        !block.getType().equals(Material.GOLD_ORE) &&
                !block.getType().equals(Material.IRON_ORE)) return;

        block.getWorld().dropItemNaturally(block.getLocation(), block.getType().equals(Material.GOLD_ORE) ? playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.GOLD_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.GOLD_INGOT) : playerInfo.getGameClassLvl() >= 25 ? CreateItemUtil.createItem(Material.IRON_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.IRON_INGOT));
    }
}
