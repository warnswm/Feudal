package feudal.auction;

import com.google.gson.Gson;
import feudal.Feudal;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.io.*;
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

        } catch (IOException e) {

            throw new RuntimeException(e);

        }


    }
    public static void load() {

        try {

            products.add(new Gson().fromJson(new FileReader(new File(Feudal.getPlugin().getDataFolder().getPath(), "auction.json")), ItemStackWrapper.class));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}
