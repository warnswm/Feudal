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
    List<String> baronsUUID = new ArrayList<>();
    List<String> membersUUID = new ArrayList<>();
    String kingdomName;
    UUID kingUUID;
    int maxNumberMembers, balance, reputation;
    List<Integer> territory = new ArrayList<>(), privateTerritory = new ArrayList<>();


    public FeudalKingdom(String kingdomName) {
        this.kingdomName = kingdomName;
    }


    public final FeudalKingdom setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public final FeudalKingdom setKing(@NotNull UUID player) {
        kingUUID = player;
        return this;
    }

    public final FeudalKingdom setMembers(List<String> membersUUID) {
        this.membersUUID = membersUUID;
        return this;
    }

    public final FeudalKingdom setTerritory(@NotNull List<Integer> territory) {
        this.territory = territory;
        return this;
    }

    public final FeudalKingdom setPrivateTerritory(@NotNull List<Integer> privateTerritory) {
        this.privateTerritory = privateTerritory;
        return this;
    }

    public final FeudalKingdom setBarons(List<String> barons) {
        baronsUUID = barons;
        return this;
    }

    public final FeudalKingdom setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public final FeudalKingdom setReputation(int reputation) {
        this.reputation = reputation;
        return this;
    }


    public final FeudalKingdom addMember(@NotNull Player player) {
        membersUUID.add(player.getUniqueId().toString());
        return this;
    }

    public final FeudalKingdom addTerritory(@NotNull Chunk chunk) {
        territory.add(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode());
        return this;
    }

    public final FeudalKingdom addPrivateTerritory(@NotNull Chunk chunk) {
        privateTerritory.add(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode());
        return this;
    }

    public final FeudalKingdom addBaron(@NotNull Player player) {
        baronsUUID.add(player.getUniqueId().toString());
        return this;
    }

    public final FeudalKingdom addBalance(int value) {
        balance += value;
        return this;
    }

    public final FeudalKingdom addReputation(int value) {
        reputation += value;
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

    public final FeudalKingdom takePrivateTerritory(int chunkHashCode) {
        privateTerritory.remove(chunkHashCode);
        return this;
    }

    public final FeudalKingdom takeAllTerritory() {
        territory.clear();
        return this;
    }

    public final FeudalKingdom takeAllPrivateTerritory() {
        privateTerritory.clear();
        return this;
    }

    public final FeudalKingdom removeBaron(@NotNull Player player) {
        baronsUUID.remove(player.getUniqueId().toString());
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

    public final FeudalKingdom clearInvitation() {
        invitationUUID.clear();
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

    public final FeudalKingdom addMaxNumberMembers(int value) {
        maxNumberMembers += value;
        return this;
    }


    public boolean chunkInKingdomCache(int chunkHashCode) {
        return territory.contains(chunkHashCode);
    }

}
