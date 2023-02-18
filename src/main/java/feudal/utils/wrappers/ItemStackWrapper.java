package feudal.utils.wrappers;

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
@FieldDefaults(makeFinal = true)
public class ItemStackWrapper {
    public Material type;
    public short durability;
    public int amount;
    public String name;
    public List<String> lore;
    public Map<Enchantment, Integer> enchants;
    public int price;

}
