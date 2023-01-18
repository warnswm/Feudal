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
    public void addStrengthLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setStrengthLvl(PlayerGameClass.getPlayerInfo().get(player).getStrengthLvl() + 1);

    }

    @Override
    public void addLuckLvl() {

        PlayerGameClass.getPlayerInfo().get(player).setLuckLvl(PlayerGameClass.getPlayerInfo().get(player).getLuckLvl() + 1);

    }
}
