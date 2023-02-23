package feudal.utils.wrappers;

import lombok.Value;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.List;

@Value
public class FlagWrapper {

    String name;
    List<Pattern> patterns;

    public ItemStack create() {

        ItemStack flag = new ItemStack(Material.BANNER, 1);
        BannerMeta flagItemMeta = (BannerMeta) flag.getItemMeta();

        flagItemMeta.setPatterns(patterns);
        flagItemMeta.setDisplayName(name);
        flag.setItemMeta(flagItemMeta);

        return flag;

    }

}
