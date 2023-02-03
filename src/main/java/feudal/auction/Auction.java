package feudal.auction;

import feudal.Feudal;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auction {
    static List<ItemStackWrapper> products = new ArrayList<>();

    public static void addProduct(@NotNull ItemStackWrapper product) {
        products.add(product);
    }

    public static void save() {

        try (PrintWriter out = new PrintWriter(new FileWriter(new File(Feudal.getPlugin().getDataFolder().getPath(), "auction.json")))) {

            out.write(GsonUtils.itemStackWrapperToJson(products));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
