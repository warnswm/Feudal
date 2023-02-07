package feudal.gameClassesListeners.peasants;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
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

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (block.hasMetadata("PLACED") ||
                feudalPlayer.getAClassID() != GameClassesIDEnum.MINER.getId() ||
                !block.getType().equals(Material.GOLD_ORE) &&
                        !block.getType().equals(Material.IRON_ORE)) return;

        block.getWorld().dropItemNaturally(block.getLocation(), block.getType().equals(Material.GOLD_ORE) ? feudalPlayer.getGameClassLvl() >= 25 ? CreateItemUtils.createItem(Material.GOLD_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.GOLD_INGOT) : feudalPlayer.getGameClassLvl() >= 25 ? CreateItemUtils.createItem(Material.IRON_INGOT, (int) (1 + Math.random() * 3)) : new ItemStack(Material.IRON_INGOT));

    }
}
