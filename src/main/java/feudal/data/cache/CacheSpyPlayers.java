package feudal.data.cache;

import feudal.data.SpyPlayer;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

@UtilityClass
public class CacheSpyPlayers {
    private final HashMap<UUID, SpyPlayer> spyPlayerCache = new HashMap<>();

    public HashMap<UUID, SpyPlayer> getSpyPlayerCache() {
        return spyPlayerCache;
    }

    public SpyPlayer getSpyPlayer(@NotNull Player player) {
        return spyPlayerCache.get(player.getUniqueId());
    }

}
