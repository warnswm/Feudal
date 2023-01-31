package feudal.listeners.gameClassesListeners;

import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.PlayerInfo;
import feudal.utils.CreateItemUtil;
import feudal.utils.enums.ClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static feudal.utils.MathUtils.getRandInt;
import static feudal.utils.enums.EnchantmentEnum.getRandomEnc;

public class FishermanListener implements Listener {

    @EventHandler
    public void playerFishing(@NotNull PlayerFishEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getPlayer());

        if (playerInfo.getAClassID() != ClassesIDEnum.FISHERMAN.getId() ||
                playerInfo.getGameClassLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        int item = getRandInt(1, 5), random = getRandInt(1, 3);

        if (random != 1 && random != 2) return;


        switch (item) {
            case 1:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.BOW)));
                break;
            case 2:
                event.getPlayer().getInventory().addItem(CreateItemUtil.createItem(Material.ENCHANTED_BOOK, getRandomEnc(), 1, 1));
                break;
            case 3:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.FISHING_ROD)));
                break;
            case 4:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.NAME_TAG)));
                break;
            case 5:
                event.getPlayer().getInventory().addItem((new ItemStack(Material.SADDLE)));
                break;
        }

    }
}