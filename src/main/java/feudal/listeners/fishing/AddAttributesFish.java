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

        if (ThreadLocalRandom.current().nextInt(1, 501) == 500) {

            tag.setString("rare", "Легендарная");
            tag.setDouble("weight", ThreadLocalRandom.current().nextDouble(1, 12000));
            tag.setDouble("size", ThreadLocalRandom.current().nextDouble(5, 6800));

        } else if (ThreadLocalRandom.current().nextInt(1, 151) == 150) {

            tag.setString("rare", "Мифическая");
            tag.setDouble("weight", ThreadLocalRandom.current().nextDouble(1, 9000));
            tag.setDouble("size", ThreadLocalRandom.current().nextDouble(5, 6800));

        } else if (ThreadLocalRandom.current().nextInt(1, 51) == 50) {

            tag.setString("rare", "Эпическая");
            tag.setDouble("weight", ThreadLocalRandom.current().nextDouble(1, 6800));
            tag.setDouble("size", ThreadLocalRandom.current().nextDouble(5, 6800));

        } else if (ThreadLocalRandom.current().nextInt(1, 16) == 15) {

            tag.setString("rare", "Редкая");
            tag.setDouble("weight", ThreadLocalRandom.current().nextDouble(1, 680));
            tag.setDouble("size", ThreadLocalRandom.current().nextDouble(5, 6800));

        } else {

            tag.setString("rare", "Обычная");
            tag.setDouble("weight", ThreadLocalRandom.current().nextDouble(1, 68));
            tag.setDouble("size", ThreadLocalRandom.current().nextDouble(5, 680));

        }


        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
