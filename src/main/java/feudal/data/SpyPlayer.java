package feudal.data;

import feudal.Feudal;
import feudal.data.cache.CacheSpyPlayers;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpyPlayer {

    final HashMap<UUID, SpyPlayer> spyPlayerCache = CacheSpyPlayers.getSpyPlayerCache();
    UUID spyPlayerUUID;
    GameMode spyPlayerGameMode;
    Location spyPlayerLocation;

    public SpyPlayer(UUID player, GameMode gameMode, Location location) {
        spyPlayerUUID = player;
        spyPlayerGameMode = gameMode;
        spyPlayerLocation = location;
    }

    public void hide() {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            if (!playerTab.getUniqueId().equals(spyPlayerUUID))
                playerTab.hidePlayer(Feudal.getPlugin(), Bukkit.getPlayer(spyPlayerUUID));

        });

        CacheSpyPlayers.getSpyPlayerCache().put(spyPlayerUUID, this);

    }

    public void load() {

        if (spyPlayerCache.isEmpty()) return;

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            for (Map.Entry<UUID, SpyPlayer> spyPlayer : spyPlayerCache.entrySet())
                if (!playerTab.getUniqueId().equals(spyPlayer.getKey()))
                    playerTab.hidePlayer(Feudal.getPlugin(), Bukkit.getPlayer(spyPlayer.getKey()));

        });
    }

    public void show() {

        Bukkit.getServer().getOnlinePlayers().forEach(playerTab -> {

            Player player = Bukkit.getPlayer(spyPlayerUUID);

            playerTab.showPlayer(Feudal.getPlugin(), player);

            player.setGameMode(spyPlayerGameMode);
            player.teleport(spyPlayerLocation);


            if (!playerTab.getGameMode().equals(GameMode.SPECTATOR))
                player.showPlayer(Feudal.getPlugin(), playerTab);

        });

        if (spyPlayerCache.containsKey(spyPlayerUUID))
            CacheSpyPlayers.getSpyPlayerCache().remove(spyPlayerUUID, this);

    }
}
