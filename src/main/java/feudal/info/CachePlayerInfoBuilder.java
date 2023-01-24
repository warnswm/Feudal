package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayerInfoBuilder {
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
    int gameClassExperience;
    String kingdomName;


    public void addExperience(int value) {

        experience += value;

    }

    public void addGameClassExperience(int value) {

        gameClassExperience += value;

    }

    public void addBalance(int value) {

        balance += value;

    }

    public void addDeaths(int value) {

        deaths += value;

    }

    public void addKills(int value) {

        kills += value;

    }

    public void addStrengthLvl(int value) {

        strengthLvl += value;

    }

    public void addSurvivabilityLvl(int value) {

        survivabilityLvl += value;

    }

    public void addSpeedLvl(int value) {

        speedLvl += value;

    }

    public void addStaminaLvl(int value) {

        staminaLvl += value;

    }

    public void addLuckLvl(int value) {

        luckLvl += value;

    }

    public CachePlayerInfoBuilder setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public CachePlayerInfoBuilder setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public CachePlayerInfoBuilder setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public CachePlayerInfoBuilder setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public CachePlayerInfoBuilder setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public CachePlayerInfoBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public CachePlayerInfoBuilder setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public CachePlayerInfoBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public CachePlayerInfoBuilder setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public CachePlayerInfoBuilder setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public CachePlayerInfoBuilder setKills(int kills) {
        this.kills = kills;
        return this;
    }

    public CachePlayerInfoBuilder setGameClassExperience(int gameClassExperience) {
        this.gameClassExperience = gameClassExperience;
        return this;
    }

    public CachePlayerInfoBuilder setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }
}
