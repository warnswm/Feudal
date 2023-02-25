package feudal.listeners.profession;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.EnchantmentE;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.visual.Menus;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class ClerkL implements Listener {

    private static boolean isaBoolean(@NotNull PlayerInteractEvent event, Player player, FeudalPlayer feudalPlayer) {
        return event.getClickedBlock() == null ||
                !event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) ||
                !player.isSneaking() ||
                !event.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                feudalPlayer.getProfessionID() != ProfessionIDE.CLERK.getId();
    }

    @EventHandler
    public final void playerItemEnchant(@NotNull EnchantItemEvent event) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(event.getEnchanter());

        if (event.getItem().getType().equals(Material.BOOK) && feudalPlayer.getProfessionID() != ProfessionIDE.CLERK.getId()) {

            event.setCancelled(true);
            return;

        }

        int professionLvl = feudalPlayer.getProfessionLvl();
        if (professionLvl < 75) return;


        if (professionLvl == 100)
            event.getItem().addUnsafeEnchantment(EnchantmentE.getRandomEnchantment(), 1);

        else
            event.setExpLevelCost((int) (event.getExpLevelCost() - event.getExpLevelCost() * 0.3));


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

    @EventHandler
    public final void playerInteract(@NotNull PlayerInteractEvent event) {

        Player player = event.getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        if (isaBoolean(event, player, feudalPlayer)) return;

        new Menus(player).clerkMenu();

    }
}
