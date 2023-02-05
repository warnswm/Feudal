package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TabUtils {

    public static void hidePlayer(Player player) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getName().contains(player.getName()))
                playerTab.hidePlayer(Feudal.getPlugin(), player);

        });
    }
    public static void showPlayer(Player player) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            playerTab.showPlayer(Feudal.getPlugin(), player);

            if (!playerTab.getGameMode().equals(GameMode.SPECTATOR))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });
    }
}
