package feudal.view;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoreBoards {

    Player player;
    int lvl;
    int experience;
    int balance;
    int deaths;
    int kills;

    final HashMap<Player, ScoreBoards> playerScoreBoard = new HashMap<>();

    public ScoreBoards setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public ScoreBoards setLvl(int lvl) {
        this.lvl = lvl;
        return this;
    }

    public ScoreBoards setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public ScoreBoards setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public ScoreBoards setDeaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public ScoreBoards setKills(int kills) {
        this.kills = kills;
        return this;
    }

    public ScoreBoards build() {


        //Score

        return this;
    }
}
