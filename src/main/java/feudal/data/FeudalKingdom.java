package feudal.data;

import feudal.utils.wrappers.ChunkWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalKingdom {
    final List<UUID> invitationUUID = new ArrayList<>();
    final List<String> kingdomLetters = new ArrayList<>();
    List<UUID> baronsUUID = new ArrayList<>();
    List<UUID> membersUUID = new ArrayList<>();
    String kingdomName, flagGson;
    UUID kingUUID;
    int maxNumberMembers, balance, reputation;
    List<Integer> territory = new ArrayList<>();

    public FeudalKingdom(String name) {
        kingdomName = name;
    }


    public final FeudalKingdom setKingdomName(String name) {
        kingdomName = name;
        return this;
    }

    public final FeudalKingdom setKing(@NotNull UUID player) {
        kingUUID = player;
        return this;
    }

    public final FeudalKingdom setMembers(List<UUID> members) {
        membersUUID = members;
        return this;
    }

    public final FeudalKingdom setTerritory(@NotNull List<Integer> territoryHashCode) {
        territory = territoryHashCode;
        return this;
    }

    public final FeudalKingdom setBarons(List<UUID> barons) {
        baronsUUID = barons;
        return this;
    }

    public final FeudalKingdom setBalance(int value) {
        balance = value;
        return this;
    }

    public final FeudalKingdom setReputation(int value) {
        reputation = value;
        return this;
    }


    public final FeudalKingdom addMember(@NotNull Player player) {
        membersUUID.add(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom addTerritory(@NotNull Chunk chunk) {
        territory.add(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode());
        return this;
    }

    public final FeudalKingdom addBaron(@NotNull Player player) {
        baronsUUID.add(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom addBalance(int value) {
        balance += value;
        return this;
    }

    public final FeudalKingdom removeMember(@NotNull Player player) {
        membersUUID.remove(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom takeTerritory(int chunkHashCode) {
        territory.remove(chunkHashCode);
        return this;
    }

    public final FeudalKingdom takeAllTerritory() {
        territory.clear();
        return this;
    }

    public final FeudalKingdom removeBaron(@NotNull Player player) {
        baronsUUID.remove(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom takeBalance(int value) {
        balance -= value;
        return this;
    }

    public final FeudalKingdom takeReputation(int value) {
        reputation -= value;
        return this;
    }

    public final FeudalKingdom addInvitation(@NotNull Player player) {
        invitationUUID.add(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom deleteInvitation(@NotNull Player player) {
        invitationUUID.remove(player.getUniqueId());
        return this;
    }

    public final FeudalKingdom setMaxNumberMembers(int value) {
        maxNumberMembers = value;
        return this;
    }

    public final FeudalKingdom setFlagGson(String value) {
        flagGson = value;
        return this;
    }


    public boolean chunkInKingdomCache(int chunkHashCode) {
        return territory.contains(chunkHashCode);
    }

}
