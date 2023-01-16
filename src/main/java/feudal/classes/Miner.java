package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Miner extends AbstractClasses {
    byte lvl = 1;
    final byte maxlvl = 40;
    float gain = 0.8F;
    final Player player;

    public Miner(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        lvl++;
        gain += 0.8F;

        //player+gain%
    }
}
