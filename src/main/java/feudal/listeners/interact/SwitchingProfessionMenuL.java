package feudal.listeners.interact;

import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.visual.ScoreBoardGeneralInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class SwitchingProfessionMenuL implements Listener {

    @EventHandler
    public final void interactInventory(@NotNull InventoryClickEvent event) {

        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getItemMeta() == null ||
                !event.getView().getTitle().equals("Смена профессии")) return;

        event.setCancelled(true);

        Player player = (Player) event.getView().getPlayer();
        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        feudalPlayer.setProfessionID(ProfessionIDE.getIDByString(event.getCurrentItem().getItemMeta().getDisplayName()));
        feudalPlayer.setProfessionLvl(0);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);
        player.closeInventory();

    }

}
