package feudal.classes;

import feudal.info.PlayerInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class PlayerClass {
    static HashMap<Player, PlayerInfo> playerInfo = new HashMap<>();

    public static HashMap<Player, PlayerInfo> getPlayerInfo() {
        return playerInfo;
    }
    public static void putElement(Player key, PlayerInfo playerInfoObj) {

        playerInfo.put(key, playerInfoObj);

    }
    public static void removeElement(Player key) {

        playerInfo.remove(key);

    }
    public static PlayerInfo getElement(Player key) {

        return playerInfo.get(key);

    }
    public abstract void addLvl();
}
