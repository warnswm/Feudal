package feudal.utils.wrappers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemStackWrapper {
    Material type;
    short durability;
    int amount;
    String name;
    List<String> lore;
    Map<Enchantment, Integer> enchants;
    int price;

}
