package feudal.data.cache;

import feudal.data.database.KingdomInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CacheKingdomsMapTest {
    /**
     * Method under test: {@link CacheKingdomsMap#getKingdomInfo()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetKingdomInfo() {
        // TODO: Complete this test.
        //   Reason: T005 Trivial constructor.
        //   See https://diff.blue/T005

        // Arrange and Act
        // TODO: Populate arranged inputs
        Map<String, KingdomInfo> actualKingdomInfo = CacheKingdomsMap.getKingdomInfo();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link CacheKingdomsMap#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache() {
        assertFalse(CacheKingdomsMap.chunkInKingdomCache("Chunk"));
        assertThrows(IllegalArgumentException.class, () -> CacheKingdomsMap.chunkInKingdomCache(null));
    }
}

