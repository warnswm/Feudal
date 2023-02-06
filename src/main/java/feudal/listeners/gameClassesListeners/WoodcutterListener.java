package feudal.listeners.gameClassesListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class WoodcutterListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());
        Block block = event.getBlock();

        if (block.hasMetadata("PLACED") ||
                feudalPlayer.getAClassID() != GameClassesIDEnum.WOODCUTTER.getId()) return;


        if (feudalPlayer.getGameClassLvl() <= 75)
            cutDownTree(block.getLocation(), event.getPlayer().getInventory().getItemInMainHand());

    }

    private void cutDownTree(@NotNull Location location, ItemStack handStack) {

        LinkedList<Block> blocks = new LinkedList<>();

        for (int i = location.getBlockY(); i < location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ()); ) {

            Location l = location.add(0, 1, 0);
            Block block = l.getBlock();

            if (block.getType().equals(Material.LOG)) {

                blocks.add(l.getBlock());

                i++;
                continue;
            }
            break;
        }

        for (Block block : blocks) {

            if (!block.breakNaturally(handStack)) return;

            handStack.setDurability((short) (handStack.getDurability() + 1));

            if (handStack.getType().getMaxDurability() == handStack.getDurability()) {
                handStack.setType(Material.AIR);
                return;
            }

        }
    }
}
