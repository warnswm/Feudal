package feudal.gameClassesListeners.peasants;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CachePlayersMap;
import feudal.utils.enums.EnchantmentEnum;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import feudal.visual.menus.ClerkMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkListener implements Listener {

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && feudalPlayer.getAClassID() != GameClassesIDEnum.CLERK.getId()) {

            event.setCancelled(true);
            return;

        }

        int classLvl = feudalPlayer.getGameClassLvl();
        if (classLvl < 75) return;


        if (classLvl == 100)
            event.getItem().addUnsafeEnchantment(EnchantmentEnum.getRandomEnchantment(), 1);

        else
            event.setExpLevelCost((int) (event.getExpLevelCost() - event.getExpLevelCost() * 0.3));

    }

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        Player player = event.getPlayer();
        FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

        if (isaBoolean(event, player, feudalPlayer)) return;

        ClerkMenu.openClerkMenu(player);

    }

    private static boolean isaBoolean(@NotNull PlayerInteractEvent event, Player player, FeudalPlayer feudalPlayer) {
        return event.getClickedBlock() == null ||
                !event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) ||
                !player.isSneaking() ||
                !event.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                feudalPlayer.getAClassID() != GameClassesIDEnum.CLERK.getId();
    }
}
