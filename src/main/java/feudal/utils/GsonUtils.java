package feudal.utils;

import com.google.gson.Gson;
import feudal.utils.wrappers.BannerWrapper;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.PlacedBlockWrapper;
import org.jetbrains.annotations.NotNull;

public class GsonUtils {

    public static String bannerToJson(@NotNull BannerWrapper bannerWrapper) {
        return new Gson().toJson(bannerWrapper);
    }

    public static String placedBlockToJson(@NotNull PlacedBlockWrapper placedBlockWrapper) {
        return new Gson().toJson(placedBlockWrapper);
    }

    public static String chunkToJson(@NotNull ChunkWrapper chunk) {
        return new Gson().toJson(chunk);
    }
}
