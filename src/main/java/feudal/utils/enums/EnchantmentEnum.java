package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnchantmentEnum {

    PROTECTION_ENVIRONMENTAL(Enchantment.PROTECTION_ENVIRONMENTAL, 1), PROTECTION_FIRE(Enchantment.PROTECTION_FIRE, 2), PROTECTION_FALL(Enchantment.PROTECTION_FALL, 3),
    PROTECTION_EXPLOSIONS(Enchantment.PROTECTION_EXPLOSIONS, 4), PROTECTION_PROJECTILE(Enchantment.PROTECTION_PROJECTILE, 5), OXYGEN(Enchantment.OXYGEN, 6),
    WATER_WORKER(Enchantment.WATER_WORKER, 7), THORNS(Enchantment.THORNS, 8), DEPTH_STRIDER(Enchantment.DEPTH_STRIDER, 9),
    FROST_WALKER(Enchantment.FROST_WALKER, 10), BINDING_CURSE(Enchantment.BINDING_CURSE, 11), DAMAGE_ALL(Enchantment.DAMAGE_ALL, 12),
    DAMAGE_UNDEAD(Enchantment.DAMAGE_UNDEAD, 13), DAMAGE_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS, 14), KNOCKBACK(Enchantment.KNOCKBACK, 15),
    FIRE_ASPECT(Enchantment.FIRE_ASPECT, 16), LOOT_BONUS_MOBS(Enchantment.LOOT_BONUS_MOBS, 17), SWEEPING_EDGE(Enchantment.SWEEPING_EDGE, 18),
    DIG_SPEED(Enchantment.DIG_SPEED, 19), DURABILITY(Enchantment.DURABILITY, 20), LOOT_BONUS_BLOCKS(Enchantment.LOOT_BONUS_BLOCKS, 21),
    ARROW_DAMAGE(Enchantment.ARROW_DAMAGE, 22), ARROW_KNOCKBACK(Enchantment.ARROW_KNOCKBACK, 23), ARROW_FIRE(Enchantment.ARROW_FIRE, 24),
    ARROW_INFINITE(Enchantment.ARROW_INFINITE, 25), LUCK(Enchantment.LUCK, 26), LURE(Enchantment.LURE, 27),
    MENDING(Enchantment.MENDING, 28), VANISHING_CURSE(Enchantment.VANISHING_CURSE, 29), SILK_TOUCH(Enchantment.SILK_TOUCH, 30);

    Enchantment enchantment;
    int id;

    public static @Nullable Enchantment getByID(int id) {
        for (EnchantmentEnum enchantmentEnum : values())
            if (enchantmentEnum.getId() == id)
                return enchantmentEnum.enchantment;
        return null;
    }

    public static Enchantment getRandomEnc() {
        return EnchantmentEnum.getByID(new Random().ints(1, 30).findFirst().getAsInt());
    }

}
