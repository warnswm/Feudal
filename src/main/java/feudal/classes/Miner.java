package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Miner extends PlayerClass {

    Player player;
    long lvl = PlayerClass.getPlayerInfo().get(player).getLvl();
    double gain = PlayerClass.getPlayerInfo().get(player).getGain();
    final long experience = PlayerClass.getPlayerInfo().get(player).getExperience();
    public Miner(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        if (lvl == 40) return;

        lvl++;
        gain += 0.8F;

        //player+gain%
    }
}
