package feudal.listeners.interactListeners.menuListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.PlayerInfo;
import feudal.utils.CreateItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AuctionMenuInteractListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Аукцион") &&
                !event.getView().getTitle().equals("Подтверждение")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        PlayerInfo playerInfo = CachePlayersMap.getPlayerInfo().get(player);

        if (event.getView().getTitle().equals("Аукцион")) {

            confirmationMenu(player, event.getCurrentItem());

        } else if (event.getView().getTitle().equals("Подтверждение")) {

//            playerInfo.takeBalance();
        }
    }

    private void confirmationMenu(Player player, ItemStack itemStack) {

        Inventory inventory = Bukkit.createInventory(player, 9, "Подтверждение");

        inventory.setItem(1, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Подтвердить"));
        inventory.setItem(4, itemStack);
        inventory.setItem(7, CreateItemUtil.createItem(Material.CLAY_BALL, 1, "Закрыть"));

        player.openInventory(inventory);

    }
}
