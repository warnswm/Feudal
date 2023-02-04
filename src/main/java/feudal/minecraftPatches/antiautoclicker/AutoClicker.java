package feudal.minecraftPatches.antiautoclicker;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AutoClicker implements Listener {

    final Map<Player, List<Long>> playerClicks = new HashMap<>();

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        Player player = event.getPlayer();
        List<Long> getPlayerClicks = playerClicks.get(player);

        if (playerClicks.containsKey(player)) {

            getPlayerClicks.add(System.currentTimeMillis());

            if (checkList(getPlayerClicks)) {

                player.sendMessage("Ваши удары не засчитываются! Выключите автокликер!");
                event.setCancelled(true);

                return;

            }

            if (playerClicks.get(player).size() >= 5)
                playerClicks.remove(player);

            return;

        }

        List<Long> currentTime = new ArrayList<>();
        currentTime.add(System.currentTimeMillis());

        playerClicks.put(player, currentTime);

        if (checkList(playerClicks.get(player))) {

            player.sendMessage("Ваши удары не засчитываются! Выключите автокликер!");
            event.setCancelled(true);

            return;

        }

        if (playerClicks.get(player).size() >= 5)
            playerClicks.remove(player);

    }

    private boolean checkList(@NotNull List<Long> playerClicks) {

        if (playerClicks.size() < 7) return false;

        return playerClicks.get(playerClicks.size() - 1) - playerClicks.get(0) <= 200;

    }

}
