package feudal.listeners.general;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import static feudal.listeners.general.ArmorE.getArmorAttributeByItemMaterial;

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

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum ArmorE {

    LEATHER_HELMET(Material.LEATHER_HELMET, -8), CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, -3), IRON_HELMET(Material.IRON_HELMET, -3),
    GOLD_HELMET(Material.GOLD_HELMET, -3), CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, 8), IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 13),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 23), GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 8), LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, -2),
    CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, 3), IRON_LEGGINGS(Material.IRON_LEGGINGS, 8), DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 13),
    LEATHER_BOOTS(Material.LEATHER_BOOTS, -8), CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, -8), IRON_BOOTS(Material.IRON_BOOTS, -3),
    GOLD_BOOTS(Material.GOLD_BOOTS, -8);

    Material armor;
    int attribute;

    public static int getArmorAttributeByItemMaterial(ItemStack item) {

        if (item == null) return 0;

        for (ArmorE armorE : values())
            if (armorE.getArmor() == item.getType())
                return armorE.getAttribute();

        return 0;

    }
}
