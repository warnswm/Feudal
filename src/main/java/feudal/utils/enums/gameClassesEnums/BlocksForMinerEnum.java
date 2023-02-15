package feudal.utils.enums.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum BlocksForMinerEnum {
    COAL_ORE(Material.COAL_ORE, 3), IRON_ORE(Material.IRON_ORE, 5), GOLD_ORE(Material.GOLD_ORE, 8),
    DIAMOND_ORE(Material.DIAMOND_ORE, 15), EMERALD_ORE(Material.EMERALD_ORE, 25), LAPIS_ORE(Material.LAPIS_ORE, 10),
    REDSTONE_ORE(Material.REDSTONE_ORE, 4);

    Material material;
    int attributeExp;

    public static int getByMaterial(Material material) {
        for (BlocksForMinerEnum blocksForMiner : values())
            if (blocksForMiner.getMaterial() == material)
                return blocksForMiner.getAttributeExp();
        return 0;
    }
}
