package feudal.utils.enums;

import org.bukkit.enchantments.Enchantment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class EnchantmentEnumTest {
    /**
     * Method under test: {@link EnchantmentEnum#getByID(int)}
     */
    @Test
    void testGetByID() {
        Enchantment actualByID = EnchantmentEnum.getByID(1);
        assertSame(Enchantment.PROTECTION_ENVIRONMENTAL, actualByID);
    }

    /**
     * Method under test: {@link EnchantmentEnum#getByID(int)}
     */
    @Test
    void testGetByID2() {
        assertNull(EnchantmentEnum.getByID(123));
    }

    /**
     * Method under test: {@link EnchantmentEnum#getRandomEnc()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRandomEnc() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        // TODO: Populate arranged inputs
        Enchantment actualRandomEnc = EnchantmentEnum.getRandomEnc();

        // Assert
        // TODO: Add assertions on result
    }
}

