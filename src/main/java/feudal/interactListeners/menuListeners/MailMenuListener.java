package feudal.interactListeners.menuListeners;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
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

import java.util.UUID;


public class MailMenuListener implements Listener {

    @EventHandler
    public void interactInventory(@NotNull InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Почта") &&
                !event.getView().getTitle().equals("Взаимодействие с приглашением")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();

        if (event.getCurrentItem().getItemMeta() == null) return;


        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        String lore = event.getCurrentItem().getItemMeta().getLore().get(0) != null ? event.getCurrentItem().getItemMeta().getLore().get(0) : " lore";
        String lastWord = lore.substring(lore.lastIndexOf(" ") + 1);


        if (lore.contains(" приглашает вас вступить в королевство - ")) {

            lastWord = lore.substring(lore.lastIndexOf(" ") + 1);
            menuInteractionInvitation(player, lastWord);

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Вступить в королевство " + lastWord)) {

            if (!feudalPlayer.getInvitations().contains(lastWord)) {

                player.sendMessage("Вы не были приглашены этим королевством! Или время приглашения истекло.");
                return;

            }

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(lastWord);
            feudalKingdom.addMember(player);

            feudalPlayer.setKingdomName(lastWord);
            feudalPlayer.clearInvitations();

            Bukkit.getPlayer(UUID.fromString(feudalKingdom.getKingUUID())).sendMessage("Игрок " +
                    player.getDisplayName() +
                    " принял ваше приглашение!");
            player.sendMessage("Вы вступили в королевство: " +
                    lastWord);

            player.closeInventory();

        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Закрыть"))
            player.closeInventory();

    }

    private void menuInteractionInvitation(Player player, String kingdomName) {

        Inventory inventory = Bukkit.createInventory(player, 9, "Взаимодействие с приглашением");

        inventory.setItem(1, CreateItemUtils.createItem(Material.GREEN_SHULKER_BOX, 1, "Вступить в королевство " + kingdomName));
        inventory.setItem(7, CreateItemUtils.createItem(Material.BARRIER, 1, "Закрыть меню"));

        player.openInventory(inventory);

    }
}
