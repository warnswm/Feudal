package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Woodcutter extends PlayerClass {
    final byte maxlvl = 30;
    final Player player;
    byte lvl = 1;
    float gain = 0.8F;

    public Woodcutter(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        lvl++;
        gain += 0.8F;

        //player+gain%
    }
}
