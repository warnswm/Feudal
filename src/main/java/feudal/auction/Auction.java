package feudal.auction;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auction {

    static int goodsNumber;
    static List<ItemStack> goods = new ArrayList<>();

    public static void addGoodsNumber() {
        goodsNumber++;
    }

    public static void addGoods(ItemStack itemStack) {
        goods.add(itemStack);
    }
}
