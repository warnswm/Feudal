package feudal.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class CollectionUtils {

    public List<String> uuidListToStringList(@NotNull List<UUID> uuids) {

        List<String> strUuids = new ArrayList<>();
        uuids.forEach(uuid -> strUuids.add(uuid.toString()));

        return strUuids;

    }

    public List<UUID> stringListToSUUIDList(@NotNull List<String> strUuids) {

        List<UUID> uuids = new ArrayList<>();
        strUuids.forEach(uuid -> uuids.add(UUID.fromString(uuid)));

        return uuids;

    }
}
