package feudal.utils;

import feudal.info.PlayerInfoCache;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerGameClass {
    static HashMap<Player, PlayerInfoCache> playerInfoHashMap = new HashMap<>();
    public static HashMap<Player, PlayerInfoCache> getPlayerInfo() {
        return playerInfoHashMap;
    }

}
