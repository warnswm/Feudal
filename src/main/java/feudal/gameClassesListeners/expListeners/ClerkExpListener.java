package feudal.gameClassesListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkExpListener implements Listener {

    @EventHandler
    public void playerItemEnchant(@NotNull EnchantItemEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && feudalPlayer.getAClassID() != GameClassesIDEnum.CLERK.getId()) {

            event.setCancelled(true);
            return;

        }

        int expLevelCost = event.getExpLevelCost();

        if (expLevelCost > 20) {

            feudalPlayer.addExperience(17);
            feudalPlayer.addGameClassExperience(68);

        } else if (expLevelCost > 10) {

            feudalPlayer.addExperience(10);
            feudalPlayer.addGameClassExperience(40);

        } else if (expLevelCost > 2) {

            feudalPlayer.addExperience(5);
            feudalPlayer.addGameClassExperience(20);

        }
    }
}
