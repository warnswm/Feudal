package feudal.view;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class OverviewOfTheCastle {
    final Player player;
    Location startingLocation;

    public OverviewOfTheCastle(Player player) {

        this.player = player;
        startingLocation = player.getLocation();

    }

    public void startOverview() {

        player.teleport(startingLocation.add(0, 100, 0));

    }
    public void stopOverview() {

        player.teleport(startingLocation);

    }
}
