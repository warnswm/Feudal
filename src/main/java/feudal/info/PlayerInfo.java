package feudal.info;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerInfo {
    Player player;
    int lvl;
    double gain;
    int aClassID;
    int experience;

    public void addExperience(int value) {

        experience += value;

        experienceCalc();

    }
    private void experienceCalc() {

        if (experience >= Math.pow(lvl + 1, 3))
            lvl++;

    }

}
