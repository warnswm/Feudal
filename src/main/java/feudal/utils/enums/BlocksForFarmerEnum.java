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
    CROPS(Material.CROPS, 3),
    POTATO(Material.POTATO, 5),
    BEETROOT_BLOCK(Material.BEETROOT_BLOCK, 8),
    CARROT(Material.CARROT, 15),
    PUMPKIN_STEM(Material.PUMPKIN_STEM, 25),
    COCOA(Material.COCOA, 25),
    MELON_STEM(Material.MELON_STEM, 25);

    Material material;
    int attributeExp;

    public static int getByMaterial(Material material) {
        for (BlocksForFarmerEnum blocksForFarmerEnum : values())
            if (blocksForFarmerEnum.getMaterial() == material)
                return blocksForFarmerEnum.attributeExp;
        return 0;
    }
    public static boolean getPlant(Material material) {
        for (BlocksForFarmerEnum blocksForFarmerEnum : values())
            if (blocksForFarmerEnum.getMaterial() == material)
                return true;
        return false;
    }
}
