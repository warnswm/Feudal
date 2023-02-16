package feudal.gcListeners.peasantsListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.MathUtils;
import feudal.utils.enums.gcEnums.GameClassesIDE;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MinerL implements Listener {

    private static boolean isPlaced(FeudalPlayer feudalPlayer, @NotNull Block block) {
        return block.hasMetadata("PLACED") ||
                feudalPlayer.getAClassID() != GameClassesIDE.MINER.getId() ||
                !block.getType().equals(Material.GOLD_ORE) &&
                        !block.getType().equals(Material.IRON_ORE);
    }

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        Block block = event.getBlock();

        if (isPlaced(feudalPlayer, block)) return;

        block.getWorld().dropItemNaturally(block.getLocation(), block.getType().equals(Material.GOLD_ORE) ?
                feudalPlayer.getGameClassLvl() >= 25 ?
                        new ItemStack(Material.GOLD_INGOT, MathUtils.getRandomInt(1, 4)) :
                        new ItemStack(Material.GOLD_INGOT) :
                feudalPlayer.getGameClassLvl() >= 25 ? new ItemStack(Material.GOLD_INGOT, MathUtils.getRandomInt(1, 4)) :
                        new ItemStack(Material.IRON_INGOT));

    }
}
