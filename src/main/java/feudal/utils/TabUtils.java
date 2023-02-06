package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TabUtils {

    static List<Player> hidePlayers = new ArrayList<>();

    public static void hidePlayer(Player player) {

        if (!hidePlayers.contains(player))
            hidePlayers.add(player);

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getName().contains(player.getName()))
                playerTab.hidePlayer(Feudal.getPlugin(), player);

        });
    }

    public static void updateHidePlayers() {

        if (hidePlayers.isEmpty()) return;

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            hidePlayers.forEach(hidePlayer -> {

                if (!playerTab.getName().contains(hidePlayer.getName()))
                    playerTab.hidePlayer(Feudal.getPlugin(), hidePlayer);

            });

        });
    }

    public static void showPlayer(Player player) {

        if (hidePlayers.contains(player))
            hidePlayers.remove(player);

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            playerTab.showPlayer(Feudal.getPlugin(), player);

            if (!playerTab.getGameMode().equals(GameMode.SPECTATOR))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });
    }
}
