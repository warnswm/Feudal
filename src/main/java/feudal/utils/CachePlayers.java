package feudal.utils;

import feudal.info.CachePlayerInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayers {
    static HashMap<Player, CachePlayerInfo> playerInfoHashMap = new HashMap<>();

    public static HashMap<Player, CachePlayerInfo> getPlayerInfo() {
        return playerInfoHashMap;
    }

}
