package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum BlocksForFarmerEnum {
    COAL_ORE(Material.COAL_ORE, 3), IRON_ORE(Material.IRON_ORE, 5), GOLD_ORE(Material.GOLD_ORE, 8),
    DIAMOND_ORE(Material.DIAMOND_ORE, 15), EMERALD_ORE(Material.EMERALD_ORE, 25);

    Material material;
    int attributeExp;

    public static int getByMaterial(Material material) {
        for (BlocksForFarmerEnum blocksForFarmerEnum : values())
            if (blocksForFarmerEnum.getMaterial() == material)
                return blocksForFarmerEnum.attributeExp;
        return 0;
    }
}
