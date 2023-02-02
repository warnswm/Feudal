package feudal.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChunkWrapper {

    String worldName;

    int x, z;

    @Contract("_ -> new")
    public static @NotNull ChunkWrapper chunkToChunkWrapper(@NotNull Chunk chunk) {
        return new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
    }

}
