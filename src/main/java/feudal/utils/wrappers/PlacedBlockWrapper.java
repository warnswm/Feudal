package feudal.utils.wrappers;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlacedBlockWrapper {
    String worldName;
    int x, y, z;

    @Contract("_ -> new")
    public static @NotNull PlacedBlockWrapper blockToPlacedBlockWrapper(@NotNull Block block) {
        return new PlacedBlockWrapper(block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
    }

    @Contract("_ -> new")
    public static @NotNull Block placedBlockWrapperToBlock(@NotNull PlacedBlockWrapper placedBlockWrapper) {

        Block block = Bukkit.getWorld(placedBlockWrapper.getWorldName()).getBlockAt(placedBlockWrapper.getX(), placedBlockWrapper.getY(), placedBlockWrapper.getZ());
        block.setMetadata("PLACED", new FixedMetadataValue(Feudal.getPlugin(), "true"));

        return Bukkit.getWorld(placedBlockWrapper.getWorldName()).getBlockAt(placedBlockWrapper.getX(), placedBlockWrapper.getY(), placedBlockWrapper.getZ());

    }
}
