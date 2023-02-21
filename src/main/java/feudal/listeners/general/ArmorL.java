package feudal.listeners.general;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import static feudal.utils.enums.ArmorE.getArmorAttributeByItemMaterial;

public class ArmorL implements Listener {

    @EventHandler
    public void playerArmorChange(@NotNull PlayerArmorChangeEvent event) {

        if (event.getNewItem() == null) return;

        Player player = event.getPlayer();
        PlayerInventory playerInv = player.getInventory();

        int attribute = getArmorAttributeByItemMaterial(playerInv.getHelmet()) +
                getArmorAttributeByItemMaterial(playerInv.getChestplate()) +
                getArmorAttributeByItemMaterial(playerInv.getLeggings()) +
                getArmorAttributeByItemMaterial(playerInv.getBoots());


        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        float speed = attribute == 0 ? 0.2f * feudalPlayer.getSpeedLvl() / 130 + 0.2f : 0.2f * feudalPlayer.getSpeedLvl() / 130 + 0.2f - 0.2f / 100 * attribute;

        player.setWalkSpeed(speed);

    }
}
