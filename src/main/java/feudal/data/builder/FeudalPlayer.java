package feudal.data.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeudalPlayer {
    Player player;
    int aClassID;
    int experience;
    long balance;
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

    public FeudalPlayer setBalance(long balance) {
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
        this.experience += value;
        return this;
    }

    public FeudalPlayer addBalance(long value) {
        this.balance += value;
        return this;
    }

    public FeudalPlayer addDeaths(int value) {
        this.deaths += value;
        return this;
    }

    public FeudalPlayer addKills(int value) {
        this.kills += value;
        return this;
    }

    public FeudalPlayer addStrengthLvl(int value) {
        this.strengthLvl += value;
        return this;
    }

    public FeudalPlayer addSurvivabilityLvl(int value) {
        this.survivabilityLvl += value;
        return this;
    }

    public FeudalPlayer addSpeedLvl(int value) {
        this.speedLvl += value;
        return this;
    }

    public FeudalPlayer addStaminaLvl(int value) {
        this.staminaLvl += value;
        return this;
    }

    public FeudalPlayer addLuckLvl(int value) {
        this.luckLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassLvl(int value) {
        this.gameClassLvl += value;
        return this;
    }

    public FeudalPlayer addGameClassExperience(int value) {
        this.gameClassExperience += value;
        return this;
    }


    public FeudalPlayer takeExperience(int value) {
        this.experience -= value;
        return this;
    }

    public FeudalPlayer takeBalance(long value) {
        this.balance -= value;
        return this;
    }

    public FeudalPlayer takeDeaths(int value) {
        this.deaths -= value;
        return this;
    }

    public FeudalPlayer takeKills(int value) {
        this.kills -= value;
        return this;
    }

    public FeudalPlayer takeStrengthLvl(int value) {
        this.strengthLvl -= value;
        return this;
    }

    public FeudalPlayer takeSurvivabilityLvl(int value) {
        this.survivabilityLvl -= value;
        return this;
    }

    public FeudalPlayer takeSpeedLvl(int value) {
        this.speedLvl -= value;
        return this;
    }

    public FeudalPlayer takeStaminaLvl(int value) {
        this.staminaLvl -= value;
        return this;
    }

    public FeudalPlayer takeLuckLvl(int value) {
        this.luckLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassLvl(int value) {
        this.gameClassLvl -= value;
        return this;
    }

    public FeudalPlayer takeGameClassExperience(int value) {
        this.gameClassExperience -= value;
        return this;
    }
}
