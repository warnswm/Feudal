package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Farmer extends PlayerClass {
    byte lvl = 1;
    final byte maxlvl = 35;
    float gain = 0.45F;
    final Player player;

    public Farmer(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        lvl++;
        gain += 0.45F;

        //player+gain%
    }
}
