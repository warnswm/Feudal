package feudal.data.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalPlayer {
    final List<String> invitations = new ArrayList<>();
    Player player;
    int aClassID;
    int experience;
    int balance;
    int deaths;
    int kills;
    int strengthLvl;
    int survivabilityLvl;
    int speedLvl;
    int staminaLvl;
    int luckLvl;
    int gameClassLvl;
    int gameClassExperience;
    String kingdomName;

    public FeudalPlayer(Player player) {
        this.player = player;
    }


    public FeudalPlayer setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public FeudalPlayer setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public FeudalPlayer setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public FeudalPlayer setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public FeudalPlayer setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public FeudalPlayer setKills(int kills) {
        this.kills = kills;
        return this;
    }

    public FeudalPlayer setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public FeudalPlayer setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public FeudalPlayer setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public FeudalPlayer setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public FeudalPlayer setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public FeudalPlayer setGameClassLvl(int gameClassLvl) {
        this.gameClassLvl = gameClassLvl;
        return this;
    }

    public FeudalPlayer setGameClassExperience(int gameClassExperience) {
        this.gameClassExperience = gameClassExperience;
        return this;
    }

    public FeudalPlayer setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }


    public FeudalPlayer addExperience(int value) {
        experience += value;
        return this;
    }

    public FeudalPlayer addBalance(int value) {
        balance += value;
        return this;
    }

    public FeudalPlayer addDeaths(int value) {
        deaths += value;
        return this;
    }

    public FeudalPlayer addKills(int value) {
        kills += value;
        return this;
    }

    public FeudalPlayer addStrengthLvl(int value) {
        strengthLvl += value;
        return this;
    }

    public FeudalPlayer addSurvivabilityLvl(int value) {
        survivabilityLvl += value;
        return this;
    }

    public FeudalPlayer addSpeedLvl(int value) {
        speedLvl += value;
        return this;
    }

    public FeudalPlayer addStaminaLvl(int value) {
        staminaLvl += value;
        return this;
    }

    public FeudalPlayer addLuckLvl(int value) {
        luckLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassLvl(int value) {
        gameClassLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassExperience(int value) {
        gameClassExperience += value;
        return this;
    }


    public FeudalPlayer takeExperience(int value) {
        experience -= value;
        return this;
    }

    public FeudalPlayer takeBalance(int value) {
        balance -= value;
        return this;
    }

    public FeudalPlayer takeDeaths(int value) {
        deaths -= value;
        return this;
    }

    public FeudalPlayer takeKills(int value) {
        kills -= value;
        return this;
    }

    public FeudalPlayer takeStrengthLvl(int value) {
        strengthLvl -= value;
        return this;
    }

    public FeudalPlayer takeSurvivabilityLvl(int value) {
        survivabilityLvl -= value;
        return this;
    }

    public FeudalPlayer takeSpeedLvl(int value) {
        speedLvl -= value;
        return this;
    }

    public FeudalPlayer takeStaminaLvl(int value) {
        staminaLvl -= value;
        return this;
    }

    public FeudalPlayer takeLuckLvl(int value) {
        luckLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassLvl(int value) {
        gameClassLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassExperience(int value) {
        gameClassExperience -= value;
        return this;
    }

    public FeudalPlayer addInvitations(String kingdomName) {
        invitations.add(kingdomName);
        return this;
    }

    public FeudalPlayer clearInvitations() {
        invitations.clear();
        return this;
    }

    public FeudalPlayer deleteInvitations(String kingdomName) {
        invitations.remove(kingdomName);
        return this;
    }

}
