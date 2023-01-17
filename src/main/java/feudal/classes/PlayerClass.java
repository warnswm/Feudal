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

    public abstract void addLvl();
}
