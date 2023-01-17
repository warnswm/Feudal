package feudal.classes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Angler extends PlayerClass {
    final byte maxlvl = 30;
    final Player player;
    byte lvl = 1;
    float gainFirst = 2.5F;
    float gainSecond = 1.25F;

    public Angler(Player player) {
        this.player = player;
    }

    @Override
    public void addLvl() {

        lvl++;
        gainFirst += 2.5F;
        gainSecond += 1.25F;

        //player+gain%
    }
}
