package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerInfo {
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

        experienceCalc();

    }

    private void experienceCalc() {



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
    public PlayerInfo setStrengthLvl(int strengthLvl) {
        this.strengthLvl = strengthLvl;
        return this;
    }

    public PlayerInfo setSurvivabilityLvl(int survivabilityLvl) {
        this.survivabilityLvl = survivabilityLvl;
        return this;
    }

    public PlayerInfo setSpeedLvl(int speedLvl) {
        this.speedLvl = speedLvl;
        return this;
    }

    public PlayerInfo setStaminaLvl(int staminaLvl) {
        this.staminaLvl = staminaLvl;
        return this;
    }

    public PlayerInfo setLuckLvl(int luckLvl) {
        this.luckLvl = luckLvl;
        return this;
    }

    public PlayerInfo setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public PlayerInfo setaClassID(int aClassID) {
        this.aClassID = aClassID;
        return this;
    }

    public PlayerInfo setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public PlayerInfo setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public PlayerInfo setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public PlayerInfo setKills(int kills) {
        this.kills = kills;
        return this;
    }
    public PlayerInfo build() {
        return this;
    }
}
