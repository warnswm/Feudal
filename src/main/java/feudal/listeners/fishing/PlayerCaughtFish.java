package feudal.listeners.fishing;

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

        ((Item) event.getCaught()).setItemStack(AddAttributesFish.setFishAttributes(((Item) event.getCaught()).getItemStack()));

        ItemMeta fishMeta = ((Item) event.getCaught()).getItemStack().getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add("Редкость: " + Objects.requireNonNull(CraftItemStack.asNMSCopy(((Item) event.getCaught()).getItemStack()).getTag()).getString("rare"));
        lore.add("Размер: " + String.format("%.2f", Objects.requireNonNull(CraftItemStack.asNMSCopy(((Item) event.getCaught()).getItemStack()).getTag()).getDouble("size")) + " см");
        lore.add("Вес: " + String.format("%.2f", Objects.requireNonNull(CraftItemStack.asNMSCopy(((Item) event.getCaught()).getItemStack()).getTag()).getDouble("weight")) + " кг");

        fishMeta.setLore(lore);
        ((Item) event.getCaught()).getItemStack().setItemMeta(fishMeta);

    }
}
