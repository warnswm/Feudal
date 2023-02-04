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

    LEATHER_HELMET(Material.LEATHER_HELMET, -10), CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, -5), IRON_HELMET(Material.IRON_HELMET, -5),
    GOLD_HELMET(Material.GOLD_HELMET, -5), CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, 10), IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 15),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 25), GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 10), LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, -5),
    CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, 5), IRON_LEGGINGS(Material.IRON_LEGGINGS, 10), DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 15),
    LEATHER_BOOTS(Material.LEATHER_BOOTS, -10), CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, -10), IRON_BOOTS(Material.IRON_BOOTS, -5),
    GOLD_BOOTS(Material.GOLD_BOOTS, -10);

    Material armor;
    int attribute;

    public static int getByItemMaterial(Material item) {
        for (ArmorEnum armorEnum : values())
            if (armorEnum.getArmor() == item)
                return armorEnum.getAttribute();
        return 0;
    }
}
