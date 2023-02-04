package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ArmorEnum {

    LEATHER_HELMET(Material.LEATHER_HELMET, -4), CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, -2), IRON_HELMET(Material.IRON_HELMET, -2),
    GOLD_HELMET(Material.GOLD_HELMET, -2), CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, 4), IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 6),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 10), GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 4), LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, -2),
    CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, 2), IRON_LEGGINGS(Material.IRON_LEGGINGS, 4), DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 6),
    LEATHER_BOOTS(Material.LEATHER_BOOTS, -4), CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, -4), IRON_BOOTS(Material.IRON_BOOTS, -2),
    GOLD_BOOTS(Material.GOLD_BOOTS, -4);

    Material armor;
    int attribute;

    public static int getByItemMaterial(Material item) {
        for (ArmorEnum armorEnum : values())
            if (armorEnum.getArmor() == item)
                return armorEnum.getAttribute();
        return 0;
    }
}
