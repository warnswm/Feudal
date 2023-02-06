package feudal.data.cache;

import feudal.data.builder.FeudalPlayer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayersMap {
    static HashMap<Player, FeudalPlayer> playerCache = new HashMap<>();

    public static HashMap<Player, FeudalPlayer> getPlayerInfo() {
        return playerCache;
    }

}
