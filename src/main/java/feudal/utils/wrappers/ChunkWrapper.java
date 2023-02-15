package feudal.utils.wrappers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChunkWrapper {

    String worldName;

    int x, z;

    @Contract("_ -> new")
    public static @NotNull ChunkWrapper chunkToChunkWrapper(@NotNull Chunk chunk) {
        return new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
    }

    @Contract("_ -> new")
    public static @NotNull Chunk chunkWrapperToChunk(@NotNull ChunkWrapper chunkWrapper) {
        return Bukkit.getWorld(chunkWrapper.getWorldName()).getChunkAt(chunkWrapper.getX(), chunkWrapper.getZ());
    }

}
