package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TabUtils {

    public static void hidePlayer(Player player) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getName().contains(player.getName()))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });
    }

    public void hidePlayers(Player player, Player[] hidePlayers) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> Arrays.asList(hidePlayers).forEach(hidePlayer -> {

            if (!playerTab.getName().contains(hidePlayer.getName()))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        }));
    }
}
