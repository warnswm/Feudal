package feudal.listeners;

import feudal.Feudal;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameClassesListenersTest {

    @org.junit.jupiter.api.Test
    void blockPlaced(BlockPlaceEvent event) {

        Block block = event.getBlock();
        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));

        assertEquals(block.hasMetadata("PLACED"), null);
    }

    @org.junit.jupiter.api.Test
    void blockBreak() {
    }

    @org.junit.jupiter.api.Test
    void playerFishing() {
    }

    @org.junit.jupiter.api.Test
    void entityBreed() {
    }

    @org.junit.jupiter.api.Test
    void playerHunted() {
    }

    @org.junit.jupiter.api.Test
    void regenerationEvent() {
    }

    @org.junit.jupiter.api.Test
    void playerMove() {
    }

    @org.junit.jupiter.api.Test
    void playerAttack() {
    }
}