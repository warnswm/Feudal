package feudal.data.cache;

import feudal.data.FeudalPlayer;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class CacheFeudalPlayers {
    private final Map<UUID, FeudalPlayer> feudalPlayerCache = new HashMap<>();

    public Map<UUID, FeudalPlayer> getFeudalPlayerInfo() {
        return feudalPlayerCache;
    }

    public FeudalPlayer getFeudalPlayer(@NotNull Player player) {
        return feudalPlayerCache.get(player.getUniqueId());
    }

}
