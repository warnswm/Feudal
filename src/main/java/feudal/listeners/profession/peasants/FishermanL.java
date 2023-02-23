package feudal.listeners.profession.peasants;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.EnchantmentE;
import feudal.utils.enums.professionEnums.FishermanLootTableE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class FishermanL implements Listener {

    private static boolean isaBoolean(@NotNull PlayerFishEvent event, @NotNull FeudalPlayer feudalPlayer, int random) {
        return feudalPlayer.getProfessionID() != ProfessionIDE.FISHERMAN.getId() ||
                feudalPlayer.getProfessionLvl() < 25 ||
                event.getState() != PlayerFishEvent.State.CAUGHT_FISH ||
                random != 1 && random != 2;
    }

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getPlayer());
        int item = ThreadLocalRandom.current().nextInt(1, 6), random = ThreadLocalRandom.current().nextInt(1, 8);

        if (isaBoolean(event, feudalPlayer, random)) return;

        if (item == 2) {

            event.getPlayer().getInventory().addItem(CreateItemUtils.createItem(Material.ENCHANTED_BOOK, EnchantmentE.getRandomEnchantment(), 1, 1));
            return;

        }

        event.getPlayer().getInventory().addItem(FishermanLootTableE.getItemByID(item));

    }
}
