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

    static File file = new File(Feudal.getPlugin().getDataFolder().getPath(), "auction.json");

    static List<ItemStackWrapper> products = new ArrayList<>();

    public static void addProduct(@NotNull ItemStackWrapper product) {

        products.add(product);

    }

    public static void save() {

        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {

            if (!file.exists())
                file.createNewFile();


            if (products.isEmpty()) return;

            out.write(GsonUtils.itemStackWrapperToJson(products));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public static void load() {

        try {

            if (!file.exists()) {

                file.createNewFile();
                return;

            }

            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(file));

            if (gson.fromJson(reader, List.class) == null) return;

            products = (List<ItemStackWrapper>) gson.fromJson(reader, List.class);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}
