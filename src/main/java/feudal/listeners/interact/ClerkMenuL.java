package feudal.listeners.interact;

import feudal.utils.CreateItemUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import static feudal.data.cache.CacheFeudalValues.getClerkTakeExp;

public class ClerkMenuL implements Listener {

    @EventHandler
    public final void playerInteractClerkMenu(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Выберите зачарование для снятия")) return;

        event.setCancelled(true);


        Player player = (Player) event.getView().getPlayer();

        for (Enchantment enchantment : event.getCurrentItem().getEnchantments().keySet()) {

            if (!player.getInventory().getItemInMainHand().getEnchantments().containsKey(enchantment)) return;

            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
            player.getInventory().addItem(CreateItemUtils.createItem(Material.ENCHANTED_BOOK, enchantment, event.getCurrentItem().getEnchantments().get(enchantment).byteValue(), 1));

            float exp = player.getExp() - ClerkTakeExpE.getExpByLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2;

            if (exp <= 0) {

                player.sendMessage("Недостаточно опыта!");
                player.closeInventory();

                return;

            }

            player.setExp((int) player.getExp() - ClerkTakeExpE.getExpByLvl(event.getCurrentItem().getEnchantments().get(enchantment).byteValue()) * 7 + 2);

            player.closeInventory();

        }

    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum ClerkTakeExpE {

    I(1, getClerkTakeExp().get(1)), II(2, getClerkTakeExp().get(2)), III(3, getClerkTakeExp().get(3)),
    IV(4, getClerkTakeExp().get(4)), V(5, getClerkTakeExp().get(5));

    int lvl;
    float exp;

    public static float getExpByLvl(int lvl) {

        for (ClerkTakeExpE clerkTakeExpE : values())
            if (clerkTakeExpE.getLvl() == lvl)
                return clerkTakeExpE.getExp();

        return 0;

    }
}

