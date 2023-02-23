package feudal.listeners.fishing;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCaughtFish implements Listener {

    @EventHandler
    public final void playerFishing(@NotNull PlayerFishEvent event) {

        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH ||
                ((Item) event.getCaught()).getItemStack().getType() != Material.RAW_FISH) return;

        Item item = (Item) event.getCaught();
        item.setItemStack(AddAttributesFish.setFishAttributes(item.getItemStack()));

        ItemMeta fishMeta = item.getItemStack().getItemMeta();
        List<String> lore = new ArrayList<>();

        NBTTagCompound nbtTag = Objects.requireNonNull(CraftItemStack.asNMSCopy(item.getItemStack()).getTag());

        lore.add("Редкость: " + nbtTag.getString("rare"));
        lore.add("Размер: " + String.format("%.2f", nbtTag.getDouble("size")) + " см");
        lore.add("Вес: " + String.format("%.2f", nbtTag.getDouble("weight")) + " кг");

        fishMeta.setLore(lore);
        item.getItemStack().setItemMeta(fishMeta);

    }
}
