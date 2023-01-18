package feudal.gameClasses.secondGameClasses;

import feudal.gameClasses.PlayerGameClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Killer extends PlayerGameClass {

    Player player;

    @Override
    public void addSpeedLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setSpeedLvl(PlayerGameClass.getPlayerInfo().get(player).getSpeedLvl() + 1);

    }

    @Override
    public void addLuckLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setLuckLvl(PlayerGameClass.getPlayerInfo().get(player).getLuckLvl() + 1);

    }
}
