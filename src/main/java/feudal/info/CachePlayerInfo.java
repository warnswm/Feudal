package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachePlayerInfo {
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

    public CachePlayerInfo setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public CachePlayerInfo setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public CachePlayerInfo setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public CachePlayerInfo setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public CachePlayerInfo setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public CachePlayerInfo setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public CachePlayerInfo setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public CachePlayerInfo setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public CachePlayerInfo setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public CachePlayerInfo setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public CachePlayerInfo setKills(int kills) {
        this.kills = kills;
        return this;
    }
    public CachePlayerInfo setGameClassExperience(int gameClassExperience) {
        this.gameClassExperience = gameClassExperience;
        return this;
    }
    public CachePlayerInfo setKingdomName(String  kingdomName) {
        this.kingdomName = kingdomName;
        return this;
    }
}
