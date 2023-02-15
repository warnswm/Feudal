package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum BlockToSaveE {

    COAL_ORE(Material.COAL_ORE), IRON_ORE(Material.IRON_ORE), GOLD_ORE(Material.GOLD_ORE),
    DIAMOND_ORE(Material.DIAMOND_ORE), EMERALD_ORE(Material.EMERALD_ORE), LOG(Material.LOG);

    Material blockMaterial;

    public static boolean checkBlockMaterial(Material blockMaterial) {

        for (BlockToSaveE blockToSaveE : values())
            if (blockToSaveE.getBlockMaterial() == blockMaterial)
                return true;

        return false;

    }
}
