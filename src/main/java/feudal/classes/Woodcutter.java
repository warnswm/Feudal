package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Woodcutter extends AbstractClasses {
    byte lvl = 1;
    final byte maxlvl = 30;
    float gain = 0.8F;
    final Player player;

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
