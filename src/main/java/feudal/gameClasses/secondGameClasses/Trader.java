package feudal.gameClasses.secondGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trader extends PlayerGameClass {

    Player player;


    @Override
    public void addLuckLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setLuckLvl(PlayerGameClass.getPlayerInfo().get(player).getLuckLvl() + 1);

    }

    @Override
    public void addSurvivabilityLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setSurvivabilityLvl(PlayerGameClass.getPlayerInfo().get(player).getSurvivabilityLvl() + 1);

    }
}
