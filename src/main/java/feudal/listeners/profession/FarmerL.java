package feudal.listeners.profession;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum BlocksForFarmerE {
    CROPS(Material.CROPS, 3), POTATO(Material.POTATO, 5), BEETROOT_BLOCK(Material.BEETROOT_BLOCK, 8),
    CARROT(Material.CARROT, 15), PUMPKIN_STEM(Material.PUMPKIN_STEM, 25), COCOA(Material.COCOA, 25),
    MELON_STEM(Material.MELON_STEM, 25);

    Material material;
    int attributeExp;

    public static int getAttributeExpByMaterial(Material material) {

        if (material == null) return 0;

        for (BlocksForFarmerE blocksForFarmerE : values())
            if (blocksForFarmerE.getMaterial() == material)
                return blocksForFarmerE.getAttributeExp();

        return 0;

    }

    public static boolean checkPlant(Material material) {

        if (material == null) return false;

        for (BlocksForFarmerE blocksForFarmerE : values())
            if (blocksForFarmerE.getMaterial() == material)
                return true;

        return false;

    }
}

public class FarmerL implements Listener {

    private static boolean isaBoolean(@NotNull PlayerInteractEvent event, Block block, ItemStack mainHand) {
        return !event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
                !mainHand.getType().equals(Material.INK_SACK) ||
                !BlocksForFarmerE.checkPlant(block.getType()) ||
                block.getData() == CropState.RIPE.getData();
    }

    @EventHandler
    public final void playerUsedBoneMeal(@NotNull PlayerInteractEvent event) {

        Block block = event.getClickedBlock();
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (isaBoolean(event, block, mainHand)) return;

        mainHand.setAmount(mainHand.getAmount() + 1);

        if (block.getType().equals(Material.BEETROOT_BLOCK)) {

            block.setData((byte) 3);
            return;

        }

        block.setData(CropState.RIPE.getData());


    }

    @EventHandler
    public final void playerBreakBlock(@NotNull BlockBreakEvent event) {

        Block block = event.getBlock();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());

        if (block.getData() != CropState.RIPE.getData() ||
                feudalPlayer.getProfessionID() != ProfessionIDE.FARMER.getId())
            return;

        int exp = BlocksForFarmerE.getAttributeExpByMaterial(block.getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }

}
