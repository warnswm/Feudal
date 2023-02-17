package feudal.professionListeners.expListeners;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkExpL implements Listener {

    @EventHandler
    public final void playerItemEnchant(@NotNull EnchantItemEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && feudalPlayer.getProfessionID() != ProfessionIDE.CLERK.getId()) {

            event.setCancelled(true);
            return;

        }

        int expLevelCost = event.getExpLevelCost();

        if (expLevelCost > 20) {

            feudalPlayer.addExperience(17);
            feudalPlayer.addProfessionExperience(68);

        } else if (expLevelCost > 10) {

            feudalPlayer.addExperience(10);
            feudalPlayer.addProfessionExperience(40);

        } else if (expLevelCost > 2) {

            feudalPlayer.addExperience(5);
            feudalPlayer.addProfessionExperience(20);

        }

    }

}
