package feudal.listeners.gameClassesListeners;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameClassesExpListenersTest {
    /**
     * Method under test: {@link GameClassesExpListeners#playerBlockBreak(BlockBreakEvent)}
     */
    @Test
    void testPlayerBlockBreak() {
        assertThrows(IllegalArgumentException.class, () -> (new GameClassesExpListeners()).playerBlockBreak(null));
    }

    /**
     * Method under test: {@link GameClassesExpListeners#playerFishing(PlayerFishEvent)}
     */
    @Test
    void testPlayerFishing() {
        assertThrows(IllegalArgumentException.class, () -> (new GameClassesExpListeners()).playerFishing(null));
    }

    /**
     * Method under test: {@link GameClassesExpListeners#playerItemEnchant(EnchantItemEvent)}
     */
    @Test
    void testPlayerItemEnchant() {
        assertThrows(IllegalArgumentException.class, () -> (new GameClassesExpListeners()).playerItemEnchant(null));
    }

    /**
     * Method under test: {@link GameClassesExpListeners#playerBreed(EntityBreedEvent)}
     */
    @Test
    void testPlayerBreed() {
        assertThrows(IllegalArgumentException.class, () -> (new GameClassesExpListeners()).playerBreed(null));
    }

    /**
     * Method under test: {@link GameClassesExpListeners#playerHunted(EntityDeathEvent)}
     */
    @Test
    void testPlayerHunted() {
        assertThrows(IllegalArgumentException.class, () -> (new GameClassesExpListeners()).playerHunted(null));
    }
}

