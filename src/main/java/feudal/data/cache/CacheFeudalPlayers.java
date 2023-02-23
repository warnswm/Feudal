package feudal.data.cache;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class CacheFeudalPlayers {
    private final Map<UUID, FeudalPlayer> feudalPlayerCache = new HashMap<>();

    public Map<UUID, FeudalPlayer> getFeudalPlayerInfo() {
        return feudalPlayerCache;
    }

    public FeudalPlayer getFeudalPlayer(@NotNull Player player) {
        return feudalPlayerCache.get(player.getUniqueId());
    }

    public boolean hasProfession(Player player) {
        return CacheFeudalPlayers.getFeudalPlayer(player).getProfessionID() == 0;
    }

    public boolean hasKingdomPosition(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(feudalPlayer.getKingdomName());

        return feudalKingdom != null &&
                feudalKingdom.getKingUUID().equals(player.getUniqueId()) &&
                feudalKingdom.getBaronsUUID().contains(player.getUniqueId());

    }

    public boolean hasKing(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(feudalPlayer.getKingdomName());

        return feudalKingdom != null &&
                feudalKingdom.getKingUUID().equals(player.getUniqueId());

    }

    public boolean hasBaron(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
        FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(feudalPlayer.getKingdomName());

        return feudalKingdom != null &&
                feudalKingdom.getBaronsUUID().contains(player.getUniqueId());

    }

    public boolean exitsPlayer(String nick) {

        Player player = Bukkit.getPlayerExact(nick);
        return player != null && player.isOnline();

    }

}
