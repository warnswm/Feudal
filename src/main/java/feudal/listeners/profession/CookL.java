package feudal.listeners.profession;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.jetbrains.annotations.NotNull;

public class CookL implements Listener {

    @EventHandler
    public final void playerExtractFurnace(@NotNull FurnaceExtractEvent event) {

        if (CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()).getProfessionID()
                != ProfessionIDE.COOK.getId()) return;

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getPlayer().getItemOnCursor());

        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setBoolean("cookedByChef", true);
        tag.setByte("chefLvl", (byte) CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()).getProfessionLvl());

        nmsItem.setTag(tag);
        event.getPlayer().setItemOnCursor(CraftItemStack.asBukkitCopy(nmsItem));


        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        int exp = FoodForCook.getAttributeExpByFood(event.getBlock().getType());

        feudalPlayer.addExperience(exp);
        feudalPlayer.addProfessionExperience(4 * exp);

    }

}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum FoodForCook {
    COOKED_BEEF(Material.COOKED_BEEF, 15), GRILLED_PORK(Material.GRILLED_PORK, 15), COOKED_MUTTON(Material.COOKED_MUTTON, 10),
    COOKED_CHICKEN(Material.COOKED_CHICKEN, 8), COOKED_FISH(Material.COOKED_FISH, 10), BAKED_POTATO(Material.BAKED_POTATO, 5),
    COOKED_RABBIT(Material.COOKED_RABBIT, 5);

    Material material;
    int attributeExp;

    public static int getAttributeExpByFood(Material material) {

        if (material == null) return 0;

        for (FoodForCook foodForCook : values())
            if (foodForCook.getMaterial() == material)
                return foodForCook.getAttributeExp();

        return 0;

    }

}

