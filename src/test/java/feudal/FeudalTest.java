package feudal;

import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FeudalTest {
    /**
     * Method under test: {@link Feudal#getPlugin()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPlugin() {
        // TODO: Complete this test.
        //   Reason: R081 Exception in arrange section.
        //   Diffblue Cover was unable to construct an instance of the class under test using
        //   feudal.Feudal.<init>
        //   A step in the arrange section threw an exception:
        //   IllegalStateException JavaPlugin requires org.bukkit.plugin.java.PluginClassLoader
        //   More information about the exception is provided in the support log.
        //   See https://diff.blue/R081 for further troubleshooting of this issue.

        // Arrange and Act
        // TODO: Populate arranged inputs
        Plugin actualPlugin = Feudal.getPlugin();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link Feudal#onEnable()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testOnEnable() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: JavaPlugin requires org.bukkit.plugin.java.PluginClassLoader
        //       at org.bukkit.plugin.java.JavaPlugin.<init>(JavaPlugin.java:58)
        //       at feudal.Feudal.<init>(Feudal.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        (new Feudal()).onEnable();
    }

    /**
     * Method under test: {@link Feudal#onDisable()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testOnDisable() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: JavaPlugin requires org.bukkit.plugin.java.PluginClassLoader
        //       at org.bukkit.plugin.java.JavaPlugin.<init>(JavaPlugin.java:58)
        //       at feudal.Feudal.<init>(Feudal.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        (new Feudal()).onDisable();
    }
}

