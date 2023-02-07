package feudal.generalListeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import static feudal.utils.enums.ArmorEnum.getByItemMaterial;

public class ArmorListener implements Listener {

    @EventHandler
    public void playerArmorChange(@NotNull PlayerArmorChangeEvent event) {

        if (event.getNewItem() == null) return;

        Player player = event.getPlayer();
        PlayerInventory playerInv = player.getInventory();

        int attribute = playerInv.getHelmet() == null ? 0 : getByItemMaterial(playerInv.getHelmet().getType());
        attribute += playerInv.getChestplate() == null ? 0 : getByItemMaterial(playerInv.getChestplate().getType());
        attribute += playerInv.getLeggings() == null ? 0 : getByItemMaterial(playerInv.getLeggings().getType());
        attribute += playerInv.getBoots() == null ? 0 : getByItemMaterial(playerInv.getBoots().getType());

        player.setWalkSpeed(0.2f - (0.2f / 100 * attribute));

    }
}
