package feudal.view;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassSelectionMenu {

    Player player;

    public ClassSelectionMenu(Player player) {
        this.player = player;
    }

    public void openClassSelection() {

        Inventory inventory = Bukkit.createInventory(player, 9, "Выберите класс");

        ItemStack farmer = new ItemStack(Material.BLACK_SHULKER_BOX, 1);
        ItemMeta farmerMeta = farmer.getItemMeta();

        farmerMeta.setDisplayName("Фермер");
        farmer.setItemMeta(farmerMeta);

        inventory.setItem(2, farmer);


        ItemStack miner = new ItemStack(Material.WHITE_SHULKER_BOX, 1);
        ItemMeta minerMeta = miner.getItemMeta();

        minerMeta.setDisplayName("Шахтёр");
        miner.setItemMeta(minerMeta);

        inventory.setItem(3, miner);


        ItemStack angler = new ItemStack(Material.RED_SHULKER_BOX, 1);
        ItemMeta anglerMeta = angler.getItemMeta();

        anglerMeta.setDisplayName("Рыболов");
        angler.setItemMeta(anglerMeta);

        inventory.setItem(4, angler);


        ItemStack hunter = new ItemStack(Material.YELLOW_SHULKER_BOX, 1);
        ItemMeta hunterMeta = hunter.getItemMeta();

        hunterMeta.setDisplayName("Охотник");
        hunter.setItemMeta(hunterMeta);

        inventory.setItem(5, hunter);


        ItemStack woodcutter = new ItemStack(Material.BLUE_SHULKER_BOX, 1);
        ItemMeta woodcutterMeta = woodcutter.getItemMeta();

        woodcutterMeta.setDisplayName("Дровосек");
        woodcutter.setItemMeta(woodcutterMeta);

        inventory.setItem(6, woodcutter);


        player.openInventory(inventory);

    }
}
