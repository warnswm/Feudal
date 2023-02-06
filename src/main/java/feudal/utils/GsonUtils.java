package feudal.utils;

import com.google.gson.Gson;
import feudal.utils.wrappers.BannerWrapper;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.ItemStackWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GsonUtils {

    public static String chunkToJson(@NotNull ChunkWrapper chunk) {
        return new Gson().toJson(chunk);
    }

    public static String bannerToJson(@NotNull BannerWrapper bannerWrapper) {
        return new Gson().toJson(bannerWrapper);
    }

    public static String itemStackWrapperToJson(@NotNull List<ItemStackWrapper> itemStack) {
        return new Gson().toJson(itemStack);
    }
}
