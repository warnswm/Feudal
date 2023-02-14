package feudal.data.builder;

import feudal.utils.wrappers.ChunkWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalKingdom {

    String kingdomName;
    final List<String> invitationUUID = new ArrayList<>();
    String kingUUID;
    int maxNumberMembers;
    List<Integer> territory = new ArrayList<>();
    List<Integer> privateTerritory = new ArrayList<>();
    List<String> membersUUID = new ArrayList<>();
    int balance;
    int reputation;
    List<String> baronsUUID = new ArrayList<>();
    final List<String> kingdomLetters = new ArrayList<>();

    public FeudalKingdom(String kingdomName) {
        this.kingdomName = kingdomName;
    }


    public FeudalKingdom setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public FeudalKingdom setKing(@NotNull Player player) {
        kingUUID = player.getUniqueId().toString();
        return this;
    }

    public FeudalKingdom setMembers(List<String> membersUUID) {
        this.membersUUID = membersUUID;
        return this;
    }

    public FeudalKingdom setTerritory(@NotNull List<Integer> territory) {
        this.territory = territory;
        return this;
    }

    public FeudalKingdom setPrivateTerritory(@NotNull List<Integer> privateTerritory) {
        this.privateTerritory = privateTerritory;
        return this;
    }

    public FeudalKingdom setBarons(List<String> barons) {
        baronsUUID = barons;
        return this;
    }

    public FeudalKingdom setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public FeudalKingdom setReputation(int reputation) {
        this.reputation = reputation;
        return this;
    }


    public FeudalKingdom addMember(@NotNull Player player) {
        membersUUID.add(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom addTerritory(@NotNull Chunk chunk) {
        territory.add(ChunkWrapper.chunkToChunkWrapper(chunk).hashCode());
        return this;
    }

    public FeudalKingdom addPrivateTerritory(@NotNull Chunk chunk) {
        privateTerritory.add(ChunkWrapper.chunkToChunkWrapper(chunk).hashCode());
        return this;
    }

    public FeudalKingdom addBaron(@NotNull Player player) {
        baronsUUID.add(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom addBalance(int value) {
        balance += value;
        return this;
    }

    public FeudalKingdom addReputation(int value) {
        reputation += value;
        return this;
    }


    public FeudalKingdom takeMember(@NotNull Player player) {
        membersUUID.remove(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom takeTerritory(int chunkHashCode) {
        territory.remove(chunkHashCode);
        return this;
    }

    public FeudalKingdom takePrivateTerritory(int chunkHashCode) {
        privateTerritory.remove(chunkHashCode);
        return this;
    }

    public FeudalKingdom takeAllTerritory() {
        territory.clear();
        return this;
    }

    public FeudalKingdom takeAllPrivateTerritory() {
        privateTerritory.clear();
        return this;
    }

    public FeudalKingdom takeBaron(@NotNull Player player) {
        baronsUUID.remove(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom takeBalance(int value) {
        balance -= value;
        return this;
    }

    public FeudalKingdom takeReputation(int value) {
        reputation -= value;
        return this;
    }

    public FeudalKingdom addInvitation(@NotNull Player player) {
        invitationUUID.add(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom clearInvitation() {
        invitationUUID.clear();
        return this;
    }

    public FeudalKingdom deleteInvitation(@NotNull Player player) {
        invitationUUID.remove(player.getUniqueId().toString());
        return this;
    }

    public FeudalKingdom setMaxNumberMembers(int value) {
        maxNumberMembers = value;
        return this;
    }

    public FeudalKingdom addMaxNumberMembers(int value) {
        maxNumberMembers += value;
        return this;
    }


    public boolean chunkInKingdomCache(int chunkHashCode) {
        return territory.contains(chunkHashCode);
    }

}
