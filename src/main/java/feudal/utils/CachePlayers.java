package feudal.utils;

import feudal.info.CachePlayerInfoBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayers {
    static HashMap<Player, CachePlayerInfoBuilder> playerInfoHashMap = new HashMap<>();

    public static HashMap<Player, CachePlayerInfoBuilder> getPlayerInfo() {
        return playerInfoHashMap;
    }

}
