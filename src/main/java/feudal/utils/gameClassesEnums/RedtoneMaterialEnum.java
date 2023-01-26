package feudal.utils.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum RedtoneMaterialEnum {
    REDSTONE_WIRE(Material.REDSTONE_WIRE),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK),
    REDSTONE_COMPARATOR(Material.REDSTONE_COMPARATOR),
    REDSTONE_TORCH_OFF(Material.REDSTONE_TORCH_OFF),
    REDSTONE_TORCH_ON(Material.REDSTONE_TORCH_ON),
    PISTON_BASE(Material.PISTON_BASE),
    PISTON_MOVING_PIECE(Material.PISTON_MOVING_PIECE),
    PISTON_EXTENSION(Material.PISTON_EXTENSION),
    PISTON_STICKY_BASE(Material.PISTON_STICKY_BASE),
    LEVER(Material.LEVER),
    STONE_BUTTON(Material.STONE_BUTTON),
    WOOD_PLATE(Material.WOOD_PLATE),
    GOLD_PLATE(Material.GOLD_PLATE),
    STONE_PLATE(Material.STONE_PLATE),
    IRON_PLATE(Material.IRON_PLATE),
    RAILS(Material.RAILS),
    ACTIVATOR_RAIL(Material.ACTIVATOR_RAIL),
    POWERED_RAIL(Material.POWERED_RAIL),
    DETECTOR_RAIL(Material.DETECTOR_RAIL),
    TRIPWIRE_HOOK(Material.TRIPWIRE_HOOK),
    TRAPPED_CHEST(Material.TRAPPED_CHEST),
    OBSERVER(Material.OBSERVER),
    DROPPER(Material.DROPPER),
    DISPENSER(Material.DISPENSER),
    WOOD_BUTTON(Material.WOOD_BUTTON);

    Material material;

    public static boolean getByMaterial(Material material) {
        for (RedtoneMaterialEnum redtoneMaterialEnum : values())
            if (redtoneMaterialEnum.getMaterial() == material)
                return true;
        return false;
    }
}
