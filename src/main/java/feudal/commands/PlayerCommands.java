package feudal.commands;

import feudal.auction.AuctionMenu;
import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.enums.gameClassesEnums.GameClassesIDEnum;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        switch (args[0]) {

            case "help":

                helpCommand(player);
                break;

            case "create":

                createKingdomCommand(player, args[1]);
                break;

            case "withdraw":

                if (args[1].length() > 10) {

                    player.sendMessage("Слишком большая сумма для снятия");
                    break;

                }

                withdrawMoneyFromTheTreasury(KingdomDBHandler.getPlayerKingdom(player), player, Integer.parseInt(args[1]));

                break;

            case "ah":

                FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

                if (feudalPlayer.getAClassID() != GameClassesIDEnum.TRADER.getId()) break;

                AuctionMenu.openAuctionMenu(player, 1);

                break;

        }

        return false;
    }

    private void helpCommand(@NotNull Player player) {
        player.sendMessage("/f claim - захватить чанк\n" + "/f create - создать королевство\n" + "/f help - меню коамнд\n" + "/f invite - добавить игрока в королевство\n" + "/f kick - удалить игрока из королевства\n" + "/f m - меню королевства\n" + "/f map - soon\n" + "/f shield - soon\n" + "/f location - указать локацию королевства\n" + "/f ah - открыть аукцион\n");
    }

    private void createKingdomCommand(@NotNull Player player, String kingdomName) {

        if (!checkKingdomName(kingdomName)) {

            player.sendMessage("Невозможно создать королевство с таким именем");
            return;

        }

        List<Player> members = new ArrayList<>();
        members.add(player);

        createKingdom(kingdomName, player, members);

    }

    private boolean checkKingdomName(@NotNull String kingdomName) {
        return kingdomName.length() <= 16 && kingdomName.length() > 3;
    }

    private void createKingdom(@NotNull String kingdomName, @NotNull Player player, List<Player> members) {

        KingdomDBHandler.createNewKingdom(kingdomName, player, members, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing((Player) KingdomDBHandler.getField(kingdomName, "king"))
                .setBalance(10000)
                .setReputation(1000)
                .setMembers((List<Player>) KingdomDBHandler.getField(kingdomName, "members"))
                .setBarons((List<Player>) KingdomDBHandler.getField(kingdomName, "barons"))
                .setTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) KingdomDBHandler.getField(kingdomName, "privateTerritory"));

        CacheKingdomsMap.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    private void withdrawMoneyFromTheTreasury(@NotNull String kingdomName, @NotNull Player player, int colum) {

        if (kingdomName.equals("")) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        }

        FeudalKingdom feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(kingdomName);

        if (feudalKingdom.getBalance() < colum) {

            player.sendMessage("В казне недостаточно средств");
            return;

        }

        feudalKingdom.takeBalance(colum);
        CachePlayersMap.getFeudalPlayer(player).addBalance(colum - colum / 100 * 5);

    }

}
