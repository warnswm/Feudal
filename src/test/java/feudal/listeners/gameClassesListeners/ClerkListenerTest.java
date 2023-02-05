package feudal.listeners.gameClassesListeners;

import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClerkListenerTest {
    /**
     * Method under test: {@link ClerkListener#playerItemEnchant(EnchantItemEvent)}
     */
    @Test
    void testPlayerItemEnchant() {
        assertThrows(IllegalArgumentException.class, () -> (new ClerkListener()).playerItemEnchant(null));
    }

    /**
     * Method under test: {@link ClerkListener#playerInteract(PlayerInteractEvent)}
     */
    @Test
    void testPlayerInteract() {
        assertThrows(IllegalArgumentException.class, () -> (new ClerkListener()).playerInteract(null));
    }
}

