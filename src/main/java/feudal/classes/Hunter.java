package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hunter extends AbstractClasses {
    byte lvl = 1;
    final byte maxlvl = 25;
    float gainFirst = 1.65F;
    float gainSecond = 0.2F;
    final Player player;

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
