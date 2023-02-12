package feudal.visual.tab;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Tab {

    static List<Player> hidePlayers = new ArrayList<>();
    static Map<Player, GameMode> gameModes = new HashMap<>();
    static Map<Player, Location> locations = new HashMap<>();

    public static void hidePlayer(Player player) {

        if (!hidePlayers.contains(player) && !gameModes.containsKey(player) && !locations.containsKey(player)) {

            hidePlayers.add(player);
            gameModes.put(player, player.getGameMode());
            locations.put(player, player.getLocation());

        }

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getUniqueId().equals(player.getUniqueId()))
                playerTab.hidePlayer(Feudal.getPlugin(), player);

        });
    }

    public static void updateHidePlayers() {

        if (hidePlayers.isEmpty()) return;

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> hidePlayers.forEach(hidePlayer -> {

            if (!playerTab.getUniqueId().equals(hidePlayer.getUniqueId()))
                playerTab.hidePlayer(Feudal.getPlugin(), hidePlayer);

        }));
    }

    public static void showPlayer(Player player) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            playerTab.showPlayer(Feudal.getPlugin(), player);

            player.setGameMode(gameModes.get(player));
            player.teleport(locations.get(player));


            if (!playerTab.getGameMode().equals(GameMode.SPECTATOR))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });

        if (hidePlayers.contains(player) && gameModes.containsKey(player) && locations.containsKey(player)) {

            hidePlayers.remove(player);
            gameModes.remove(player);
            locations.remove(player);

        }
    }
}
