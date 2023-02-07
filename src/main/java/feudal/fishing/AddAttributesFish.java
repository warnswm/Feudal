package feudal.fishing;

import feudal.utils.MathUtils;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AddAttributesFish {

    public static @NotNull ItemStack setFishAttributes(ItemStack fish) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(fish);

        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();

        if (MathUtils.getRandomInt(1, 501) == 500)
            tag.setString("rare", "Легендарная");

        else if (MathUtils.getRandomInt(1, 151) == 150)
            tag.setString("rare", "Мифическая");

        else if (MathUtils.getRandomInt(1, 51) == 50)
            tag.setString("rare", "Эпическая");

        else if (MathUtils.getRandomInt(1, 16) == 15)
            tag.setString("rare", "Редкая");

        else
            tag.setString("rare", "Обычная");


        tag.setDouble("weight", MathUtils.getRandomDouble(1, 34000));
        tag.setDouble("size", MathUtils.getRandomDouble(5, 1300));

        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
