package feudal.gameClasses.firstGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shepherd extends PlayerGameClass {

    Player player;

    @Override
    public void addSurvivabilityLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setSurvivabilityLvl(PlayerGameClass.getPlayerInfo().get(player).getSurvivabilityLvl() + 1);

    }

    @Override
    public void addStaminaLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStaminaLvl(PlayerGameClass.getPlayerInfo().get(player).getStaminaLvl() + 1);

    }
}
