package feudal.data.cache;

import feudal.data.builder.FeudalPlayer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheFeudalPlayers {
    static HashMap<String, FeudalPlayer> feudalPlayerCache = new HashMap<>();

    public static HashMap<String, FeudalPlayer> getFeudalPlayerInfo() {
        return feudalPlayerCache;
    }

    public static FeudalPlayer getFeudalPlayer(@NotNull Player player) {
        return feudalPlayerCache.get(player.getUniqueId().toString());
    }

}
