package feudal.info;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheKingdomInfo {
    String kingdomName;
    Player king;
    Player[] members;
    Chunk[] territory;
    Player[] barons;
}
