package feudal.utils.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum BlockForFarmer {
    COAL_ORE(Material.COAL_ORE, 3),
    IRON_ORE(Material.IRON_ORE, 5),
    GOLD_ORE(Material.GOLD_ORE, 8),
    DIAMOND_ORE(Material.DIAMOND_ORE, 15),
    EMERALD_ORE(Material.EMERALD_ORE, 25);

    Material material;
    int attributeExp;

    public static int getByMaterial(Material material) {
        for (BlockForFarmer blockForFarmer : values())
            if (blockForFarmer.getMaterial() == material)
                return blockForFarmer.attributeExp;
        return 0;
    }
}
