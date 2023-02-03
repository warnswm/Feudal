package feudal.auction;

import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auction {

    static int productNumber;
    static List<ItemStackWrapper> products = new ArrayList<>();
    static Map<ItemStackWrapper, Integer> productsPrice = new HashMap<>();

    public static void addProductNumber() {
        productNumber++;
    }

    public static void addProduct(@NotNull ItemStackWrapper product, int productPrice) {
        products.add(product);
        productsPrice.put(product, productPrice);
    }

    public static void save() {
        System.out.println(GsonUtils.itemStackToJson(products));
    }
}
