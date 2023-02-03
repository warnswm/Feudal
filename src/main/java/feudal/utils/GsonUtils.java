package feudal.utils;

import com.google.gson.Gson;
import feudal.utils.wrappers.BannerWrapper;
import feudal.utils.wrappers.ChunkWrapper;
import org.jetbrains.annotations.NotNull;

public class GsonUtils {

    public static String chunkToJson(@NotNull ChunkWrapper chunk) {
        return new Gson().toJson(chunk);
    }
    public static String bannerToJson(@NotNull BannerWrapper bannerWrapper) {
        return new Gson().toJson(bannerWrapper);
    }
}
