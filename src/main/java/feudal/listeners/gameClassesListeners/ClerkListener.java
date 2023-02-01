package feudal.listeners.gameClassesListeners;

import feudal.data.cache.CachePlayers;
import feudal.data.database.PlayerInfo;
import feudal.utils.enums.ClassesIDEnum;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

import static feudal.utils.enums.EnchantmentEnum.getRandomEnc;

public class ClerkListener implements Listener {

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        PlayerInfo playerInfo = CachePlayers.getPlayerInfo().get(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && playerInfo.getAClassID() != ClassesIDEnum.CLERK.getId()) {

            event.setCancelled(true);
            return;

        }

        val classLvl = playerInfo.getGameClassLvl();

        if (classLvl < 75) return;

        event.setExpLevelCost((int) (event.getExpLevelCost() - event.getExpLevelCost() * 0.3));

        if (classLvl != 100) return;

        event.getItem().addUnsafeEnchantment(getRandomEnc(), 1);
    }
//    @EventHandler
//    public void playerInteract(@NotNull PlayerInteractEvent event) {
//
//        Player player = event.getPlayer();
//
//        if (event.getClickedBlock() == null ||
//                !event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) ||
//                !player.isSneaking() ||
//                !event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
//                !event.getMaterial().equals(Material.ENCHANTED_BOOK)) return;
//
//        for (Enchantment e : player.getInventory().getItemInMainHand().getEnchantments().keySet())
//            player.getInventory().getItemInMainHand().removeEnchantment(e);
//
//    }
}
