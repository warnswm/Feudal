package feudal.listeners.interact;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;


public class MailMenuL implements Listener {

    private static boolean isaBoolean(@NotNull InventoryClickEvent event) {
        return event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Почта") &&
                        !event.getView().getTitle().equals("Взаимодействие с приглашением");
    }

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (isaBoolean(event)) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        String lore = event.getCurrentItem().getItemMeta().getLore() != null ? event.getCurrentItem().getItemMeta().getLore().get(0) : " lore";
        String displayName = event.getCurrentItem().getItemMeta().getDisplayName();


        if (lore.contains(" приглашает вас вступить в королевство - "))
            menuInteractionInvitation(player, lore.substring(lore.lastIndexOf(" ") + 1));

        if (displayName.contains("Вступить в королевство")) {

            String kingdomName = displayName.substring(displayName.lastIndexOf(" ") + 1);

            if (!feudalPlayer.getInvitations().contains(kingdomName)) {

                player.sendMessage("Вы не были приглашены этим королевством! Или время приглашения истекло.");
                return;

            }

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
            feudalKingdom.addMember(player);

            feudalPlayer.setKingdomName(kingdomName);
            feudalPlayer.clearInvitations();

            Bukkit.getPlayer(feudalKingdom.getKingUUID()).sendMessage("Игрок " + player.getDisplayName() + " принял ваше приглашение!");

            player.sendMessage("Вы вступили в королевство: " + kingdomName);
            player.closeInventory();

        }

    }

    private void menuInteractionInvitation(Player player, String kingdomName) {

        Inventory inventory = Bukkit.createInventory(player, 9, "Взаимодействие с приглашением");

        inventory.setItem(1, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Вступить в королевство " + kingdomName));
        inventory.setItem(7, CreateItemUtils.createItem(Material.BARRIER, 1, "Закрыть меню"));

        player.openInventory(inventory);

    }
}
