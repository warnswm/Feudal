package feudal.gameClasses.firstGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hunter extends PlayerGameClass {

    Player player;

    @Override
    public void addStaminaLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStaminaLvl(PlayerGameClass.getPlayerInfo().get(player).getStaminaLvl() + 1);

    }

    @Override
    public void addSpeedLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setSpeedLvl(PlayerGameClass.getPlayerInfo().get(player).getSpeedLvl() + 1);

    }
}
