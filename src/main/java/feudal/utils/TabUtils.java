package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TabUtils {
    Player player;
    Player[] hidePlayers;

    public TabUtils(Player player) {
        this.player = player;
    }

    public TabUtils(Player[] hidePlayers) {
        this.hidePlayers = hidePlayers;
    }

    public void hidePlayer() {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getName().contains(player.getName()))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });
    }
    public void hidePlayers() {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> Arrays.asList(hidePlayers).forEach(hidePlayer -> {

            if (!playerTab.getName().contains(hidePlayer.getName()))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        }));
    }
}
