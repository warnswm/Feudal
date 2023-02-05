package feudal.utils.wrappers;

import org.bukkit.Chunk;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ChunkWrapperTest {
    /**
     * Method under test: {@link ChunkWrapper#chunkToChunkWrapper(Chunk)}
     */
    @Test
    void testChunkToChunkWrapper() {
        assertThrows(IllegalArgumentException.class, () -> ChunkWrapper.chunkToChunkWrapper(null));
    }
}

