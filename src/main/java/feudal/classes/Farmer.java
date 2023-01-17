package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Farmer extends PlayerClass {
    final byte maxlvl = 35;
    final Player player;
    byte lvl = 1;
    float gain = 0.45F;

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
