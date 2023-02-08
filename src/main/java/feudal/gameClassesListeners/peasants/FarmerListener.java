package feudal.gameClassesListeners.peasants;

import feudal.utils.enums.gameClassesEnums.BlocksForFarmerEnum;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FarmerListener implements Listener {

    @EventHandler
    public void playerUsedBoneMeal(@NotNull PlayerInteractEvent event) {

        Block block = event.getClickedBlock();
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
                !mainHand.getType().equals(Material.INK_SACK) ||
                !BlocksForFarmerEnum.getPlant(block.getType()) ||
                block.getData() == CropState.RIPE.getData()) return;

        mainHand.setAmount(mainHand.getAmount() + 1);

        if (block.getType().equals(Material.BEETROOT_BLOCK)) {

            block.setData((byte) 3);
            return;

        }

        block.setData(CropState.RIPE.getData());
    }
}
