package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerInfoCache {
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


    public void addExperience(int value) {

        experience += value;

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
    public PlayerInfoCache setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public PlayerInfoCache setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public PlayerInfoCache setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public PlayerInfoCache setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public PlayerInfoCache setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public PlayerInfoCache setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public PlayerInfoCache setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public PlayerInfoCache setExperience(int experience) {
        this.experience = experience;
        return this;
    }
    public PlayerInfoCache setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public PlayerInfoCache setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public PlayerInfoCache setKills(int kills) {
        this.kills = kills;
        return this;
    }
    public PlayerInfoCache build() {
        return this;
    }
}
