package feudal.data.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalKingdom {

    String kingdomName;
    Player king;
    List<Player> members;
    List<Chunk> territory;
    List<Chunk> privateTerritory;
    List<Player> barons;
    long balance;
    int reputation;

    public FeudalKingdom(String kingdomName) {
        this.kingdomName = kingdomName;
    }


    public FeudalKingdom setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }

    public FeudalKingdom setKing(Player king) {
        this.king = king;
        return this;
    }

    public FeudalKingdom setMembers(List<Player> members) {
        this.members = members;
        return this;
    }

    public FeudalKingdom setTerritory(List<Chunk> territory) {
        this.territory = territory;
        return this;
    }

    public FeudalKingdom setPrivateTerritory(List<Chunk> privateTerritory) {
        this.privateTerritory = privateTerritory;
        return this;
    }

    public FeudalKingdom setBarons(List<Player> barons) {
        this.barons = barons;
        return this;
    }

    public FeudalKingdom setBalance(long balance) {
        this.balance = balance;
        return this;
    }

    public FeudalKingdom setReputation(int reputation) {
        this.reputation = reputation;
        return this;
    }


    public FeudalKingdom addMember(@NotNull Player player) {
        members.add(player);
        return this;
    }

    public FeudalKingdom addTerritory(@NotNull Chunk chunk) {
        territory.add(chunk);
        return this;
    }

    public FeudalKingdom addPrivateTerritory(@NotNull Chunk chunk) {
        privateTerritory.add(chunk);
        return this;
    }

    public FeudalKingdom addBaron(@NotNull Player player) {
        barons.add(player);
        return this;
    }

    public FeudalKingdom addBalance(long value) {
        balance += value;
        return this;
    }

    public FeudalKingdom addReputation(int value) {
        reputation += value;
        return this;
    }


    public FeudalKingdom takeMember(@NotNull Player player) {
        members.remove(player);
        return this;
    }

    public FeudalKingdom takeTerritory(@NotNull Chunk chunk) {
        territory.remove(chunk);
        return this;
    }

    public FeudalKingdom takePrivateTerritory(@NotNull Chunk chunk) {
        privateTerritory.remove(chunk);
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
        barons.remove(player);
        return this;
    }

    public FeudalKingdom takeBalance(long value) {
        balance -= value;
        return this;
    }

    public FeudalKingdom takeReputation(int value) {
        reputation -= value;
        return this;
    }


    public boolean chunkInKingdomCache(@NotNull String chunk) {
        return territory.contains(chunk);
    }

}
