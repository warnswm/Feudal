package feudal.utils;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

public class GsonUtils {

    public static String chunkToJson(@NotNull ChunkWrapper chunk) {
        return new Gson().toJson(chunk);
    }
}
