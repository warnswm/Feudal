package feudal.listeners.gameClassesListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.CreateItemUtil;
import feudal.utils.MathUtils;
import feudal.utils.enums.EnchantmentEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FishermanListener implements Listener {

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(event.getPlayer());

        if (feudalPlayer.getAClassID() != GameClassesIDEnum.FISHERMAN.getId() ||
                feudalPlayer.getGameClassLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        int item = MathUtils.getRandomInt(1, 6), random = MathUtils.getRandomInt(1, 4);

        if (random != 1 && random != 2) return;

        Inventory inventory = event.getPlayer().getInventory();


        switch (item) {
            case 1:
                inventory.addItem((new ItemStack(Material.BOW)));
                break;
            case 2:
                inventory.addItem(CreateItemUtil.createItem(Material.ENCHANTED_BOOK, EnchantmentEnum.getRandomEnchantment(), 1, 1));
                break;
            case 3:
                inventory.addItem((new ItemStack(Material.FISHING_ROD)));
                break;
            case 4:
                inventory.addItem((new ItemStack(Material.NAME_TAG)));
                break;
            case 5:
                inventory.addItem((new ItemStack(Material.SADDLE)));
                break;
        }

    }
}
