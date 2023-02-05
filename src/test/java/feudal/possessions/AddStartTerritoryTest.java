package feudal.possessions;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class AddStartTerritoryTest {
    /**
     * Method under test: {@link AddStartTerritory#createStartTerritory(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateStartTerritory() {
        // TODO: Complete this test.
        //   Reason: R006 Static initializer failed.
        //   The static initializer of
        //   feudal.possessions.AddStartTerritory
        //   threw java.lang.NullPointerException while trying to load it.
        //   Make sure the static initializer of AddStartTerritory
        //   can be executed without throwing exceptions.
        //   Exception: java.lang.NullPointerException
        //       at org.bukkit.Bukkit.getPluginManager(Bukkit.java:422)
        //       at feudal.possessions.AddStartTerritory.<clinit>(AddStartTerritory.java:18)
        //       at java.lang.Class.forName0(Native Method)
        //       at java.lang.Class.forName(Class.java:348)

        // Arrange
        // TODO: Populate arranged inputs
        String kingdomName = "";
        List<String> startTerritory = null;

        // Act
        boolean actualCreateStartTerritoryResult = AddStartTerritory.createStartTerritory(kingdomName, startTerritory);

        // Assert
        // TODO: Add assertions on result
    }
}

