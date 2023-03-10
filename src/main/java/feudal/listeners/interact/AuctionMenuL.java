package feudal.listeners.interact;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AuctionMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Аукцион")) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (event.getView().getTitle().equals("Аукцион")) {

            ItemStack item = event.getCurrentItem();

            if (player.getInventory().contains(item)) return;

            confirmationMenu(player, event.getCurrentItem());

        } else if (event.getView().getTitle().equals("Подтверждение покупки")) {

//            feudalPlayer.takeBalance();
        }
    }

    private void confirmationMenu(Player player, ItemStack itemStack) {

        Inventory inventory = Bukkit.createInventory(player, 9, "Подтверждение покупки");

        inventory.setItem(1, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Подтвердить"));
        inventory.setItem(4, itemStack);
        inventory.setItem(7, CreateItemUtils.createItem(Material.CLAY_BALL, 1, "Закрыть"));

        player.openInventory(inventory);

    }
}
