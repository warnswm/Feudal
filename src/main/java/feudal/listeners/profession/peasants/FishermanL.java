package feudal.listeners.profession.peasants;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import feudal.utils.enums.EnchantmentE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
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

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum FishermanLootTableE {

    BOW(1, new ItemStack(Material.BOW)),
    FISHING_ROD(3, new ItemStack(Material.FISHING_ROD)),
    NAME_TAG(4, new ItemStack(Material.NAME_TAG)),
    SADDLE(5, new ItemStack(Material.SADDLE));

    int id;
    ItemStack item;

    public static ItemStack getItemByID(int id) {

        for (FishermanLootTableE fishermanLootTableE : values())
            if (fishermanLootTableE.getId() == id)
                return fishermanLootTableE.getItem();

        return new ItemStack(Material.STICK);

    }
}
