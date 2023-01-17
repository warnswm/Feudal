package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hunter extends PlayerClass {
    final byte maxlvl = 25;
    final Player player;
    byte lvl = 1;
    float gainFirst = 1.65F;
    float gainSecond = 0.2F;

    public Hunter(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        lvl++;
        gainFirst += 1.65F;
        gainSecond += 0.2F;

        //player+gain%
    }
}
