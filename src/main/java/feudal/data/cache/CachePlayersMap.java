package feudal.data.cache;

import feudal.data.database.PlayerInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayersMap {
    static HashMap<Player, PlayerInfo> playerInfoHashMap = new HashMap<>();

    public static HashMap<Player, PlayerInfo> getPlayerInfo() {
        return playerInfoHashMap;
    }

}
