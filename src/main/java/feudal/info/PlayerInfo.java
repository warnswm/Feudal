package feudal.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerInfo {
    Player player;
    int lvl;
    double gain;
    int aClassID;
    int experience;
    int balance;
    int deaths;
    int kills;

    public void addExperience(int value) {

        experience += value;

        experienceCalc();

    }

    private void experienceCalc() {

        if (experience >= Math.pow(lvl + 1, 3))
            lvl++;

    }

    public PlayerInfo setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public PlayerInfo setLvl(int lvl) {
        this.lvl = lvl;
        return this;
    }

    public PlayerInfo setGain(double gain) {
        this.gain = gain;
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
