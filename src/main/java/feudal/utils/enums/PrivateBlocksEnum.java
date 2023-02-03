package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PrivateBlocksEnum {

    DISPENSER(Material.DISPENSER), NOTE_BLOCK(Material.NOTE_BLOCK), CHEST(Material.CHEST),
    FURNACE(Material.FURNACE), STONE_BUTTON(Material.STONE_BUTTON), WOOD_BUTTON(Material.WOOD_BUTTON),
    LEVER(Material.LEVER), JUKEBOX(Material.JUKEBOX), TRAP_DOOR(Material.TRAP_DOOR),
    ENCHANTMENT_TABLE(Material.ENCHANTMENT_TABLE), ENDER_CHEST(Material.ENDER_CHEST), BEACON(Material.BEACON),
    TRAPPED_CHEST(Material.TRAPPED_CHEST), HOPPER(Material.HOPPER), HOPPER_MINECART(Material.HOPPER_MINECART),
    DROPPER(Material.DROPPER), ACACIA_FENCE_GATE(Material.ACACIA_FENCE_GATE), BIRCH_FENCE_GATE(Material.BIRCH_FENCE_GATE),
    JUNGLE_FENCE_GATE(Material.JUNGLE_FENCE_GATE), SPRUCE_FENCE_GATE(Material.SPRUCE_FENCE_GATE), DARK_OAK_FENCE_GATE(Material.DARK_OAK_FENCE_GATE),
    FECE_GATE(Material.FENCE_GATE), GREEN_SHULKER_BOX(Material.GREEN_SHULKER_BOX), GRAY_SHULKER_BOX(Material.GRAY_SHULKER_BOX),
    SILVER_SHULKER_BOX(Material.SILVER_SHULKER_BOX), BLACK_SHULKER_BOX(Material.BLACK_SHULKER_BOX), BLUE_SHULKER_BOX(Material.BLUE_SHULKER_BOX),
    BROWN_SHULKER_BOX(Material.BROWN_SHULKER_BOX), CYAN_SHULKER_BOX(Material.CYAN_SHULKER_BOX), LIME_SHULKER_BOX(Material.LIME_SHULKER_BOX),
    MAGENTA_SHULKER_BOX(Material.MAGENTA_SHULKER_BOX), PINK_SHULKER_BOX(Material.PINK_SHULKER_BOX), ORANGE_SHULKER_BOX(Material.ORANGE_SHULKER_BOX),
    RED_SHULKER_BOX(Material.RED_SHULKER_BOX), WHITE_SHULKER_BOX(Material.WHITE_SHULKER_BOX), PURPLE_SHULKER_BOX(Material.PURPLE_SHULKER_BOX),
    YELLOW_SHULKER_BOX(Material.YELLOW_SHULKER_BOX), LIGHT_BLUE_SHULKER_BOX(Material.LIGHT_BLUE_SHULKER_BOX), DARK_OAK_DOOR(Material.DARK_OAK_DOOR),
    ACACIA_DOOR(Material.ACACIA_DOOR), IRON_DOOR(Material.IRON_DOOR), BIRCH_DOOR(Material.BIRCH_DOOR),
    SPRUCE_DOOR(Material.SPRUCE_DOOR), JUNGLE_DOOR(Material.JUNGLE_DOOR), WOOD_DOOR(Material.WOOD_DOOR),
    WOODEN_DOOR(Material.WOODEN_DOOR), BURNING_FURNACE(Material.BURNING_FURNACE), BED(Material.BED),
    REDSTONE_COMPARATOR(Material.REDSTONE_COMPARATOR), DIODE(Material.DIODE), DIODE_BLOCK_ON(Material.DIODE_BLOCK_ON),
    DIODE_BLOCK_OFF(Material.DIODE_BLOCK_OFF), BREWING_STAND(Material.BREWING_STAND), ANVIL(Material.ANVIL);

    Material material;

    public static boolean getByMaterial(Material material) {
        for (PrivateBlocksEnum privateBlocksEnum : values())
            if (privateBlocksEnum.getMaterial() == material)
                return true;
        return false;
    }
}
