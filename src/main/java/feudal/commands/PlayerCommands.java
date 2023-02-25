package feudal.commands;

import alterr.command.Command;
import alterr.command.paramter.Param;
import feudal.data.Auction;
import feudal.data.FeudalPlayer;
import feudal.utils.RTPUtils;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.ItemStackWrapper;
import feudal.visual.Menus;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static feudal.data.cache.CacheFeudalPlayers.getFeudalPlayer;
import static feudal.data.cache.CacheFeudalPlayers.hasProfession;

public class PlayerCommands {

    @Command(names = {"f up", "up"}, playerOnly = true)
    public void up(@NotNull Player player) {

        new Menus(player).upgradeProfessionMenu();

    }

    @Command(names = {"f sell", "sell"}, playerOnly = true, cooldown = 5000)
    public void sell(@NotNull Player player, @Param(name = "value") int value) {

        if (player.getInventory().getItemInMainHand() == null) {

            player.sendMessage("Возьмите предмет в основную руку!");
            return;

        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Auction.getProducts().add(new ItemStackWrapper(itemStack.getType(), itemStack.getDurability(), itemStack.getAmount(), itemStack.getItemMeta().getDisplayName(), itemStack.getItemMeta().getLore(), itemStack.getEnchantments(), value));

    }

    @Command(names = {"f ah", "ah"}, playerOnly = true)
    public void ah(@NotNull Player player) {

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        if (feudalPlayer.getProfessionID() != ProfessionIDE.TRADER.getId()) {

            player.sendMessage("Вы не торговец!");

        }

//        new Menus(player).auctionMenu(1);

    }

    @Command(names = {"f prof", "prof"}, playerOnly = true)
    public void prof(@NotNull Player player) {

        if (!hasProfession(player)) {

            player.sendMessage("У вас уже есть профессия!");
            return;

        }

        new Menus(player).professionSelectionMenu();

    }

    @Command(names = {"f rtp", "rtp"}, playerOnly = true, async = true, cooldown = 20000)
    public void rtp(@NotNull Player player) {

        player.teleport(RTPUtils.rtpCalc(player, -25000, 25000, -25000, 25000));

    }

    @Command(names = {"f mail", "mail"}, playerOnly = true)
    public void mail(@NotNull Player player) {
        new Menus(player).mailMenu();
    }

    @Command(names = {"f help", "f"}, playerOnly = true)
    public void help(@NotNull Player player) {

        player.sendMessage("/f up\n" +
                "/f sell\n" +
                "/f ah\n" +
                "/f prof\n" +
                "/f rtp\n" +
                "/f mail\n" +
                "/f create\n" +
                "/f flag\n" +
                "/f removebaron\n" +
                "/f addbaron\n" +
                "/f claim\n" +
                "/f leave\n" +
                "/f disband\n" +
                "/f reject\n" +
                "/f kick\n" +
                "/f invite\n" +
                "/f withdraw\n" +
                "/f replenish\n");

    }
}
