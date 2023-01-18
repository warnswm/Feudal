package feudal.gameClasses.firstGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Farmer extends PlayerGameClass {

    Player player;


    @Override
    public void addStaminaLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStaminaLvl(PlayerGameClass.getPlayerInfo().get(player).getStaminaLvl() + 1);

    }

    @Override
    public void addStrengthLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStrengthLvl(PlayerGameClass.getPlayerInfo().get(player).getStrengthLvl() + 1);

    }
}
