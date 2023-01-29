package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TabUtils {

    public static void hidePlayer(Player player) {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getName().contains(player.getName()))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });
    }
}
