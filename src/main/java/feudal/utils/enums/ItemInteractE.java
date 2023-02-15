package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ItemInteractE {
    BUCKET(Material.BUCKET), LAVA_BUCKET(Material.LAVA_BUCKET), WATER_BUCKET(Material.WATER_BUCKET);

    Material item;

    public static boolean checkItemMaterial(Material item) {

        for (ItemInteractE itemInteractE : values())
            if (itemInteractE.getItem() == item)
                return true;

        return false;

    }
}
