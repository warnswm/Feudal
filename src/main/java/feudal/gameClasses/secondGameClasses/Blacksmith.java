package feudal.gameClasses.secondGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blacksmith extends PlayerGameClass {

    Player player;

    @Override
    public void addStaminaLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStaminaLvl(PlayerGameClass.getPlayerInfo().get(player).getStaminaLvl() + 1);

    }

    @Override
    public void addLuckLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setLuckLvl(PlayerGameClass.getPlayerInfo().get(player).getLuckLvl() + 1);

    }
}
