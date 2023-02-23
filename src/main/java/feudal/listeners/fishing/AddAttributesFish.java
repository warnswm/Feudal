package feudal.listeners.fishing;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class AddAttributesFish {

    public static @NotNull ItemStack setFishAttributes(ItemStack fish) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(fish);
        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();

        int chance = ThreadLocalRandom.current().nextInt(1, 501);

        if (chance == 500) {

            tag.setString("rare", "Легендарная");
            tag.setDouble("weight", rand(1, 12000));
            tag.setDouble("size", rand(5, 6800));

        } else if (chance <= 3) {

            tag.setString("rare", "Мифическая");
            tag.setDouble("weight", rand(1, 9000));
            tag.setDouble("size", rand(5, 6800));

        } else if (chance <= 10) {

            tag.setString("rare", "Эпическая");
            tag.setDouble("weight", rand(1, 6800));
            tag.setDouble("size", rand(5, 6800));

        } else if (chance <= 30) {

            tag.setString("rare", "Редкая");
            tag.setDouble("weight", rand(1, 680));
            tag.setDouble("size", rand(5, 6800));

        } else {

            tag.setString("rare", "Обычная");
            tag.setDouble("weight", rand(1, 68));
            tag.setDouble("size", rand(5, 680));

        }


        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);

    }

    private static double rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
