package feudal.data.cache;

import feudal.data.builder.FeudalPlayer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheFeudalPlayers {
    static HashMap<UUID, FeudalPlayer> feudalPlayerCache = new HashMap<>();

    public static HashMap<UUID, FeudalPlayer> getFeudalPlayerInfo() {
        return feudalPlayerCache;
    }

    public static FeudalPlayer getFeudalPlayer(@NotNull Player player) {
        return feudalPlayerCache.get(player.getUniqueId());
    }

}
