package feudal.visual.menus;

import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.utils.CreateItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class MailMenu {

    public static void openMailMenu(@NotNull Player player) {

        FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);

        Inventory mailMenu = Bukkit.createInventory(player, 54, "Почта");

        int iterations = Math.min(feudalPlayer.getLetters().size(), 54);

        for (int i = 0; i < iterations; i++) {

            String text = feudalPlayer.getLetters().get(i);
            String title = text.length() < 10 ? text : feudalPlayer.getLetters().get(i).substring(0, 10);

            mailMenu.setItem(i, CreateItemUtils.createItem(Material.PAPER, 1, title + "...", feudalPlayer.getLetters().get(i)));

        }

        player.openInventory(mailMenu);

    }
}
