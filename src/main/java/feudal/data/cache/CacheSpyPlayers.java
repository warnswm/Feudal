package feudal.data.cache;

import feudal.data.SpyPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class CacheSpyPlayers {
    static HashMap<UUID, SpyPlayer> spyPlayerCache = new HashMap<>();

    public static HashMap<UUID, SpyPlayer> getSpyPlayerCache() {
        return spyPlayerCache;
    }

    public static SpyPlayer getSpyPlayer(@NotNull Player player) {
        return spyPlayerCache.get(player.getUniqueId());
    }

}
